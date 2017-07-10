package org.yang.bean;

import java.util.Date;

/**
 * t_user ≥÷æ√ªØ ¿‡
 * 
 * 
 * 
 */
public class User {

	private Long id;
	private String userName;
	private String userPassword;
	private Date regDate;
	private Double money;
	private String email;
	private String phone;
	private Integer gread;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGread() {
		return gread;
	}

	public void setGread(Integer gread) {
		this.gread = gread;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword="
				+ userPassword + ", regDate=" + regDate + ", money=" + money
				+ ", email=" + email + ", phone=" + phone + ", gread=" + gread
				+ ", status=" + status + "]";
	}

}
