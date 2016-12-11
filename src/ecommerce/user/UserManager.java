package ecommerce.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class UserManager {
	
	//Users configuration file name
	private static final String usersFileName = ".e-users";
	
	//Users map index by password hash
	private static HashMap<String, User> users = null; 
	
	private UserManager(){
		
	}
	
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
			}
		}
		else{
			System.out.println("Users file not found");

			users = new HashMap<String, User>();			
		}
		return false;
	}
	
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
	
	public static void addUser(User newUser){
		users.put(newUser.getName() + newUser.getPasswordHash(), newUser);
	}
	
	public static void addAdmin(User newUser){
		newUser.setAdmin(true);
		users.put(newUser.getName() + newUser.getPasswordHash(), newUser);
	}
	
	public static int getUserCount(){
		return users == null ? 0 : users.size();
	}
	
	public static boolean userExists(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(name + password.hashCode());
		}
		
		return user != null;
	}
	
	public static User getUser(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(name + password.hashCode());
			
			return user;
		}
		
		return null;
	}

}
