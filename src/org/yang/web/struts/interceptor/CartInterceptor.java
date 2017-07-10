package org.yang.web.struts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.yang.bean.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class CartInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7888420423475339885L;

	@Override
	protected String doIntercept(ActionInvocation invAction) throws Exception {
		// Auto-generated method stub
		try {
			System.out.println("通过 CartInterceptor ");
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpSession hs = req.getSession();
			User u = (User) hs.getAttribute("userInformation");
			if (u == null) {
				req.setAttribute("errorMsg", "★请登录后再进行操作");
				return "login";
			}
			return invAction.invoke();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "global-exception";
	}

}
