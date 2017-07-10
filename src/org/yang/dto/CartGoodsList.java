package org.yang.dto;

public class CartGoodsList {

	private long id;
	private String name;
	private double price;
	private String image;
	private int num;
	private double total;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartGoodsList [id=" + id + ", name=" + name + ", price="
				+ price + ", image=" + image + ", num=" + num + ", total="
				+ total + "]";
	}

}
