package org.yang.web.struts.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.service.impl.UserService;


@Component("userAjaxAction")
@Scope("prototype")
public class UserAjaxAction extends SupportAction {
	private String name;
	private String email; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;


	/**
	 * 检查用户名是否已被注册
	 * 
	 * 直接向html写入纯文本 存在写入true; 否则false;
	 */
	public void checkName() {
		
		if (userService.checkUserByUserName(name)) {
			super.printHTML("true");
		} else {
			super.printHTML("false");
		}
		
	}

	/**
	 * 检查email是否已被注册
	 * 
	 * 直接向html写入纯文本 存在写入true; 否则false;
	 */
	public void checkEmail() {
		
		if (userService.checkUserByEmail(email)) {
			super.printHTML("true");
		} else {
			super.printHTML("false");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserService getUserservice() {
		return userService;
	}

	@Resource(name = "userService")
	public void setUserservice(UserService userservice) {
		this.userService = userservice;
	}
}
