package ecommerce.product.filter;

import ecommerce.product.Product;

public class DiscountFilter extends ProductFilter{
		
	@Override
	public boolean match(Product product) {
		return product.getDiscount() > 0;
	}

	
	
}
