package ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Davide
 * @file
 * Classe contentente metodi statici per la gestione del singleton rappresentante le configurazioni del programma.
 * La classe è serializzabile per il salvataggio su file.  
 */
public class Configuration implements Serializable{

	/**
	 * @var configFileName
	 * Costante contenente il nome del file di configurazione
	 */
	private static final String configFileName = ".e-config";
	
	/**
	 * @var instance
	 * Istanza delle configurazioni
	 */
	private static Configuration instance = null;
	
	/**
	 * @var productFilePath
	 * Path del file dei prodotti
	 */
	public String productFilePath = System.getProperty("user.home") + File.separator + ".e-products";
	
	
	/**
	 * @brief Costruttore privato per evitare la creazione di un'istanza
	 */
	private Configuration() {
		
	}
	
	/**
	 * @brief Ritorna l'istanza delle configurazioni
	 * @return
	 */
	public static Configuration getInstance(){
		return instance;
	}
	
	/**
	 * @brief Carica le configurazioni da file
	 */
	public static void load(){
		String homeFolder = System.getProperty("user.home");
		File configFile = new File(homeFolder + File.separator + configFileName);
		
		//Check if the file exists
		if(configFile.exists()){
			System.out.println("Config file found at " + configFile.getAbsolutePath());
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
			save();
		}
	}
	
	/**
	 * @brief Salva su file le configurazioni
	 */
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
