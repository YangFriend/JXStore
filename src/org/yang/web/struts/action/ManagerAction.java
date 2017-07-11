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
	
	//��ҳ,������ʾ��Ʒ��һЩ��Ϣ
	private String display;
	private int page;
	
	//�鿴�����ID
	private long goodsId;
	private long orderId;
	private long bulletinId;
	
	
	
	private GoodsDto goodsDto;//�ϴ�����ƷDTO
	
	//������������
	private String title;
	private String content;
	
	
	//���ظ�ǰ̨
	private List<Goods> goodsList;
	private List<User> userList;
	private List<Order> orderList;
	
	//��½ 
	private String name;
	private String password;
	private String flag;
	
	/**
	 * ��½
	 * @return
	 */
	public String login(){
		
		Manager manager = managerService.login(name, password);
		
		if(manager == null){
			super.request.put("errorMsg", "�û������������!");
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
	 * �ǳ�
	 * @return
	 */
	public String logout(){
		super.session.remove("managerInformation");
		super.request.put("errorMsg", "���Ѱ�ȫ�˳�");
		return "login";
	}
	/**
	 * תCenter
	 * @return
	 */
	public String managerCenter(){
		return SUCCESS;
	}
	
	
	
	/**
	 * �����û�
	 * @return
	 */
	public String users(){
		userList = managerService.getAllUser();
		
		pageInf.setTitle("��̨���� - �û�");
		pageInf.setCaption("��̨���� - �����û�");
		pageInf.setHint("��"+userList.size()+"���û�");
		
		super.request.put("pageInf", pageInf);
		super.request.put("userList", userList);
		return "user";
	}
	
	
	
//////////////
	/**
	 * ���ϼ���Ʒ
	 * 
	 * @return
	 */
	public String upGoods(){
		pageInf.setTitle("��̨���� - ��Ʒ");
		pageInf.setCaption("��̨���� - �ϼ���Ʒ");
		return "upGoods";
	}
	
	/**
	 * �޸���Ʒ
	 * @return
	 */
	public String modGoods(){
		pageInf.setTitle("��̨���� - ��Ʒ");
		pageInf.setCaption("��̨���� - �޸���Ʒ");
		
		Goods goods = managerService.getGoods(goodsId);
		super.request.put("pageInf", pageInf);
		super.request.put("goods", goods);
		return "modGoods";
	}
	
	/**
	 * 
	 * 
	 * �ϴ���Ʒ����
	 * @return
	 * @throws IOException 
	 */
	public String updateProcess() throws IOException{
		pageInf.setTitle("��̨���� - ��Ʒ");
		pageInf.setCaption("��̨���� - �޸���Ʒ");
		pageInf.setMesg("--->>>>>>���³ɹ�>>>>>---");
		
		Goods goods = null;
		try {
			goods = managerService.updateProcess(goodsDto, UPDATE_PATH);
		} catch (ConstraintViolationException e) {
			goods = goodsDto.castGoodsObjPartField();
			pageInf.setMesg("����:ͬ������Ϸ���Ѵ���!");
			
		}
		super.request.put("pageInf", pageInf);
		super.request.put("goods", goods);
		return "modGoods";
	}
	
	

	/**
	 * ����鿴��Ʒ
	 * @return
	 */
	public String goods(){
		pageInf.setTitle("��̨���� - ��Ʒ");
	
		if( display == null || display.equals("all")){
			pageInf.setDisplay("all");
			pageInf.setCaption("��̨���� - ������Ʒ");
			pageInf.setHint("ȫ��");
			
			pageInf.setPageCount(managerService.getAllGoodsPageCount() );
			if(page <= 0){
				page = 1;
			}
			goodsList = managerService.getAllGoodsList(page);
			pageInf.setPage(page);
			
		}else if(display.equals("sell")){
			pageInf.setDisplay("sell");
			pageInf.setCaption("��̨���� - ������Ʒ");
			pageInf.setHint("�����۵�");
			
			pageInf.setPageCount(managerService.getSellGoodsPageCount() );
			if(page <= 0){
				page = 1;
			}
			goodsList = managerService.getSellGoodsList(page);
			pageInf.setPage(page);
			
		}else if(display.equals("drop")){
			pageInf.setDisplay("drop");
			pageInf.setCaption("��̨���� - �¼���Ʒ");
			pageInf.setHint("���¼ܵ�");
			
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
	 * �鿴���ж���
	 * @return
	 */
	public String checkoutOrder(){
		pageInf.setTitle("��̨���� - ����");
		pageInf.setCaption("��̨���� - �鿴���ж���");
		
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
	 * �鿴������������
	 * @return
	 */
	public String orderDetail() {
		pageInf.setTitle("��̨���� - ��������");
		List<OrderGoodsDetail> ogdList = managerService.queryOrderDetal(orderId);
		pageInf.setCaption("��̨���� - "+ogdList.get(0).getId()+"����������");
		
		super.request.put("pageInf", pageInf);
		super.request.put("ogdList",ogdList);		
		return "orderDetail";
	}
	
//////////////
	/**
	 * �鿴���й���
	 * @return
	 */
	public String bulletin() {
		pageInf.setTitle("��̨���� - ����");
		List<Bulletin> bulletinList = managerService.getAllBulletin();
		pageInf.setCaption("��̨���� - "+"���й���");
		
		super.request.put("bulletinList", bulletinList);
		super.request.put("pageInf", pageInf);
		return "bulletin";
	}
	
	
	
	/**
	 * ת�򷢲�����ҳ
	 * 
	 * @return
	 */
	public String turnRelBulletin(){
		pageInf.setTitle("��̨���� - ����");
		pageInf.setCaption("��̨���� - "+"��������");
		super.request.put("pageInf", pageInf);
		return "relBulletin";
	}
	
	/**
	 * �������� - �ύ����
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
