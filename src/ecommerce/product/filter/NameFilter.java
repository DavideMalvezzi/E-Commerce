package ecommerce.product.filter;

import ecommerce.product.Product;

public class NameFilter extends ProductFilter{
	
	private String name;
	
	public NameFilter(String name) {
		this.name = name;
	}

	@Override
	public boolean match(Product product) {
		return name.isEmpty() || product.getName().contains(name);
	}

}
