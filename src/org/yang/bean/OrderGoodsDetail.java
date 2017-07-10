package org.yang.bean;

import java.util.List;

/**
 * t_orderDetail 持久化 类
 * 
 * @author Administrator
 * 
 */
public class OrderGoodsDetail {

	private Long id;
	private Long orderId;
	private Long goodsId;
	private String goodsname;
	private String goodsinfo;
	private double goodsprice;
	private Integer num;
	private List<OrderAccountDetail> accounts;

	public OrderGoodsDetail() {

	}

	/**
	 * 订单中的单个 商品细节
	 * 
	 * @param orderId
	 *            订单id
	 * @param goodsId
	 *            商品id
	 * @param goodsname
	 *            商品名称
	 * @param goodsinfo
	 *            商品介绍
	 * @param goodsprice
	 *            商品价格
	 * @param num
	 *            商品数量
	 */
	public OrderGoodsDetail(Long orderId, Long goodsId, String goodsname,
			String goodsinfo, double goodsprice, Integer num) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.goodsname = goodsname;
		this.goodsinfo = goodsinfo;
		this.goodsprice = goodsprice;
		this.num = num;
	}

	public List<OrderAccountDetail> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<OrderAccountDetail> accounts) {
		this.accounts = accounts;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsinfo() {
		return goodsinfo;
	}

	public void setGoodsinfo(String goodsinfo) {
		this.goodsinfo = goodsinfo;
	}

	public double getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
