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
	 * 下单时的商品列表,在下单前,购买后清空
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
	 * 预定/下单
	 * 
	 * @param u
	 *            下单用户
	 * @param buyGoodsList其中key =商品id; value=购买数量
	 * @return 返回订单bean;
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
		// 设置订单信息...
		Order order = new Order(u);
		order.setOrderDate(new Date());
		order.setAllValue(goodsValue);
		order.setAccountNum(aNum);
		order.setGoodsNum(gNum);
		//创建 返回
		return orderDao.create(order);
	}

	/**
	 * 购买/剁手;
	 * 
	 * TODO CartDto是用户提交的 可能会被修改/ 两套独立计算方式 并无问题 ,感觉哪里不太对...
	 * 
	 * @param u
	 *            购买/下单的用户
	 * @param buyGoodsList
	 *            其中key=商品id; value=购买数量
	 * @throws UserMoneyNotEnoughException
	 *             当用户余额不足以支付该订单时
	 * @throws GameAccountNotEnoughException
	 *             当购买的商品库存不足时
	 */
	@Transactional
	public void buy(User u, Map<Long, Integer> buyGoodsList)
			throws UserMoneyNotEnoughException, GameAccountNotEnoughException {
		// 订单:生成订单/概览
		Order order = this.reserved(u, buyGoodsList);

		// 通过Order查询的User还能解决实时问题 ;
		User orderUser = order.getUser();

		// 商品总价值;算下够不够money
		double goodsValue = order.getAllValue();

		double userMoney = orderUser.getMoney();

		if (orderUser.getGread() == 5) {
			// 首单用户5
			goodsValue = goodsValue * 0.1;
		}

		if (goodsValue > userMoney) {
			// throw 余额不足
			throw new UserMoneyNotEnoughException();
		}
		/**
		 * 订单:售出的商品详情列表
		 */
		List<OrderGoodsDetail> orderGoodsDS = new ArrayList<OrderGoodsDetail>();

		for (Goods goods : this.reservedGoodsList) {
			/**
			 * 订单中的单个商品细节
			 */
			OrderGoodsDetail ogd = new OrderGoodsDetail(order.getId(),
					goods.getId(), goods.getName(), goods.getInfo(),
					goods.getPrice(), buyGoodsList.get(goods.getId()));
		
			/**
			 * 订单:单个商品中 售出的账号列表
			 */
			List<OrderAccountDetail> oadL = new ArrayList<OrderAccountDetail>();
			
			//账号列表 bean 数据库中的
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
				// throw 商品库存小于需要购买的数量(库存不足)
				throw new GameAccountNotEnoughException();
			}
			orderGoodsDS.add(ogd);
		}

		// 订单:实际付款价格
		order.setActPrice(goodsValue);
		// 订单:更新商品详情
		orderDao.updateOrder(order, orderGoodsDS);

		// 用户扣钱 ,gread=6(移除首单优惠) 持久化 清空temp list.. (TODO 这里非常不好,设计之前没想到coupon问题...
		// 将就吧...)
		orderUser.setGread(6);
		orderUser.setMoney(userMoney - goodsValue);
		userDao.update(orderUser);
		reservedGoodsList.clear();
	}

	/**
	 * 查询这个user的订单列表
	 * 
	 * @param user
	 * @return 没有则返回null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Order> queryMyOrder(User user) {
		return orderDao.queryOrderByUser(user.getId());
	}


	/**
	 * 查询这个订单id的,商品/账号详情列表
	 * 
	 * @param u 查询的用户
	 * @param orderId 要查询的订单id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderGoodsDetail> queryMyOrderGoodsDetail(User u,long orderId) {
		
		Order order = orderDao.queryOrderById(orderId);
		
		if(order.getUser().getId() == u.getId() ){
			List<OrderGoodsDetail> ogdList = orderDao.queryOrderGoodsDetail(orderId);
			//只能自己查自己的订单
			return ogdList;
		}
		return null;
		
	}

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return 失败返回false;成功返回true;
	 */
	@Transactional
	public boolean regUser(User user) {

		if (userDao.checkUserByUserName(user.getUserName())) {
			return false;
		}
		if (userDao.checkUserByUserName(user.getEmail())) {
			return false;
		}
		// 简单加密密码
		user.setUserPassword(Tool.encryptionString(user.getUserPassword()));
		// 设置新会员的默认属性, 注册即送1999 :)
		user.setGread(5);
		user.setStatus(0);
		user.setRegDate(new java.util.Date());
		user.setMoney(1999.00);
		userDao.save(user);
		return true;
	}

	/**
	 * 加载/查询(登陆)
	 * 
	 * @param u
	 * @return 返回新的完整的user; 登陆失败返回null
	 */
	@Transactional(readOnly = true)
	public User loadUserByName(User u) {
		// 简单加密密码
		u.setUserPassword(Tool.encryptionString(u.getUserPassword()));
		u = userDao.queryUserByName(u);
		return u;
	}

	/**
	 * 使用已经是密文的密码 (登陆)加载/查询
	 * 
	 * @param u
	 * @return 返回新的完整的user; 登陆失败返回null
	 */
	@Transactional(readOnly = true)
	public User loadUser(User u) {
		u = userDao.queryUserByName(u);
		return u;
	}



	/**
	 * 刷新用户信息
	 * 
	 * @param u
	 */
	@Transactional(readOnly = true)
	public void refreshUser(User u) {
		userDao.refresh(u);
	}

	/**
	 * 更新密码
	 * 
	 * @param user
	 * @param newPassword
	 */
	@Transactional
	public void updatePassword(User user, String newPassword) {
		// 简单加密密码
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
