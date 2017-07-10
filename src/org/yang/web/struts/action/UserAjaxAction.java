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
	 * ����û����Ƿ��ѱ�ע��
	 * 
	 * ֱ����htmlд�봿�ı� ����д��true; ����false;
	 */
	public void checkName() {
		
		if (userService.checkUserByUserName(name)) {
			super.printHTML("true");
		} else {
			super.printHTML("false");
		}
		
	}

	/**
	 * ���email�Ƿ��ѱ�ע��
	 * 
	 * ֱ����htmlд�봿�ı� ����д��true; ����false;
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
