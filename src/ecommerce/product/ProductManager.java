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

/**
 * Classe contentente metodi statici per la gestione del singleton rappresentante l'insieme dei prodotti.
 * @author Davide Malvezzi
 */
public class ProductManager {
	
	/**
	 * @var isDirty
	 * Valore booleano che indica se il carrello è stato modificato dall'ultima visualizzazione
	 */
	private static boolean isDirty = true;
	
	/**
	 * @var products
	 * Vettore che contiene le istante di tutti i prodotti
	 */	
	private static Vector<Product> products = null;
	
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
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
				isDirty = true;
				ois.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Products file not found");
			products = new Vector<Product>();
			isDirty = true;
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
		isDirty = true;
	}
	
	public static void replaceProduct(Product oldP, Product newP){
		products.set(products.indexOf(oldP), newP);
		isDirty = true;
	}
	
	public static void removeProduct(Product p){
		products.remove(p);
		isDirty = true;
	}
	
	public static void removeProduct(int index){
		products.remove(index);
		isDirty = true;
	}
	
	public static int getProductCount(){
		return products == null ? 0 : products.size();
	}
	
	public static int getProductIndex(Product p){
		return products.indexOf(p);
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
				
		return categories;
	}
	
	public static Vector<String> getProductBrandList(){
		Vector<String> brands = new Vector<String>();
		
		for(int i = 0; i < products.size(); i++){
			if(!brands.contains(products.get(i).getBrand())){
				brands.add(products.get(i).getBrand());
			}
		}
		
		Collections.sort(brands);
				
		return brands;
	}
	
	public static boolean isDirty(){
		return isDirty;
	}

	
	public static void setDirty(boolean dirty){
		isDirty = dirty;
	}
}

