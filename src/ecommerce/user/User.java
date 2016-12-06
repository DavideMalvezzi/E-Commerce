package ecommerce.user;

import java.io.Serializable;

public class User implements Serializable{
	
	private String name;
	private int passwordHash;
	private boolean isAdmin;
	
	public User(String name, String password) {
		this(name, password, false);
	}
	
	public User(String name, String password, boolean isAdmin) {
		this.name = name;
		this.passwordHash = password.hashCode();
		this.isAdmin = isAdmin;
	} 
	
	public String getName() {
		return name;
	}
	
	public int getPasswordHash() {
		return passwordHash;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
}
