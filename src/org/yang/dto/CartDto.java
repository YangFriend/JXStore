package org.yang.dto;

import java.util.List;

import org.yang.util.Tool;

public class CartDto {

	List<CartGoodsList> goodsL;
	private String couponCode;
	private double couponValue;
	private double allValue;
	private double actPrice;
	private double userMoney;
	private String tips;

	public List<CartGoodsList> getGoodsL() {
		return goodsL;
	}

	public void setGoodsL(List<CartGoodsList> goodsL) {
		this.goodsL = goodsL;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getAllValue() {
		return Tool.formatDouble(allValue, 2) ;
	}

	public void setAllValue(double allValue) {
		this.allValue = allValue;
	}

	public double getCouponValue() {
		return Tool.formatDouble(couponValue, 2);
	}

	public void setCouponValue(double couponValue) {
		this.couponValue = couponValue;
	}

	public double getActPrice() {
		return Tool.formatDouble(actPrice, 2);
	}

	public void setActPrice(double actPrice) {
		this.actPrice = actPrice;
	}

	public double getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(double userMoney) {
		this.userMoney = userMoney;
	}

	@Override
	public String toString() {
		return "CartDto [goodsL=" + goodsL + ", couponCode=" + couponCode
				+ ", couponValue=" + couponValue + ", allValue=" + allValue
				+ ", actPrice=" + actPrice + ", userMoney=" + userMoney + ", ";
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}
