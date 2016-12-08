package ecommerce.product;

public class DiscountedProduct extends Product{
	
	private int discount;
	
	public void setDiscount(int discount){
		this.discount = discount;
	
	}
	
	@Override
	public int getDiscount() {
		return discount;
	}

}
