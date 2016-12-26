package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import ecommerce.basket.BasketManager;
import ecommerce.basket.BasketProductRemoved;
import ecommerce.product.Product;
import ecommerce.utils.WrapLayout;
import ecommerce.widget.BasketProductPanel;

/**
 * Classe che implementa la schermata di gestione dei prodotti nel carrello
 * @author Davide Malvezzi
 */
public class BasketPanel extends CustomPanel implements BasketProductRemoved {
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "basket";

	/**
	 * @var backButton
	 * Bottone per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var clearBasket
	 * Bottone per rimuovere tutti i prodotti nel carrello
	 */
	private JButton clearBasket;
	
	/**
	 * @var buyButton
	 * Bottone per passare alla schermata di acquisto
	 */
	private JButton buyButton;
	
	/**
	 * @var totLabel
	 * Label contenente il totale dei prodtti nel carrello
	 */
	private JLabel totLabel;
	
	/**
	 * @var productsPanel
	 * Pannello contenente tutti i prodotti
	 */
	private JPanel productsPanel;
		
	/**
	 * Costruttore
	 * @param panelManager Finestra
	 */
	public BasketPanel(PanelManager panelManager) {
		super(panelManager);
	
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		backButton = createToolBarButton("/image/back.png", "Indietro");
		clearBasket = createToolBarButton("/image/removebasket.png", "Svuota carrello");
		buyButton = createToolBarButton("/image/buy.png", "Conferma acquisto");
		
		totLabel = new JLabel("0.00");
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(clearBasket);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(new JLabel("Totale: € "));
		toolBar.add(totLabel);
		toolBar.addSeparator();
		toolBar.add(buyButton);
	
		
		productsPanel = new JPanel(new WrapLayout(WrapLayout.CENTER, 32, 32));
	
		add(toolBar, BorderLayout.PAGE_START);
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
	}
	
	/**
	 * @brief Carica tutti prodotti presenti nel carrello
	 */
	@Override
	public void onEnter() {
		//If the basket is dirty
		if(BasketManager.isDirty()){
			//Remove all products
			productsPanel.removeAll();
			//Loadd all new products
			for(int i = 0; i < BasketManager.getCount(); i++){
				productsPanel.add(new BasketProductPanel(BasketManager.getProduct(i), BasketManager.getProductQuantity(i), this));
			}
			//Clean the basket
			BasketManager.setDirty(false);
		}
		
		reloadTotal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Go back
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(ClientPanel.TAG);
		}
		//Clear basket
		else if(e.getSource().equals(clearBasket)){
			BasketManager.clear();
			reloadTotal();
			productsPanel.removeAll();
			productsPanel.revalidate();
			productsPanel.repaint();
		}
		//Go to buy panel
		else if(e.getSource().equals(buyButton)){
			if(BasketManager.getCount() > 0){
				panelManager.setCurrentPanel(BuyPanel.TAG);
			}
			else{
				JOptionPane.showMessageDialog(this, "Impossibile procedere all'acquisto: il carrello è vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			reloadTotal();
		}
		
	}
	
	/**
	 * @brief Calcolo del costo totale dei prodotti
	 */
	private void reloadTotal() {
		float total = 0;
		for(int i = 0; i < BasketManager.getCount(); i++){
			total += BasketManager.getProduct(i).getTotal(BasketManager.getProductQuantity(i));
		}
		totLabel.setText(String.format("%.2f", total));
	}

	/**
	 * @brief Rimuove un prodotto e ricalcola il totale
	 */
	@Override
	public void onBasketProductRemoved(BasketProductPanel basketProductPanel) {
		Product product = basketProductPanel.getProduct();
		BasketManager.removeProduct(product);
		
		reloadTotal();
		
		productsPanel.remove(basketProductPanel);
		productsPanel.revalidate();
		productsPanel.repaint();
	}

}
