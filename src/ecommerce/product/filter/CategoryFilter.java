package ecommerce.product.filter;

import ecommerce.product.Product;

public class CategoryFilter extends ProductFilter{

	public static final String CATEGORY_WILDCARD = "Tutte";
	
	private String category;
	
	
	public CategoryFilter(String category) {
		this.category = category;
	}

	@Override
	public boolean match(Product product) {
		return category.equals(CATEGORY_WILDCARD) || product.getCategory().equals(category);
	}

	
	
}
