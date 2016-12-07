package ecommerce.product;

public class Product3x2 extends Product {
	
	@Override
	public float getTotal(int n) {
		float tot = getFinalPrice() * n;
		return tot - getFinalPrice() * (n / 3);
	}

}
