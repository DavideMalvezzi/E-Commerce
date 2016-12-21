package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Filtro di ricerca prodotti in base al nome
 * @author Davide Malvezzi
 */
public class NameFilter extends ProductFilter{
	
	/**
	 * @var brand
	 * Stringa contenente il nome che si desidera filtrare
	 */
	private String name;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param name Nome che si desidera filtrare
	 */
	public NameFilter(String name) {
		this.name = name;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il filtro è nullo o se il nome del prodotto contiene il nome cercato
	 */
	@Override
	public boolean match(Product product) {
		return name.isEmpty() || product.getName().contains(name);
	}

}
