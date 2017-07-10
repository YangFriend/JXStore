package org.yang.bean;

/**
 * t_manager ≥÷æ√ªØ¿‡
 * 
 * @author Administrator
 * 
 */
public class Manager {
	Long id;
	String name;
	String password;
	Integer permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

}
