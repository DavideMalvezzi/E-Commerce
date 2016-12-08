package ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Configuration implements Serializable{

	private static final String configFileName = ".e-config";
	
	private static Configuration instance = null;
	
	public String productFilePath = System.getProperty("user.home") + File.separator + ".e-products";
	
	private Configuration() {
		
	}
	
	public static Configuration getInstance(){
		return instance;
	}
	
	public static void load(){
		String homeFolder = System.getProperty("user.home");
		File configFile = new File(homeFolder + File.separator + configFileName);
		
		//Check if the file exists
		if(configFile.exists()){
			System.out.println("Config file found");
			try {
				//Load the serialized users hash map
				FileInputStream fis = new FileInputStream(configFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				instance = (Configuration)ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Config file not found");
			instance = new Configuration();
		}
	}
	
	public static void save(){
		//Get the users configuration file path
		String homeFolder = System.getProperty("user.home");
		File usersFile = new File(homeFolder + File.separator + configFileName);
		
		try {
			//Save the serialized users hash map
			FileOutputStream fos = new FileOutputStream(usersFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
			oos.flush();
			oos.close();
			
			System.out.println("Config file saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
