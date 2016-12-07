package ecommerce.product;

public class DiscountedProduct extends Product{
	
	private float discount;
	
	public void setDiscount(float discount){
		this.discount = discount;
	
	}
	
	@Override
	public float getDiscount() {
		return discount;
	}

}
