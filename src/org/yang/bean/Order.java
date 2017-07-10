package org.yang.bean;

/**
 * t_order 持久化 类
 * 
 * @author Administrator
 * 
 */
public class Order {

	private Long id;
	private User user;
	private java.util.Date orderDate;
	private double allValue;
	private double actPrice;
	private Integer goodsNum;
	private Integer accountNum;

	public Order() {

	}

	/**
	 * 根据User创建订单
	 * 
	 * @param user
	 */
	public Order(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getAllValue() {
		return allValue;
	}

	public void setAllValue(double allValue) {
		this.allValue = allValue;
	}

	public double getActPrice() {
		return actPrice;
	}

	public void setActPrice(double actPrice) {
		this.actPrice = actPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Integer getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}

}
