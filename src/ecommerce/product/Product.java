package ecommerce.product;

import java.io.Serializable;

public class Product implements Serializable{

	protected String name;
	protected String brand;
	protected String code;
	protected String category;
	protected float price;
	protected String img;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public void setDiscount(int discount){
	
	}

	public int getDiscount(){
		return 0;
	}

	public float getFinalPrice(){
		return price - price * getDiscount() / 100;
	}
	
	public float getTotal(int n){
		return getFinalPrice() * n;
	}
	
	
}
