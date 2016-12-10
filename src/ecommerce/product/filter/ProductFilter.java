package ecommerce.product.filter;

import ecommerce.product.Product;

public abstract class ProductFilter {

	public abstract boolean match(Product product);
	
}
