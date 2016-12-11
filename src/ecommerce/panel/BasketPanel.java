package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import ecommerce.basket.BasketManager;
import ecommerce.utils.WrapLayout;
import ecommerce.widget.BasketProductPanel;

public class BasketPanel extends CustomPanel {
	
	public static final String TAG = "basket";

	private JButton backButton;
	private JButton clearBasket;
	private JButton buyButton;
	
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
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(clearBasket);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(buyButton);
		
		productsPanel = new JPanel(new WrapLayout(WrapLayout.CENTER, 32, 32));
	
		add(toolBar, BorderLayout.PAGE_START);
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
	}
	
	@Override
	public void onEnter() {
		productsPanel.removeAll();
		
		System.out.println("Ci sono prodotti " + BasketManager.getCount());
		
		for(int i = 0; i < BasketManager.getCount(); i++){
			productsPanel.add(new BasketProductPanel(BasketManager.getProduct(i), BasketManager.getProductQuantity(i)));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(ClientPanel.TAG);
		}
		else if(e.getSource().equals(clearBasket)){
			BasketManager.clear();
		}
		
	}

}
