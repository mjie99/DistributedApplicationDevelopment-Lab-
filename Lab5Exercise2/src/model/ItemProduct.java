package model;

import java.io.Serializable;

public class ItemProduct implements Serializable {
	
	private int itemProductId;
	private String name;
	private float price;
	
	// For product id
	public int getItemProductId() {
		return itemProductId;
	}
	public void setItemProductId(int itemProductId) {
		this.itemProductId = itemProductId;
	}
	
	// For product name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// For product price
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	

}
