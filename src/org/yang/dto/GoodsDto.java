package org.yang.dto;

import java.io.File;

import org.yang.bean.Goods;


public class GoodsDto {
	

	private Long id;
	private String name;
	private Double price;
	private File image;
	private String info;
	private String gameType;
	private Integer status;
	
	
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
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Goods castGoodsObjPartField() {
		Goods g = new Goods();
		g.setGameType(this.getGameType());
		g.setInfo(this.info);
		g.setName(this.name);
		g.setPrice(this.price);
		g.setStatus(this.status);
		return g;
	}
	
	@Override
	public String toString() {
		return "GoodsDto [id=" + id + ", name=" + name + ", price=" + price
				+ ", image=" + image + ", info=" + info + ", gameType="
				+ gameType + ", status=" + status + "]";
	}

}
