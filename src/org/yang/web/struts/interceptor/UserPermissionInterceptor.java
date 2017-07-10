package org.yang.web.struts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 功能:拦截没权限的action操作
 * 
 * @author Administrator
 * 
 */
public class UserPermissionInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		try {
			System.out.println("通过 UserPermissionInterceptor ");
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpSession hs = req.getSession();
			Object obj = hs.getAttribute("userInformation");// User 不转了 随便
															// 只是判断一下
			if (obj == null) {
				req.setAttribute("errorMsg", "★请登录后再进行操作");
				return "error";
			}
			return arg0.invoke();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "global-exception";
	}

}
