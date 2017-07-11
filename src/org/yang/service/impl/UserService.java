package org.yang.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.GameAccounts;
import org.yang.bean.Goods;
import org.yang.bean.Order;
import org.yang.bean.OrderAccountDetail;
import org.yang.bean.OrderGoodsDetail;
import org.yang.bean.User;
import org.yang.dao.impl.GameAccountsDao;
import org.yang.dao.impl.GoodsDao;
import org.yang.dao.impl.OrderDao;
import org.yang.dao.impl.UserDao;
import org.yang.exception.GameAccountNotEnoughException;
import org.yang.exception.UserMoneyNotEnoughException;
import org.yang.util.Tool;


@Component("userService")
public class UserService {

	private UserDao userDao;
	private OrderDao orderDao;
	private GoodsDao goodsDao;
	private GameAccountsDao gameAccountsDao;

	/**
	 * �µ�ʱ����Ʒ�б�,���µ�ǰ,��������
	 */
	private List<Goods> reservedGoodsList = new ArrayList<Goods>();

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	@Resource(name = "orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public GameAccountsDao getGameAccountsDao() {
		return gameAccountsDao;
	}

	@Resource(name = "gameAccountsDao")
	public void setGameAccountsDao(GameAccountsDao gameAccountsDao) {
		this.gameAccountsDao = gameAccountsDao;
	}

	/**
	 * Ԥ��/�µ�
	 * 
	 * @param u
	 *            �µ��û�
	 * @param buyGoodsList����key =��Ʒid; value=��������
	 * @return ���ض���bean;
	 */
	@Transactional
	public Order reserved(User u, Map<Long, Integer> buyGoodsList) {
		double goodsValue = 0;
		reservedGoodsList.clear();
		int aNum = 0;
		int gNum = 0;
		for (Entry<Long, Integer> element : buyGoodsList.entrySet()) {
			gNum++;
			aNum += element.getValue();
			Goods goods = goodsDao.getGoodsById(element.getKey());
			goodsValue += (goods.getPrice() * element.getValue());
			reservedGoodsList.add(goods);
		}
		// ���ö�����Ϣ...
		Order order = new Order(u);
		order.setOrderDate(new Date());
		order.setAllValue(goodsValue);
		order.setAccountNum(aNum);
		order.setGoodsNum(gNum);
		//���� ����
		return orderDao.create(order);
	}

	/**
	 * ����/����;
	 * 
	 * TODO CartDto���û��ύ�� ���ܻᱻ�޸�/ ���׶������㷽ʽ �������� ,�о����ﲻ̫��...
	 * 
	 * @param u
	 *            ����/�µ����û�
	 * @param buyGoodsList
	 *            ����key=��Ʒid; value=��������
	 * @throws UserMoneyNotEnoughException
	 *             ���û�������֧���ö���ʱ
	 * @throws GameAccountNotEnoughException
	 *             ���������Ʒ��治��ʱ
	 */
	@Transactional
	public void buy(User u, Map<Long, Integer> buyGoodsList)
			throws UserMoneyNotEnoughException, GameAccountNotEnoughException {
		// ����:���ɶ���/����
		Order order = this.reserved(u, buyGoodsList);

		// ͨ��Order��ѯ��User���ܽ��ʵʱ���� ;
		User orderUser = order.getUser();

		// ��Ʒ�ܼ�ֵ;���¹�����money
		double goodsValue = order.getAllValue();

		double userMoney = orderUser.getMoney();

		if (orderUser.getGread() == 5) {
			// �׵��û�5
			goodsValue = goodsValue * 0.1;
		}

		if (goodsValue > userMoney) {
			// throw ����
			throw new UserMoneyNotEnoughException();
		}
		/**
		 * ����:�۳�����Ʒ�����б�
		 */
		List<OrderGoodsDetail> orderGoodsDS = new ArrayList<OrderGoodsDetail>();

		for (Goods goods : this.reservedGoodsList) {
			/**
			 * �����еĵ�����Ʒϸ��
			 */
			OrderGoodsDetail ogd = new OrderGoodsDetail(order.getId(),
					goods.getId(), goods.getName(), goods.getInfo(),
					goods.getPrice(), buyGoodsList.get(goods.getId()));
		
			/**
			 * ����:������Ʒ�� �۳����˺��б�
			 */
			List<OrderAccountDetail> oadL = new ArrayList<OrderAccountDetail>();
			
			//�˺��б� bean ���ݿ��е�
			List<GameAccounts> gal = gameAccountsDao.sellGameAccounts(
					goods.getId(), buyGoodsList.get(goods.getId()));
			
			for(GameAccounts ga: gal){
				OrderAccountDetail oad = new OrderAccountDetail();
				oad.setAccountName(ga.getAccountName());
				oad.setPassword(ga.getPassword());
				oad.setTer(ga.getTer());
				oadL.add(oad);
			}
			ogd.setAccounts(oadL);

			if (gal.size() < buyGoodsList.get(goods.getId())) {
				// throw ��Ʒ���С����Ҫ���������(��治��)
				throw new GameAccountNotEnoughException();
			}
			orderGoodsDS.add(ogd);
		}

		// ����:ʵ�ʸ���۸�
		order.setActPrice(goodsValue);
		// ����:������Ʒ����
		orderDao.updateOrder(order, orderGoodsDS);

		// �û���Ǯ ,gread=6(�Ƴ��׵��Ż�) �־û� ���temp list.. (TODO ����ǳ�����,���֮ǰû�뵽coupon����...
		// ���Ͱ�...)
		orderUser.setGread(6);
		orderUser.setMoney(userMoney - goodsValue);
		userDao.update(orderUser);
		reservedGoodsList.clear();
	}

	/**
	 * ��ѯ���user�Ķ����б�
	 * 
	 * @param user
	 * @return û���򷵻�null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Order> queryMyOrder(User user) {
		return orderDao.queryOrderByUser(user.getId());
	}


	/**
	 * ��ѯ�������id��,��Ʒ/�˺������б�
	 * 
	 * @param u ��ѯ���û�
	 * @param orderId Ҫ��ѯ�Ķ���id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderGoodsDetail> queryMyOrderGoodsDetail(User u,long orderId) {
		
		Order order = orderDao.queryOrderById(orderId);
		
		if(order.getUser().getId() == u.getId() ){
			List<OrderGoodsDetail> ogdList = orderDao.queryOrderGoodsDetail(orderId);
			//ֻ���Լ����Լ��Ķ���
			return ogdList;
		}
		return null;
		
	}

	/**
	 * ע���û�
	 * 
	 * @param user
	 * @return ʧ�ܷ���false;�ɹ�����true;
	 */
	@Transactional
	public boolean regUser(User user) {

		if (userDao.checkUserByUserName(user.getUserName())) {
			return false;
		}
		if (userDao.checkUserByUserName(user.getEmail())) {
			return false;
		}
		// �򵥼�������
		user.setUserPassword(Tool.encryptionString(user.getUserPassword()));
		// �����»�Ա��Ĭ������, ע�ἴ��1999 :)
		user.setGread(5);
		user.setStatus(0);
		user.setRegDate(new java.util.Date());
		user.setMoney(1999.00);
		userDao.save(user);
		return true;
	}

	/**
	 * ����/��ѯ(��½)
	 * 
	 * @param u
	 * @return �����µ�������user; ��½ʧ�ܷ���null
	 */
	@Transactional(readOnly = true)
	public User loadUserByName(User u) {
		// �򵥼�������
		u.setUserPassword(Tool.encryptionString(u.getUserPassword()));
		u = userDao.queryUserByName(u);
		return u;
	}

	/**
	 * ʹ���Ѿ������ĵ����� (��½)����/��ѯ
	 * 
	 * @param u
	 * @return �����µ�������user; ��½ʧ�ܷ���null
	 */
	@Transactional(readOnly = true)
	public User loadUser(User u) {
		u = userDao.queryUserByName(u);
		return u;
	}



	/**
	 * ˢ���û���Ϣ
	 * 
	 * @param u
	 */
	@Transactional(readOnly = true)
	public void refreshUser(User u) {
		userDao.refresh(u);
	}

	/**
	 * ��������
	 * 
	 * @param user
	 * @param newPassword
	 */
	@Transactional
	public void updatePassword(User user, String newPassword) {
		// �򵥼�������
		user.setUserPassword(Tool.encryptionString(newPassword));
		userDao.update(user);
	}

	/**
	 * @see org.yang.dao.impl.UserDao#checkUserByEmail(String)
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public boolean checkUserByEmail(String email) {
		return userDao.checkUserByEmail(email);
	}

	/**
	 * 
	 * @see org.yang.dao.impl.UserDao#checkUserByUserName(String)
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public boolean checkUserByUserName(String name) {
		return userDao.checkUserByUserName(name);
	}

}
