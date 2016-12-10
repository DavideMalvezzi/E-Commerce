package ecommerce.product.filter;

import ecommerce.product.Product;
import ecommerce.product.Product3x2;

public class ThreexTwoFilter extends ProductFilter{

	@Override
	public boolean match(Product product) {
		return product instanceof Product3x2;
	}

	
	
}
