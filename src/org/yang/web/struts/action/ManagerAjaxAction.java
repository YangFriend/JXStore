package org.yang.web.struts.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.service.impl.ManagerService;
import org.yang.util.Tool;




@Component("managerAjaxAction")
@Scope("prototype")
public class ManagerAjaxAction extends SupportAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ManagerService managerService;
	
	private long userId;
	private long bulletinId;
	private String userNewPwd;
	
	/**
	 * 禁止某个用户登录
	 * 成功在html流中 写入"ok"
	 */
	public void blockUser(){
		System.out.println(userId);
		managerService.changgeUserStatus(userId, true);
		super.printHTML("ok");
	}
	
	/**
	 * 允许某个用户登录
	 * 成功在html流中 写入"ok"
	 */
	public void acceptUser(){
		managerService.changgeUserStatus(userId, false);
		super.printHTML("ok");
	}
	/**
	 * 为用户重置密码
	 * 成功在html流中 写入"ok"
	 */
	public void restUserPwd(){
		//加密
		userNewPwd = Tool.encryptionString(userNewPwd);
		managerService.restUserPassword(userId, userNewPwd);
		super.printHTML("ok");
	}
	
	/**
	 * 删除公告
	 * 成功在html流中 写入"ok"
	 */
	public void deleteBulletin() {
		managerService.delete(bulletinId);
		super.printHTML("ok");
	}
	
	
	
	public String getUserNewPwd() {
		return userNewPwd;
	}

	public void setUserNewPwd(String userNewPwd) {
		this.userNewPwd = userNewPwd;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public long getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(long bulletinId) {
		this.bulletinId = bulletinId;
	}
	
	

}
