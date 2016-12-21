package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Filtro di ricerca prodotti in base allo sconto
 * @author Davide Malvezzi
 */
public class DiscountFilter extends ProductFilter{
	
	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prodotto presenta uno sconto
	 */
	@Override
	public boolean match(Product product) {
		return product.getDiscount() > 0;
	}

	
	
}
