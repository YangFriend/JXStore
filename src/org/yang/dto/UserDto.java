package org.yang.dto;

import org.yang.bean.User;

public class UserDto {

	private String userName;
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	private String password;
	private String password2;
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public User getUser() {

		User u = new User();
		u.setUserName(userName);
		u.setUserPassword(password);
		u.setEmail(email);
		return u;
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", newPassword2="
				+ newPassword2 + ", password=" + password + ", password2="
				+ password2 + ", email=" + email + "]";
	}

}
