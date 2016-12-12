package ecommerce.basket;

import java.util.HashMap;

import ecommerce.product.Product;

public class BasketManager {

	private static boolean isDirty = true;
	
	private static HashMap<Product, Integer> basket = new HashMap<Product, Integer>();
	
	private BasketManager() {
	
	}
	
	public static void addProduct(Product product, int qt){
		basket.put(product, qt);
		isDirty = true;
	}
	
	public static void removeProduct(Product product){
		basket.remove(product);
		isDirty = true;
	}
	
	public static void clear(){
		basket.clear();
		isDirty = true;
	}
	
	public static int getCount(){
		return basket.size();
	}
	
	public static boolean isDirty(){
		return isDirty;
	}
	
	public static void setDirty(boolean dirty){
		isDirty = dirty;
	}
	
	public static Product getProduct(int index){
		return (Product)basket.keySet().toArray()[index];
	}
	
	public static int getProductQuantity(int index){
		return (int)basket.values().toArray()[index];
	}
	
}
