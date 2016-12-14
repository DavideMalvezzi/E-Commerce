package ecommerce.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


/**
 * @author Davide
 * @file
 * Classe contentente metodi statici per la gestione del singleton rappresentante l'insieme degli utenti 
 */
public class UserManager {
	
	/**
	 * @var usersFileName
	 * Costante contenente il nome del file degli utenti
	 */
	private static final String usersFileName = ".e-users";
	
	/**
	 * @var users
	 * Hashmap che associa una stringa univoca ad ogni utente
	 */
	private static HashMap<String, User> users = null; 
	
	
	/**
	 * @brief Costruttutore privare per evitare la creazione di un'istanza
	 */
	private UserManager(){
		
	}
	
	/**
	 * @brief Carica il file degli utenti
	 * @return true se il caricamento è andato a buon fine
	 */
	public static boolean loadUsers(){
		//Get the users configuration file path
		String homeFolder = System.getProperty("user.home");
		File usersFile = new File(homeFolder + File.separator + usersFileName);
		//System.out.println(usersFile.getAbsolutePath());
		
		//Check if the file exists
		if(usersFile.exists()){
			System.out.println("Users file found at " + usersFile.getAbsolutePath());
			try {				
				//Load the serialized users hash map
				FileInputStream fis = new FileInputStream(usersFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				users = (HashMap<String, User>)ois.readObject();
				ois.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				users = new HashMap<String, User>();			
			}
		}
		else{
			System.out.println("Users file not found");

			users = new HashMap<String, User>();			
		}
		return false;
	}
	
	/**
	 * @brief Salva il file degli utenti
	 * @return true se il salvataggio è andato a buon fine
	 */
	public static boolean saveUsers(){
		//Get the users configuration file path
		String homeFolder = System.getProperty("user.home");
		File usersFile = new File(homeFolder + File.separator + usersFileName);
		
		try {
			//Save the serialized users hash map
			FileOutputStream fos = new FileOutputStream(usersFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.flush();
			oos.close();
			
			System.out.println("Users file saved");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * @brief Aggiunge un nuovo utente
	 */
	public static void addUser(User newUser){
		users.put(newUser.getName() + newUser.getPasswordHash(), newUser);
	}
	
	/**
	 * @brief Aggiunge un nuovo amministratore
	 */
	public static void addAdmin(User newUser){
		newUser.setAdmin(true);
		users.put(newUser.getName() + newUser.getPasswordHash(), newUser);
	}
	
	/**
	 * @brief Ritorna il numero degli utenti
	 * @return Numero utenti
	 */
	public static int getUserCount(){
		return users == null ? 0 : users.size();
	}
	
	/**
	 * @brief Controlla se un utente esiste
	 * @param name Nome utente
	 * @param password Password utente
	 * @return true se l'utente esiste
	 */
	public static boolean userExists(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(name + password.hashCode());
		}
		
		return user != null;
	}
	
	/**
	 * @brief Ritorna se esiste l'istanza di un utente
	 * @param name Nome dell'utente da cercare
	 * @param password Password dell'utente da cercare
	 * @return Istanza dell'utente se esiste altrimenti null
	 */
	public static User getUser(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(name + password.hashCode());
			
			return user;
		}
		
		return null;
	}

}
