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
public class UserPermissionInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		try {
			System.out.println("ͨ�� UserPermissionInterceptor ");
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpSession hs = req.getSession();
			Object obj = hs.getAttribute("userInformation");// User ��ת�� ���
															// ֻ���ж�һ��
			if (obj == null) {
				req.setAttribute("errorMsg", "�����¼���ٽ��в���");
				return "error";
			}
			return arg0.invoke();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "global-exception";
	}

}
