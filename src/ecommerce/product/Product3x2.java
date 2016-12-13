package ecommerce.product;

/**
 * @author Davide
 * @file
 * Classe rappresentate prodotto soggetto all'offerta 3x2
 */
public class Product3x2 extends Product {
	
	@Override
	/**
	 * @brief Ritorna il totale soggetto all'offerta 3x2.
	 * Reimplementazione del metodo della classe Product
	 * @return Totale
	 */
	public float getTotal(int n) {
		float tot = getFinalPrice() * n;
		return tot - getFinalPrice() * (n / 3);
	}

}
