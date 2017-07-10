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
	 * 登陆/查询
	 * 
	 * @param name
	 *            登陆名
	 * @param password
	 *            密码
	 * @return Manager对象;登陆失败返回null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Manager login(String name, String password) {
		return managerDao.queryLogin(name, password);
	}

	/**
	 * 更改用户是否可以登陆 <br >
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            true为禁止用户登陆;
	 */
	@Transactional
	public void changgeUserStatus(long userId, boolean status) {
		userDao.setUserStatus(userId, status);
	}

	/**
	 * 查询所有注册的用户
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<User> getAllUser() {
		return userDao.getUserList();
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUserById(long userId) {
		return userDao.getUser(userId);
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userId
	 *            用户id
	 * @param newPassword
	 *            新的密码
	 */
	@Transactional
	public void restUserPassword(long userId, String newPassword) {
		userDao.restPassword(userId, newPassword);
	}

	////////////
	/**
	 * 分页(通用): 分页计算
	 * 
	 * 给定总数据,使用默认(10条)分页数,计算出共有多少页
	 * 
	 * @param count 给定的总数据
	 * @return 共有多少页
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
	 * 分页(全部): 所有商品分页的页数
	 * 
	 * @return 所有商品分页的页数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int getAllGoodsPageCount() {
		
		return this.getPageCount( goodsDao.getAllCount());
	}

	/**
	 * 分页(全部):获取所有商品,某页数据(包含下架)
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getAllGoodsList(int page) {
//		if ( page > getAllGoodsPageCount()){
//		//需要的结果大总页 
//	}
		page = page * DEFAULE_PAGESIZE - DEFAULE_PAGESIZE;
		return goodsDao.getAllGoodsList(page, DEFAULE_PAGESIZE);
	}


	
	
	/**
	 * 分页(仅售): 仅售商品分页的页数
	 * 
	 * @return 仅售商品分页的页数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getSellGoodsPageCount() {
	
		return this.getPageCount( goodsDao.getSellCount() );
	}

	/**
	 * 分页(仅售):获取仅售商品,某页数据(包含下架)
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
	 * 分页(下架)
	 * @return 下架商品分页的页数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getDropGoodsPageCount() {
		return this.getPageCount( goodsDao.getDropCount() );
	}

	/**
	 * 分页(下架):获取下架商品,某页数据
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
	 * 更新或者上架商品
	 * 
	 * 
	 * @param goodsDto	dto
	 * @param path 保存路径 同时也是 读取路径
	 * @return	返回保存后的商品对象
	 * @throws ConstraintViolationException 上架或更新的商品字段,违反约束
	 * @throws IOException 
	 */
	@Transactional
	public Goods updateProcess(GoodsDto goodsDto,String path) 
			throws ConstraintViolationException, IOException {
		
		//要被持久化到数据库的商品对象
		Goods saveGoods = null;
		
		if(goodsDto.getId() != null){
			//需要更新已存在的
			saveGoods = this.getGoods(goodsDto.getId());
			
		}else{
			//新的 上架
			saveGoods = new Goods();
			saveGoods.setAddedDate(new Date());
		}
		
		
		//旧的图片
		File oldImg = null;
		if(null != saveGoods.getImage())
			oldImg =new File(path +"/"+ saveGoods.getImage());
		
		//上传的图片
		File upImg = goodsDto.getImage();
		//保存后的图片
		File newImg = null;
		
		//更新其他字段
		saveGoods.setGameType(goodsDto.getGameType());
		saveGoods.setInfo(goodsDto.getInfo());
		saveGoods.setName(goodsDto.getName());
		saveGoods.setPrice(goodsDto.getPrice());
		saveGoods.setStatus(goodsDto.getStatus());
		
		try {
			if(upImg != null){
				//需要更新图片
				newImg = this.saveGoodsImage(upImg,path);
				saveGoods.setImage(newImg.getName());
				
				System.out.println("新图片,文件名:"+newImg.getAbsolutePath());
				//删除旧文件
				if(oldImg != null){
					System.out.println("删除旧的图片:"+oldImg.getAbsolutePath());
					oldImg.delete();
				}
					
			}
			//保存/持久化
			goodsDao.saveOrUpdate(saveGoods);
			
		} catch (ConstraintViolationException e1) {	
			//违反约束
			if(upImg != null){
				//删除保存的图片
				System.out.println("违反约束");
				System.out.println("删除保存的图片,文件名:"+oldImg.getName());
				newImg.delete();
			}
			throw e1;//向上抛 事务问题,数据库回滚
		}finally{
			if(upImg != null){
				//删除上传的临时文件
				System.out.println("删除上传的临时文件:"+upImg.getAbsolutePath());
				upImg.delete();
			}
		}
		
		return saveGoods; 
	}


	
	/**
	 * 保存上传的商品图片
	 * 注意,没有删除struts的临时文件
	 * 
	 * @param image 上传的图片文件
	 * @param path 保存路径
	 * @return	返回保存后的文件
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
		//一些必须做的>..
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
	 * 根据id 查询商品
	 * 
	 * @param goodsId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Goods getGoods(long goodsId) {
		return goodsDao.getGoods(goodsId);
	}

	/**
	 * 更新商品
	 * 
	 * @param goods
	 */
	@Transactional
	public void updateGoods(Goods goods) {
		goodsDao.update(goods);
	}

	/**
	 * 上下架这个商品
	 * 
	 * @param goodsId
	 * @param status
	 *            true 状态设置为上架; false状态设置为下架;
	 */
	@Transactional
	public void changeGoods(long goodsId, boolean status) {
		goodsDao.setStatus(goodsId, status);
	}
	////////////
	
	/**
	 * 分页(订单):
	 * 
	 * @return 订单总页页数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int getOrderCount(){
		
		return this.getPageCount( orderDao.getAllOrderCount());
	}
	
	/**
	 * 分页():获取所有订单,某页的数据
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
	 * 查询这个订单id的,商品/账号详情列表
	 * 
	 * @param orderId 要查询的订单id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderGoodsDetail> queryOrderDetal(long orderId) {
			List<OrderGoodsDetail> ogdList = orderDao.queryOrderGoodsDetail(orderId);
		return ogdList;
		
	}

	
	// //////////

	/**
	 * 发布公告
	 * 
	 * @param manager
	 *            发布者
	 * @param title
	 *            标题
	 * @param content
	 *            内容
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
	 * 查询/返回所有公告
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getAllBulletin() {
		return (List<Bulletin>) bulletinDao.getAllBulletin();
	}

	/**
	 * 根据ID 删除公告
	 * 
	 * @param bulletinId
	 */
	@Transactional
	public void delete(long bulletinId) {
		bulletinDao.delete(bulletinId);
	}
	


}
