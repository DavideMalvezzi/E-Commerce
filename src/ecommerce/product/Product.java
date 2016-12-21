package ecommerce.product;

import java.io.Serializable;

/**
 * Classe rappresentante un singolo prodotto senza sconti e offerte.
 * La classe è serializzabile per il salvataggio su file.
 * @author Davide Malvezzi
 */
public class Product implements Serializable{

	/**
	 * @var name
	 * Nome del prodotto
	 */
	protected String name;
	
	/**
	 * @var brand
	 * Marca del prodotto
	 */
	protected String brand;
	
	/**
	 * @var code
	 * Codice del prodotto
	 */
	protected String code;
	
	/**
	 * @var category
	 * Categoria del prodotto
	 */
	protected String category;
	
	/**
	 * @var price
	 * Prezzo del prodotto
	 */
	protected float price;
	
	/**
	 * @var img
	 * Path dell'immagine del prodotto
	 */
	protected String img;
	
	
	/**
	 * @brief Ritorna il nome del prodotto
	 * @return Nome prodotto
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @brief Setta il nome del prodotto
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @brief Ritorna la marca del prodotto
	 * @return Marca prodotto
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * @brief Setta la marca del prodotto
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * @brief Ritorna il codice del prodotto
	 * @return Codice prodotto
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @brief Setta il codice del prodotto
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @brief Ritorna la categoria del prodotto
	 * @return Categoria prodotto
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @brief Setta la categoria del prodotto
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * @brief Ritorna il prezzo del prodotto
	 * @return Prezzo prodotto
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * @brief Setta il prezzo del prodotto
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * @brief Ritorna il path dell'immagine del prodotto
	 * @return Path immagine prodotto
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * @brief Setta il path dell'immagine del prodotto
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * @brief Setta lo sconto del prodotto.
	 * Il metodo è presente per compatibilità nel caso di up-casting ma non è implementato
	 */
	public void setDiscount(int discount){
	
	}

	/**
	 * @brief Ritorna lo sconto del prodotto
	 * Il metodo è presente per compatibilità nel caso di up-casting ma ritorna sempre 0
	 * @return Sconto del prodotto
	 */
	public int getDiscount(){
		return 0;
	}

	/**
	 * @brief Ritorna il prezzo scontato del prodotto
	 * @return Prezzo scontato
	 */
	public float getFinalPrice(){
		return price - price * getDiscount() / 100;
	}
	
	/**
	 * @brief Ritorna il totale dell'acquisto di n prodotti
	 * @return Totale acquisto
	 */
	public float getTotal(int n){
		return getFinalPrice() * n;
	}
	
	
}
