package org.yang.web.struts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ����:����ûȨ�޵�action����
 * 
 * @author Administrator
 * 
 */
public class ManagerPermissionInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		try {
			System.out.println("ͨ�� ManagerPermissionInterceptor ");
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpSession hs = req.getSession();
			Object m = hs.getAttribute("managerInformation");
			Object flag = req.getAttribute("flag");
			if (m == null) {
				req.setAttribute("errorMsg", "����Ȩ����! ���ǹ���Ա���ȵ�¼");
				
				if( flag != null){
					req.setAttribute("flag", flag);
				}
				return "login";
			}
			
			
			return arg0.invoke();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "global-exception";
	}

}
