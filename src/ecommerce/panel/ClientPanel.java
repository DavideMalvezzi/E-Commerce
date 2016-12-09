package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import ecommerce.panel.widget.client.ClientProductView;
import ecommerce.product.ProductManager;

public class ClientPanel extends CustomPanel{

	public static final String TAG = "client";
	
	private JButton backButton;
	private JTextField nameField;
	private JComboBox<String> categoryCombo;
	private JButton searchButton;
	private JButton filterButton;
	private JButton cartButton;
	
	
	private ClientProductView productView;
	
	public ClientPanel(PanelManager panelManager) {
		super(panelManager);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		backButton = createToolBarButton("/image/back.png", "Esci");
		nameField = new JTextField();
		nameField.setMaximumSize(new Dimension(4096*2, 24));
		categoryCombo = new JComboBox<String>();
		categoryCombo.setMaximumSize(new Dimension(4096, 24));

		searchButton = createToolBarButton("/image/search.png", "Cerca");
		filterButton = createToolBarButton("/image/filter.png", "Applica filtro");
		cartButton = createToolBarButton("/image/cart.png", "Carrello");
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(nameField);
		toolBar.add(categoryCombo);
		toolBar.add(searchButton);
		toolBar.add(filterButton);
		toolBar.add(Box.createHorizontalGlue()); // After this every component will be added to the right
		toolBar.add(cartButton);
		
		
		productView = new ClientProductView();
		
		add(toolBar, BorderLayout.PAGE_START);		
		add(productView, BorderLayout.CENTER);
	}

	@Override
	public void onEnter() {
		//Load the products
		ProductManager.loadProducts();
		categoryCombo.setModel(new DefaultComboBoxModel<String>(ProductManager.getProductCategoryList()));
		productView.refresh();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		
	}

}
