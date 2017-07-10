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
			super.request.put("errorMsg", "您的密码已修改");
			// 通知客户端移除cookie
			super.request.put("rmCookie", "ok");
			return "info";
		}
		super.request.put("errorMsg", "密码验证失败!");
		return "info";

	}

	/**
	 * 转到User info
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
	 * 注册新用户
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
		super.request.put("errorMsg", "注册失败!!!!!");
		return ERROR;
	}

	/**
	 * 登陆
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
				// 被禁止登录
				super.request.put("errorMsg", "★抱歉,你已被禁止登陆.");
				return ERROR;
			}

			super.session.put("userInformation", u);
			if (rememberMe != null) {
				remember();
			}
			return SUCCESS;
		} else {
			super.request.put("name_c", userName);
			super.request.put("errorMsg", "★用户名或密码错误!");
			return ERROR;
		}
	}

	/**
	 * 登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		super.session.remove("userInformation");
		super.request.put("errorMsg", "★你已安全退出 :)");
		return ERROR;
	}

	/**
	 * 查询订单列表
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
	 * 查询某个订单详情
	 * 
	 * @return
	 */
	public String orderDetail() {
		User u = (User) super.session.get("userInformation");
		List<OrderGoodsDetail> ogdList = userservice.queryMyOrderGoodsDetail(u, orderId);
		if(ogdList == null){
			//非法访问
			return "global-illegal";
		}
		super.request.put("ogdList", ogdList);
		return "orderDetail";
	}
	

	/**
	 * 尝试cookie登陆
	 * 
	 * @param cookies
	 * @return 登陆成功返回true;
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
				// 被禁止登录
				super.request.put("errorMsg", "★抱歉,你已被禁止登陆.");
				return false;
			}
			super.session.put("userInformation", u);
			return true;
		}
		if (isOK) {
			super.request.put("errorMsg", "★用户名账号或密码错误![c]");
		}
		return false;
	}

	/**
	 * cookie保存 用户名&密码
	 * 
	 */
	private void remember() {
		HttpServletResponse res = ServletActionContext.getResponse();
		Cookie n_c = new Cookie("name_c", userName);
		Cookie p_c = new Cookie("paw_c", Tool.encryptionString(password));
		n_c.setMaxAge(60 * 60 * 24 * 7); // 7天
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
