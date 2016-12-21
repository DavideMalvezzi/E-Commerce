package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Classe base per la creazione di filtri ricerca dei prodotti
 * @author Davide Malvezzi
 */
public abstract class ProductFilter {

	/**
	 * @brief Funzione astratta da implementare nelle specializzazioni.
	 * La funzione ritorna true se rispetta le condizioni del filtro di ricerca altrimenti false.
	 * @param product Prodotto da controllare
	 * @return true se il prodotto rispetta le condizione del filtro
	 */
	public abstract boolean match(Product product);
	
}
