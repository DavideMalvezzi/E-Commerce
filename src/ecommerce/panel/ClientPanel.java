package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import ecommerce.basket.BasketManager;
import ecommerce.product.Product;
import ecommerce.product.ProductManager;
import ecommerce.product.filter.ProductFilter;
import ecommerce.product.filter.CategoryFilter;
import ecommerce.product.filter.NameFilter;
import ecommerce.widget.ProductPanel;

public class ClientPanel extends CustomPanel{

	public static final String TAG = "client";
	
	private JButton backButton;
	private JTextField nameField;
	private JComboBox<String> categoryCombo;
	private JButton searchButton;
	private JButton filterButton;
	private JButton cartButton;
	private JPanel productsPanel;
	
		
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
		cartButton = createToolBarButton("/image/basket.png", "Carrello");
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(nameField);
		toolBar.add(categoryCombo);
		toolBar.add(searchButton);
		toolBar.add(filterButton);
		toolBar.add(Box.createHorizontalGlue()); // After this every component will be added to the right
		toolBar.add(cartButton);
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridBagLayout());		

		add(toolBar, BorderLayout.PAGE_START);		
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
	}

	@Override
	public void onEnter() {
		//Load the products
		ProductManager.loadProducts();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1f;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(4, 8, 4, 8);

		productsPanel.removeAll();
		for(int i = 0; i < ProductManager.getProductCount(); i++){
			c.gridy = i;
			productsPanel.add(new ProductPanel(ProductManager.getProduct(i)), c);
		}
		
		
		Vector<String> categories = ProductManager.getProductCategoryList();
		categories.add(0, CategoryFilter.CATEGORY_WILDCARD);
		categoryCombo.setModel(new DefaultComboBoxModel<String>(categories));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			BasketManager.clear();
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		else if(e.getSource().equals(searchButton)){
			applyFilter(new NameFilter(nameField.getText()), new CategoryFilter((String)categoryCombo.getSelectedItem()));
		}
	}
	
	private void applyFilter(ProductFilter...filters) {
		Product p;
		
		for(Component c : productsPanel.getComponents()){
			c.setVisible(true);
		}
		
		for(ProductFilter filter : filters){
			for(Component c : productsPanel.getComponents()){
				if(c.isVisible()){
					p = ((ProductPanel)c).getProduct();
					c.setVisible(filter.match(p));
				}
			}
		}
	}

}
