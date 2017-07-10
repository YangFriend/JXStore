package org.yang.web.struts.action;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class SupportAction extends ActionSupport implements
		RequestAware, SessionAware {

	private static final long serialVersionUID = 123L;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	public SupportAction() {
	};

	public void setApplication(Map<String, Object> arg0) {
		// Auto-generated method stub
		this.application = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		// Auto-generated method stub
		this.session = arg0;
	}

	public void setRequest(Map<String, Object> arg0) {
		// Auto-generated method stub
		this.request = arg0;
	}
	
	
	
	/**
	 * 直接在HTML流中写入
	 * 
	 * @param context
	 */
	protected void printHTML(String context) {
		//
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(context);
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
