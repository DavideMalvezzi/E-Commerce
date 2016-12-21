package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Filtro di ricerca prodotti in base ad una fascia di prezzi
 * @author Davide Malvezzi
 */
public class PriceFilter extends ProductFilter{

	/**
	 * @var minPrice
	 * Prezzo minimo
	 */
	private int minPrice;
	
	/**
	 * @var maxPrice
	 * Prezzo massimo
	 */
	private int maxPrice;
	
	/**
	 * @brief Costruttore con parametri
	 * @param minPrice Prezzo minimo
	 * @param maxPrice Prezzo massimo
	 */
	public PriceFilter(int minPrice, int maxPrice) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prezzo del prodotto rientra nella fascia di prezzi del filtro
	 */
	@Override
	public boolean match(Product product) {
		return product.getFinalPrice() >= minPrice && product.getFinalPrice() <= maxPrice;
	}

	
	
}
