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
	private static HashMap<Integer, User> users = null; 
	
	public static void loadUsers(){
		//Get the users configuration file path
		String homeFolder = System.getProperty("user.home");
		File usersFile = new File(homeFolder + File.separator + usersFileName);
		//System.out.println(usersFile.getAbsolutePath());
		
		//Check if the file exists
		if(usersFile.exists()){
			try {
				//Load the serialized users hash map
				FileInputStream fis = new FileInputStream(usersFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				users = (HashMap<Integer, User>)ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			users = new HashMap<Integer, User>();
		}
	}
	
	public static void saveUsers(){
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addUser(String name, String password){
		User newUser = new User(name, password);
		users.put(newUser.getPasswordHash(), newUser);
	}
	
	public static void addAdmin(String name, String password){
		User newUser = new User(name, password, true);
		users.put(newUser.getPasswordHash(), newUser);
	}
	
	public static int getUserCount(){
		return users == null ? 0 : users.size();
	}
	
	public static boolean userExists(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(password.hashCode());
		}
		
		return user != null && user.getName().equals(name);
	}
	
	public static User getUser(String name, String password){
		User user = null;
		
		if(users != null){
			user = users.get(password.hashCode());
			if(user.getName().equals(name)){
				return user;
			}
		}
		
		return null;
	}

}
