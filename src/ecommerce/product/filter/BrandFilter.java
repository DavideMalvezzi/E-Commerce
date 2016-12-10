package ecommerce.product.filter;

import ecommerce.product.Product;

public class BrandFilter extends ProductFilter{

	public static final String ALL_BRANDS = "Tutte";
	
	private String brand;
	
	
	public BrandFilter(String brand) {
		this.brand = brand;
	}

	@Override
	public boolean match(Product product) {
		return brand.equals(ALL_BRANDS) || product.getBrand().equals(brand);
	}

	
	
}
