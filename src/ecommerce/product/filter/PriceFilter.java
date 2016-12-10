package ecommerce.product.filter;

import ecommerce.product.Product;

public class PriceFilter extends ProductFilter{

	private int minPrice, maxPrice;
	
	
	public PriceFilter(int minPrice, int maxPrice) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	@Override
	public boolean match(Product product) {
		return product.getFinalPrice() >= minPrice && product.getFinalPrice() <= maxPrice;
	}

	
	
}
