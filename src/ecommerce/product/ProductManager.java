package ecommerce.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import ecommerce.Configuration;


public class ProductManager {
	
	//Products vector
	private static Vector<Product> products = null;
	
	private ProductManager() {
	
	}
	
	public static boolean loadProducts(){
		//Get the products configuration file path
		File productFile = new File(Configuration.getInstance().productFilePath);
		
		//Check if the file exists
		if(productFile.exists()){
			System.out.println("Products file found at " + productFile.getAbsolutePath());
			try {
				//Load the serialized users hash map
				FileInputStream fis = new FileInputStream(productFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				products = (Vector<Product>)ois.readObject();
				ois.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Products file not found");
			products = new Vector<Product>();
		}
		return false;
	}
	
	public static boolean saveProducts(){
		//Get the products configuration file path
		File usersFile = new File(Configuration.getInstance().productFilePath);
		
		try {
			//Save the serialized products vector
			FileOutputStream fos = new FileOutputStream(usersFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(products);
			oos.flush();
			oos.close();
			
			System.out.println("Products file saved");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static void addProduct(Product p){
		products.add(p);
	}
	
	public static void replaceProduct(Product oldP, Product newP){
		products.set(products.indexOf(oldP), newP);
	}
	
	public static void removeProduct(Product p){
		products.remove(p);
	}
	
	public static void removeProduct(int index){
		products.remove(index);
	}
	
	public static int getProductCount(){
		return products == null ? 0 : products.size();
	}
	
	public static Product getProduct(int index){
		if(index < products.size()){
			return products.get(index);
		}
		return null;
	}
	
	public static Vector<String> getProductCategoryList(){
		Vector<String> categories = new Vector<String>();
		
		for(int i = 0; i < products.size(); i++){
			if(!categories.contains(products.get(i).getCategory())){
				categories.add(products.get(i).getCategory());
			}
		}
		
		Collections.sort(categories);
		
		categories.add(0, "Tutte");
		
		return categories;
	}
	
}
