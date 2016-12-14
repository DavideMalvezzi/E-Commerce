package ecommerce.product.filter;

import ecommerce.product.Product;
import ecommerce.product.Product3x2;


/**
 * @author Davide
 * @file
 * Filtro di ricerca prodotti in base all'offerta 3x2
 */
public class ThreexTwoFilter extends ProductFilter{

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prodotto è un'istanza di Product3x2
	 */
	@Override
	public boolean match(Product product) {
		return product instanceof Product3x2;
	}

	
	
}
