package org.yang.bean;

import java.util.Date;

/**
 * t_goods 持久化类
 * 
 * @author Administrator
 * 
 */
public class Goods {

	private Long id;
	private String name;
	private Double price;
	private String image;
	private String info;
	private String gameType;
	private Date addedDate;
	private Integer status;
	//
	private Integer surplus;
	
	public Goods() {

	}

	/**
	 * 构建一个商品需要的信息
	 * 
	 * @param name
	 * @param price
	 * @param image
	 * @param info
	 * @param gameType
	 * @param addedDate
	 * @param status
	 */
	public Goods(String name, Double price, String image, String info,
			String gameType, Date addedDate, Integer status) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.info = info;
		this.gameType = gameType;
		this.addedDate = addedDate;
		this.status = status;
	}



	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	//

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price
				+ ", image=" + image + ", info=" + info + ", gameType="
				+ gameType + ", addedDate=" + addedDate + ", status=" + status
				+ ", surplus=" + surplus + "]";
	}

}
