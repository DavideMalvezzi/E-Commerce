package ecommerce.user;

import java.io.Serializable;

/**
 * @author Davide
 * @file
 * Classe rapprentante un utente che può essere anche amministratore.
 * La classe è serializzabile per il salvataggio su file.
 */
public class User implements Serializable{
	
	/**
	 * @var name
	 * Nome utente
	 */
	private String name;
	
	/**
	 * @var passwordHash
	 * Hash della password
	 */
	private int passwordHash;
	
	/**
	 * @var isAdmin
	 * Valore booleano che indica se l'utente è amministratore
	 */
	private boolean isAdmin;
	
	/**
	 * @brief Costruttore
	 * @param name Nome utente
	 * @param password Password
	 */
	public User(String name, String password) {
		this(name, password, false);
	}
	
	/**
	 * @brief Costruttore
	 * @param name Nome utente
	 * @param password Password
	 * @param isAdmin Indica se l'utente deve essere o no un amministratore
	 */
	public User(String name, String password, boolean isAdmin) {
		this.name = name;
		this.passwordHash = password.hashCode();
		this.isAdmin = isAdmin;
	} 
	
	/**
	 * @brief Ritorna il nome dell'utente
	 * @return Nome utente
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @brief Ritorna l'hash della password dell'utente
	 * @return Hash password utente
	 */
	public int getPasswordHash() {
		return passwordHash;
	}
	
	/**
	 * @brief Ritorna se l'utente è amministratore
	 * @return Flag
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @brief Setta se l'utente è amministratore o no
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
