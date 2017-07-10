package org.yang.bean;

import java.util.List;

/**
 * t_orderDetail �־û� ��
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
	 * �����еĵ��� ��Ʒϸ��
	 * 
	 * @param orderId
	 *            ����id
	 * @param goodsId
	 *            ��Ʒid
	 * @param goodsname
	 *            ��Ʒ����
	 * @param goodsinfo
	 *            ��Ʒ����
	 * @param goodsprice
	 *            ��Ʒ�۸�
	 * @param num
	 *            ��Ʒ����
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
