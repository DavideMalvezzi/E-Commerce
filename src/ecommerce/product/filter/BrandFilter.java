package ecommerce.product.filter;

import ecommerce.product.Product;

/**
 * Filtro di ricerca prodotti in base alla marca
 * @author Davide Malvezzi
 */
public class BrandFilter extends ProductFilter{

	/**
	 * @var ALL_BRANDS
	 * Stringa jolly per indicare tutte le possibili marche
	 */
	public static final String ALL_BRANDS = "Tutte";
	
	/**
	 * @var brand
	 * Stringa contenente la marca che si desidera filtrare
	 */
	private String brand;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param brand Marca che si desidera filtrare
	 */
	public BrandFilter(String brand) {
		this.brand = brand;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se la marca del prodotto è uguale a quella filtrata o se il filtro è impostato su tutte le marche
	 */
	@Override
	public boolean match(Product product) {
		return brand.equals(ALL_BRANDS) || product.getBrand().equals(brand);
	}

	
	
}
