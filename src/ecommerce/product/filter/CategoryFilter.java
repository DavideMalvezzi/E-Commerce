package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Filtro di ricerca prodotti in base alla categoria
 * @author Davide Malvezzi
 */
public class CategoryFilter extends ProductFilter{

	/**
	 * @var ALL_CATEGORIES
	 * Stringa jolly per indicare tutte le possibili categorie
	 */
	public static final String ALL_CATEGORIES = "Tutte";
	
	/**
	 * @var brand
	 * Stringa contenente la categoria che si desidera filtrare
	 */
	private String category;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param category Categoria che si desidera filtrare
	 */
	public CategoryFilter(String category) {
		this.category = category;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se la categoria del prodotto è uguale a quella filtrata o se il filtro è impostato su tutte le marche
	 */
	@Override
	public boolean match(Product product) {
		return category.equals(ALL_CATEGORIES) || product.getCategory().equals(category);
	}

	
	
}
