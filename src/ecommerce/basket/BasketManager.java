package ecommerce.basket;

import java.util.HashMap;

import ecommerce.product.Product;

public class BasketManager {

	private static HashMap<Product, Integer> basket = new HashMap<Product, Integer>();
	
	private BasketManager() {
	
	}
	
	public static void addProduct(Product product, int qt){
		System.out.println("Added " + product.getName() + " to basket qt: " + qt);
		basket.put(product, qt);
	}
	
	public static void removeProduct(Product product){
		basket.remove(product);
	}
	
	public static void clear(){
		basket.clear();
	}
	
}
