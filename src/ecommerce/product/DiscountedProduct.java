package ecommerce.product;

/**
 * Classe rappresentante un prodotto scontato
 * @author Davide Malvezzi
 */
public class DiscountedProduct extends Product{
	
	/**
	 * @var discount
	 * Percentuale intera di sconto
	 */
	private int discount;
	
	/**
	 * @brief Setta lo sconto. Reimplementazione del metodo della classe Product
	 */
	@Override
	public void setDiscount(int discount){
		this.discount = discount;
	
	}
	
	/**
	 * @brief Ritorna lo sconto. Reimplementazione del metodo della classe Product
	 * @return Sconto prodotto
	 */
	@Override
	public int getDiscount() {
		return discount;
	}

}
