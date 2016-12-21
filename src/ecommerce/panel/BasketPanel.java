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

public class BasketPanel extends CustomPanel implements BasketProductRemoved {
	
	public static final String TAG = "basket";

	private JButton backButton;
	private JButton clearBasket;
	private JButton buyButton;
	
	private JLabel totLabel;
	
	private JPanel productsPanel;
		
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
	
	@Override
	public void onEnter() {
		if(BasketManager.isDirty()){
			productsPanel.removeAll();
					
			for(int i = 0; i < BasketManager.getCount(); i++){
				productsPanel.add(new BasketProductPanel(BasketManager.getProduct(i), BasketManager.getProductQuantity(i), this));
			}
			
			BasketManager.setDirty(false);
		}
		
		reloadTotal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(ClientPanel.TAG);
		}
		else if(e.getSource().equals(clearBasket)){
			BasketManager.clear();
			reloadTotal();
			productsPanel.removeAll();
			productsPanel.revalidate();
			productsPanel.repaint();
		}
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
	
	private void reloadTotal() {
		float total = 0;
		for(int i = 0; i < BasketManager.getCount(); i++){
			total += BasketManager.getProduct(i).getTotal(BasketManager.getProductQuantity(i));
		}
		totLabel.setText(String.format("%.2f", total));
	}

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
