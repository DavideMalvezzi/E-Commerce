package ecommerce.basket;

import ecommerce.widget.BasketProductPanel;

/**
 * Interfaccia utilizzata per implementare un metodo di callback per la pressione del tasto rimuovi in BasketProductPanel
 * @author Davide Malvezzi
 */
public interface BasketProductRemoved {

	/**
	 * @brief Funzione di callback invocata da un'istanza di BasketProductPanel quando il bottone rimuovi � stato premuto
	 * @param basketProductPanel Istanza di BasketProductPanel in cui � stato premuto il bottone rimuovi
	 */
	public void onBasketProductRemoved(BasketProductPanel basketProductPanel);
}
