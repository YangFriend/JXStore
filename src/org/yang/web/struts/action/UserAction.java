package org.yang.web.struts.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Order;
import org.yang.bean.OrderGoodsDetail;
import org.yang.bean.User;
import org.yang.dto.UserDto;
import org.yang.service.impl.UserService;
import org.yang.util.Tool;


@Component("userAction")
@Scope("prototype")
public class UserAction extends SupportAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userservice;
	
	private UserDto userDto;
	
	private String userName;
	private String password;
	private String rememberMe;
	private String flag;
	private long orderId;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upPassword() throws Exception {
		User user = (User) super.session.get("userInformation");

		String userPassword = user.getUserPassword().trim();
		String valiPassword = Tool.encryptionString(userDto.getOldPassword());
		String newPassword = userDto.getNewPassword();

		if (valiPassword.equals(userPassword)) {
			this.userservice.updatePassword(user, newPassword);
			super.request.put("errorMsg", "�����������޸�");
			// ֪ͨ�ͻ����Ƴ�cookie
			super.request.put("rmCookie", "ok");
			return "info";
		}
		super.request.put("errorMsg", "������֤ʧ��!");
		return "info";

	}

	/**
	 * ת��User info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String info() throws Exception {
		User user = (User) super.session.get("userInformation");
		user = this.userservice.loadUser(user);
		super.session.put("userInformation", user);
		return "info";
	}

	/**
	 * ע�����û�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String signup() throws Exception {
		if (userservice.regUser(userDto.getUser())) {
			userName = userDto.getUser().getUserName();
			password = userDto.getUser().getUserPassword();
			return login();
		}
		super.request.put("errorMsg", "ע��ʧ��!!!!!");
		return ERROR;
	}

	/**
	 * ��½
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		if (flag != null && flag.equals("turn")) {
			if (cookieLogin()) {
				return SUCCESS;
			}
			return ERROR;
		}

		User u = new User();
		u.setUserName(userName);
		u.setUserPassword(password);
		u = userservice.loadUserByName(u);
		if (u != null) {
			if (u.getStatus() == 1) {
				// ����ֹ��¼
				super.request.put("errorMsg", "�ﱧǸ,���ѱ���ֹ��½.");
				return ERROR;
			}

			super.session.put("userInformation", u);
			if (rememberMe != null) {
				remember();
			}
			return SUCCESS;
		} else {
			super.request.put("name_c", userName);
			super.request.put("errorMsg", "���û������������!");
			return ERROR;
		}
	}

	/**
	 * �ǳ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		super.session.remove("userInformation");
		super.request.put("errorMsg", "�����Ѱ�ȫ�˳� :)");
		return ERROR;
	}

	/**
	 * ��ѯ�����б�
	 * 
	 * @return
	 */
	public String order() {
		List<Order> orderList = this.userservice
				.queryMyOrder((User) super.session.get("userInformation"));
		super.request.put("orderList", orderList);
		return "order";
	}
	
	/**
	 * ��ѯĳ����������
	 * 
	 * @return
	 */
	public String orderDetail() {
		User u = (User) super.session.get("userInformation");
		List<OrderGoodsDetail> ogdList = userservice.queryMyOrderGoodsDetail(u, orderId);
		if(ogdList == null){
			//�Ƿ�����
			return "global-illegal";
		}
		super.request.put("ogdList", ogdList);
		return "orderDetail";
	}
	

	/**
	 * ����cookie��½
	 * 
	 * @param cookies
	 * @return ��½�ɹ�����true;
	 */
	private boolean cookieLogin() {
		boolean isOK = false;
		HttpServletRequest req = ServletActionContext.getRequest();
		Cookie[] cookies = req.getCookies();
		User u = new User();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("name_c")) {
					isOK = true;
					u.setUserName(c.getValue());
				}
				if (c.getName().equals("paw_c")) {
					u.setUserPassword(c.getValue());
				}
			}
		}
		u = userservice.loadUser(u);
		if (u != null) {
			if (u.getStatus() == 1) {
				// ����ֹ��¼
				super.request.put("errorMsg", "�ﱧǸ,���ѱ���ֹ��½.");
				return false;
			}
			super.session.put("userInformation", u);
			return true;
		}
		if (isOK) {
			super.request.put("errorMsg", "���û����˺Ż��������![c]");
		}
		return false;
	}

	/**
	 * cookie���� �û���&����
	 * 
	 */
	private void remember() {
		HttpServletResponse res = ServletActionContext.getResponse();
		Cookie n_c = new Cookie("name_c", userName);
		Cookie p_c = new Cookie("paw_c", Tool.encryptionString(password));
		n_c.setMaxAge(60 * 60 * 24 * 7); // 7��
		p_c.setMaxAge(60 * 60 * 24 * 7);
		res.addCookie(n_c);
		res.addCookie(p_c);
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


}
