package org.yang.web.struts.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Bulletin;
import org.yang.bean.Goods;
import org.yang.bean.Manager;
import org.yang.bean.Order;
import org.yang.bean.OrderGoodsDetail;
import org.yang.bean.User;
import org.yang.dto.GoodsDto;
import org.yang.dto.ManagerPageInfoDto;
import org.yang.service.impl.ManagerService;




@Component("managerAction")
@Scope("prototype")
public class ManagerAction extends SupportAction {

	private static final long serialVersionUID = 1L;
	
	private static final String UPDATE_PATH = ServletActionContext.
			getServletContext().getRealPath("/jsp")+"/goods/img";
	
	@Autowired
	ManagerService managerService;
	
	ManagerPageInfoDto pageInf = new ManagerPageInfoDto();
	
	//分页,分类显示商品的一些信息
	private String display;
	private int page;
	
	//查看详情的ID
	private long goodsId;
	private long orderId;
	private long bulletinId;
	
	
	
	private GoodsDto goodsDto;//上传的商品DTO
	
	//发布公告所需
	private String title;
	private String content;
	
	
	//返回给前台
	private List<Goods> goodsList;
	private List<User> userList;
	private List<Order> orderList;
	
	//登陆 
	private String name;
	private String password;
	private String flag;
	
	/**
	 * 登陆
	 * @return
	 */
	public String login(){
		
		Manager manager = managerService.login(name, password);
		
		if(manager == null){
			super.request.put("errorMsg", "用户名或密码错误!");
			return "login";
		}
		super.session.put("managerInformation", manager);
		
		if(flag != null){
			if(flag.equals("u")){
				
				return "chainUsers";
				
			}else if(flag.equals("g")){
				
				return "chainGoods";
				
			}else if(flag.equals("o")){
				
				return "chainOrder";
				
			}else if(flag.equals("b")){
				
				return "chainBulletin";
				
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 登出
	 * @return
	 */
	public String logout(){
		super.session.remove("managerInformation");
		super.request.put("errorMsg", "你已安全退出");
		return "login";
	}
	/**
	 * 转Center
	 * @return
	 */
	public String managerCenter(){
		return SUCCESS;
	}
	
	
	
	/**
	 * 管理用户
	 * @return
	 */
	public String users(){
		userList = managerService.getAllUser();
		
		pageInf.setTitle("后台管理 - 用户");
		pageInf.setCaption("后台管理 - 所有用户");
		pageInf.setHint("共"+userList.size()+"个用户");
		
		super.request.put("pageInf", pageInf);
		super.request.put("userList", userList);
		return "user";
	}
	
	
	
//////////////
	/**
	 * 新上架商品
	 * 
	 * @return
	 */
	public String upGoods(){
		pageInf.setTitle("后台管理 - 商品");
		pageInf.setCaption("后台管理 - 上架商品");
		return "upGoods";
	}
	
	/**
	 * 修改商品
	 * @return
	 */
	public String modGoods(){
		pageInf.setTitle("后台管理 - 商品");
		pageInf.setCaption("后台管理 - 修改商品");
		
		Goods goods = managerService.getGoods(goodsId);
		super.request.put("pageInf", pageInf);
		super.request.put("goods", goods);
		return "modGoods";
	}
	
	/**
	 * 
	 * 
	 * 上传商品处理
	 * @return
	 * @throws IOException 
	 */
	public String updateProcess() throws IOException{
		pageInf.setTitle("后台管理 - 商品");
		pageInf.setCaption("后台管理 - 修改商品");
		pageInf.setMesg("--->>>>>>更新成功>>>>>---");
		
		Goods goods = null;
		try {
			goods = managerService.updateProcess(goodsDto, UPDATE_PATH);
		} catch (ConstraintViolationException e) {
			goods = goodsDto.castGoodsObjPartField();
			pageInf.setMesg("错误:同类型游戏名已存在!");
			
		}
		super.request.put("pageInf", pageInf);
		super.request.put("goods", goods);
		return "modGoods";
	}
	
	

	/**
	 * 管理查看商品
	 * @return
	 */
	public String goods(){
		pageInf.setTitle("后台管理 - 商品");
	
		if( display == null || display.equals("all")){
			pageInf.setDisplay("all");
			pageInf.setCaption("后台管理 - 所有商品");
			pageInf.setHint("全部");
			
			pageInf.setPageCount(managerService.getAllGoodsPageCount() );
			if(page <= 0){
				page = 1;
			}
			goodsList = managerService.getAllGoodsList(page);
			pageInf.setPage(page);
			
		}else if(display.equals("sell")){
			pageInf.setDisplay("sell");
			pageInf.setCaption("后台管理 - 在售商品");
			pageInf.setHint("仅在售的");
			
			pageInf.setPageCount(managerService.getSellGoodsPageCount() );
			if(page <= 0){
				page = 1;
			}
			goodsList = managerService.getSellGoodsList(page);
			pageInf.setPage(page);
			
		}else if(display.equals("drop")){
			pageInf.setDisplay("drop");
			pageInf.setCaption("后台管理 - 下架商品");
			pageInf.setHint("已下架的");
			
			pageInf.setPageCount(managerService.getDropGoodsPageCount() );
			if(page <= 0){
				page = 1;
			}
			goodsList = managerService.getDropGoodsList(page);
			pageInf.setPage(page);
		}
		
		super.request.put("pageInf", pageInf);
		super.request.put("goodsList", goodsList);
		return "goods";
	}
//////////////
	/**
	 * 查看所有订单
	 * @return
	 */
	public String checkoutOrder(){
		pageInf.setTitle("后台管理 - 订单");
		pageInf.setCaption("后台管理 - 查看所有订单");
		
		int pageCount = managerService.getOrderCount();
		if(page <= 0 || page > pageCount){
			page = 1;
		}
		orderList = managerService.getOrderList(page);
		
		pageInf.setPageCount(page);
		pageInf.setPageCount(pageCount);
		
		super.request.put("pageInf", pageInf);
		super.request.put("orderList",orderList);
		return "order";
	}
	/**
	 * 查看订单订单详情
	 * @return
	 */
	public String orderDetail() {
		pageInf.setTitle("后台管理 - 订单详情");
		List<OrderGoodsDetail> ogdList = managerService.queryOrderDetal(orderId);
		pageInf.setCaption("后台管理 - "+ogdList.get(0).getId()+"订单的详情");
		
		super.request.put("pageInf", pageInf);
		super.request.put("ogdList",ogdList);		
		return "orderDetail";
	}
	
//////////////
	/**
	 * 查看所有公告
	 * @return
	 */
	public String bulletin() {
		pageInf.setTitle("后台管理 - 公告");
		List<Bulletin> bulletinList = managerService.getAllBulletin();
		pageInf.setCaption("后台管理 - "+"所有公告");
		
		super.request.put("bulletinList", bulletinList);
		super.request.put("pageInf", pageInf);
		return "bulletin";
	}
	
	
	
	/**
	 * 转向发布公告页
	 * 
	 * @return
	 */
	public String turnRelBulletin(){
		pageInf.setTitle("后台管理 - 公告");
		pageInf.setCaption("后台管理 - "+"发布公告");
		super.request.put("pageInf", pageInf);
		return "relBulletin";
	}
	
	/**
	 * 发布公告 - 提交处理
	 * 
	 * 
	 * @return
	 */
	public String releaseBulletin() {
		
		Manager manager = (Manager) super.session.get("managerInformation");
		if(manager == null){
			return "global-illegal";
		}
		managerService.releaseBulletin(manager, title, content);
		return "chainBullet";
	}

	

	
	
	

	public GoodsDto getGoodsDto() {
		return goodsDto;
	}

	public void setGoodsDto(GoodsDto goodsDto) {
		this.goodsDto = goodsDto;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public ManagerService getManagerService() {
		return managerService;
	}
	
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(long bulletinId) {
		this.bulletinId = bulletinId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
