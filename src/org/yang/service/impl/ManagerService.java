package org.yang.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.Bulletin;
import org.yang.bean.Goods;
import org.yang.bean.Manager;
import org.yang.bean.Order;
import org.yang.bean.OrderGoodsDetail;
import org.yang.bean.User;
import org.yang.dao.impl.BulletinDao;
import org.yang.dao.impl.GameAccountsDao;
import org.yang.dao.impl.GoodsDao;
import org.yang.dao.impl.ManagerDao;
import org.yang.dao.impl.OrderDao;
import org.yang.dao.impl.UserDao;
import org.yang.dto.GoodsDto;
import org.yang.util.Tool;


@Component("managerService")
public class ManagerService {

	private final static int DEFAULE_PAGESIZE = 10;
	
	private ManagerDao managerDao;
	private UserDao userDao;
	private GoodsDao goodsDao;
	private GameAccountsDao gameAccountsDao;
	private OrderDao orderDao;
	private BulletinDao bulletinDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

	public OrderDao getOrderDao() {
		return orderDao;
	}

	@Resource(name = "orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public BulletinDao getBulletinDao() {
		return bulletinDao;
	}

	@Resource(name = "bulletinDao")
	public void setBulletinDao(BulletinDao bulletinDao) {
		this.bulletinDao = bulletinDao;
	}

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	@Resource(name = "managerDao")
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	/**
	 * ��½/��ѯ
	 * 
	 * @param name
	 *            ��½��
	 * @param password
	 *            ����
	 * @return Manager����;��½ʧ�ܷ���null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Manager login(String name, String password) {
		return managerDao.queryLogin(name, password);
	}

	/**
	 * �����û��Ƿ���Ե�½ <br >
	 * 
	 * @param userId
	 *            �û�id
	 * @param status
	 *            trueΪ��ֹ�û���½;
	 */
	@Transactional
	public void changgeUserStatus(long userId, boolean status) {
		userDao.setUserStatus(userId, status);
	}

	/**
	 * ��ѯ����ע����û�
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<User> getAllUser() {
		return userDao.getUserList();
	}

	/**
	 * ����id��ѯ�û�
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUserById(long userId) {
		return userDao.getUser(userId);
	}

	/**
	 * �����û�����
	 * 
	 * @param userId
	 *            �û�id
	 * @param newPassword
	 *            �µ�����
	 */
	@Transactional
	public void restUserPassword(long userId, String newPassword) {
		userDao.restPassword(userId, newPassword);
	}

	////////////
	/**
	 * ��ҳ(ͨ��): ��ҳ����
	 * 
	 * ����������,ʹ��Ĭ��(10��)��ҳ��,��������ж���ҳ
	 * 
	 * @param count ������������
	 * @return ���ж���ҳ
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	private int getPageCount(long count) {
		
		if (count % DEFAULE_PAGESIZE == 0) {
			return (int) (count / DEFAULE_PAGESIZE);
		}else{
			return (int) (count / DEFAULE_PAGESIZE + 1);
		}
	}

	/**
	 * ��ҳ(ȫ��): ������Ʒ��ҳ��ҳ��
	 * 
	 * @return ������Ʒ��ҳ��ҳ��
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int getAllGoodsPageCount() {
		
		return this.getPageCount( goodsDao.getAllCount());
	}

	/**
	 * ��ҳ(ȫ��):��ȡ������Ʒ,ĳҳ����(�����¼�)
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getAllGoodsList(int page) {
//		if ( page > getAllGoodsPageCount()){
//		//��Ҫ�Ľ������ҳ 
//	}
		page = page * DEFAULE_PAGESIZE - DEFAULE_PAGESIZE;
		return goodsDao.getAllGoodsList(page, DEFAULE_PAGESIZE);
	}


	
	
	/**
	 * ��ҳ(����): ������Ʒ��ҳ��ҳ��
	 * 
	 * @return ������Ʒ��ҳ��ҳ��
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getSellGoodsPageCount() {
	
		return this.getPageCount( goodsDao.getSellCount() );
	}

	/**
	 * ��ҳ(����):��ȡ������Ʒ,ĳҳ����(�����¼�)
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getSellGoodsList(int page) {
	
		page = page * DEFAULE_PAGESIZE - DEFAULE_PAGESIZE;
		return goodsDao.getSellGoodsList(page, DEFAULE_PAGESIZE);
	}
	
	/**
	 * ��ҳ(�¼�)
	 * @return �¼���Ʒ��ҳ��ҳ��
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getDropGoodsPageCount() {
		return this.getPageCount( goodsDao.getDropCount() );
	}

	/**
	 * ��ҳ(�¼�):��ȡ�¼���Ʒ,ĳҳ����
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getDropGoodsList(int page) {

		page = page * DEFAULE_PAGESIZE - DEFAULE_PAGESIZE;
		return goodsDao.getDropGoodsList(page, DEFAULE_PAGESIZE);
	}
	

	/**
	 * ���»����ϼ���Ʒ
	 * 
	 * 
	 * @param goodsDto	dto
	 * @param path ����·�� ͬʱҲ�� ��ȡ·��
	 * @return	���ر�������Ʒ����
	 * @throws ConstraintViolationException �ϼܻ���µ���Ʒ�ֶ�,Υ��Լ��
	 * @throws IOException 
	 */
	@Transactional
	public Goods updateProcess(GoodsDto goodsDto,String path) 
			throws ConstraintViolationException, IOException {
		
		//Ҫ���־û������ݿ����Ʒ����
		Goods saveGoods = null;
		
		if(goodsDto.getId() != null){
			//��Ҫ�����Ѵ��ڵ�
			saveGoods = this.getGoods(goodsDto.getId());
			
		}else{
			//�µ� �ϼ�
			saveGoods = new Goods();
			saveGoods.setAddedDate(new Date());
		}
		
		
		//�ɵ�ͼƬ
		File oldImg = null;
		if(null != saveGoods.getImage())
			oldImg =new File(path +"/"+ saveGoods.getImage());
		
		//�ϴ���ͼƬ
		File upImg = goodsDto.getImage();
		//������ͼƬ
		File newImg = null;
		
		//���������ֶ�
		saveGoods.setGameType(goodsDto.getGameType());
		saveGoods.setInfo(goodsDto.getInfo());
		saveGoods.setName(goodsDto.getName());
		saveGoods.setPrice(goodsDto.getPrice());
		saveGoods.setStatus(goodsDto.getStatus());
		
		try {
			if(upImg != null){
				//��Ҫ����ͼƬ
				newImg = this.saveGoodsImage(upImg,path);
				saveGoods.setImage(newImg.getName());
				
				System.out.println("��ͼƬ,�ļ���:"+newImg.getAbsolutePath());
				//ɾ�����ļ�
				if(oldImg != null){
					System.out.println("ɾ���ɵ�ͼƬ:"+oldImg.getAbsolutePath());
					oldImg.delete();
				}
					
			}
			//����/�־û�
			goodsDao.saveOrUpdate(saveGoods);
			
		} catch (ConstraintViolationException e1) {	
			//Υ��Լ��
			if(upImg != null){
				//ɾ�������ͼƬ
				System.out.println("Υ��Լ��");
				System.out.println("ɾ�������ͼƬ,�ļ���:"+oldImg.getName());
				newImg.delete();
			}
			throw e1;//������ ��������,���ݿ�ع�
		}finally{
			if(upImg != null){
				//ɾ���ϴ�����ʱ�ļ�
				System.out.println("ɾ���ϴ�����ʱ�ļ�:"+upImg.getAbsolutePath());
				upImg.delete();
			}
		}
		
		return saveGoods; 
	}


	
	/**
	 * �����ϴ�����ƷͼƬ
	 * ע��,û��ɾ��struts����ʱ�ļ�
	 * 
	 * @param image �ϴ���ͼƬ�ļ�
	 * @param path ����·��
	 * @return	���ر������ļ�
	 * @throws IOException
	 */
	private File saveGoodsImage(File image,String path) throws IOException{
		FileInputStream fis = null;
		FileOutputStream out = null;
		File file = null;
		try {
			fis = new FileInputStream(image);
			file = new File(path+"//"+Tool.returnUUID()+".jpg");
			out = new FileOutputStream(file );
			
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				out.write(b, 0, i);
			}
		//һЩ��������>..
		} catch (IOException e) {
			throw e;
			
		}finally{
			try {
				if (fis != null )
					fis.close();
			}catch (IOException e2){throw e2;}
			
			
			try {
				if (out != null )
					out.close();
			} catch (IOException e3) {throw e3; }
		}
		
		return file;
		
	}
	
	
	
	
	
	
	
	

	/**
	 * ����id ��ѯ��Ʒ
	 * 
	 * @param goodsId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Goods getGoods(long goodsId) {
		return goodsDao.getGoods(goodsId);
	}

	/**
	 * ������Ʒ
	 * 
	 * @param goods
	 */
	@Transactional
	public void updateGoods(Goods goods) {
		goodsDao.update(goods);
	}

	/**
	 * ���¼������Ʒ
	 * 
	 * @param goodsId
	 * @param status
	 *            true ״̬����Ϊ�ϼ�; false״̬����Ϊ�¼�;
	 */
	@Transactional
	public void changeGoods(long goodsId, boolean status) {
		goodsDao.setStatus(goodsId, status);
	}
	////////////
	
	/**
	 * ��ҳ(����):
	 * 
	 * @return ������ҳҳ��
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int getOrderCount(){
		
		return this.getPageCount( orderDao.getAllOrderCount());
	}
	
	/**
	 * ��ҳ():��ȡ���ж���,ĳҳ������
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Order> getOrderList(int page){
		
		page = page * DEFAULE_PAGESIZE - DEFAULE_PAGESIZE;
		return orderDao.getAllOrderList(page, DEFAULE_PAGESIZE);
	}
	
	/**
	 * ��ѯ�������id��,��Ʒ/�˺������б�
	 * 
	 * @param orderId Ҫ��ѯ�Ķ���id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderGoodsDetail> queryOrderDetal(long orderId) {
			List<OrderGoodsDetail> ogdList = orderDao.queryOrderGoodsDetail(orderId);
		return ogdList;
		
	}

	
	// //////////

	/**
	 * ��������
	 * 
	 * @param manager
	 *            ������
	 * @param title
	 *            ����
	 * @param content
	 *            ����
	 */
	@Transactional
	public void releaseBulletin(Manager manager, String title, String content) {
		Bulletin bean = new Bulletin();
		bean.setManagerid(manager.getId());
		bean.setReleaseDate(new java.util.Date());
		bean.setTitle(title);
		bean.setContent(content);
		bulletinDao.save(bean);
	}



	/**
	 * ��ѯ/�������й���
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getAllBulletin() {
		return (List<Bulletin>) bulletinDao.getAllBulletin();
	}

	/**
	 * ����ID ɾ������
	 * 
	 * @param bulletinId
	 */
	@Transactional
	public void delete(long bulletinId) {
		bulletinDao.delete(bulletinId);
	}
	


}
