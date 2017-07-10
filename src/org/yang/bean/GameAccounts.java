package org.yang.bean;

import java.util.Date;

/**
 * t_gameAccounts 持久化类
 * 
 * @author Administrator
 * 
 */
public class GameAccounts {
	private Long id;
	private Long goodsId;
	private String ter;
	private String accountName;
	private String password;
	private Integer version;
	private Date regDate;
	private Integer sell = 0;

	public GameAccounts() {

	}

	/**
	 * 构建一个游戏账号所必须的信息
	 * 
	 * @param goodsId
	 * @param ter
	 * @param accountName
	 * @param password
	 * @param regDate
	 */
	public GameAccounts(Long goodsId, String ter, String accountName,
			String password, Date regDate) {
		this.goodsId = goodsId;
		this.ter = ter;
		this.accountName = accountName;
		this.password = password;
		this.regDate = regDate;
		this.sell = 0;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getTer() {
		return ter;
	}

	public void setTer(String ter) {
		this.ter = ter;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSell() {
		return sell;
	}

	public void setSell(Integer sell) {
		this.sell = sell;
	}

	@Override
	public String toString() {
		return "GameAccounts [id=" + id + ", goodsId=" + goodsId + ", ter="
				+ ter + ", regDate=" + regDate + ", accountName=" + accountName
				+ ", password=" + password + ", version=" + version + ", sell="
				+ sell + "]";
	}

}
