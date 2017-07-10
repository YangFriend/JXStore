package org.yang.web.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("defaultAction")
@Scope("prototype")
public class DefaultAction extends ActionSupport {

	public DefaultAction() {
		System.out.println("DefaultAction");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("DefaultAction 404 url:" + request.getContextPath());
		return "global-404";
	}

}
