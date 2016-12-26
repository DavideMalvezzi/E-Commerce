package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import ecommerce.basket.BasketManager;
import ecommerce.dialog.FilterProductDialog;
import ecommerce.product.Product;
import ecommerce.product.ProductManager;
import ecommerce.product.filter.ProductFilter;
import ecommerce.product.filter.CategoryFilter;
import ecommerce.product.filter.NameFilter;
import ecommerce.widget.BuyProductPanel;

/**
 * Classe che implementa la schermata di navigazione dei prodotti
 * @author Davide Malvezzi
 */
public class ClientPanel extends CustomPanel implements DropTargetListener{

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "client";
	
	/**
	 * @var backButton
	 * Bottone per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var nameField
	 * Casella di testo per ricerca rapida tramite nome
	 */
	private JTextField nameField;
	
	/**
	 * @var categoryCombo
	 * Combobox per filtrare le categorie
	 */
	private JComboBox<String> categoryCombo;
	
	/**
	 * @var searchButton
	 * Bottone per la ricerca rapida
	 */
	private JButton searchButton;
	
	/**
	 * @var filterButton
	 * Bottone per aprire la dialog di ricerca avanzata
	 */
	private JButton filterButton;
	
	/**
	 * @var removeFilterButton
	 * Bottone per rimuovere tutti i filtri di ricerca applicati
	 */
	private JButton removeFilterButton;
	
	/**
	 * @var basketButton
	 * Bottone per andare alla schermata del carrello
	 */
	private JButton basketButton;
	
	/**
	 * @var productsPanel
	 * Pannello contenente tutti i prodotti
	 */
	private JPanel productsPanel;
	
	
	/**
	 * @brief Costruttore
	 * @param panelManager Finestra
	 */
	public ClientPanel(PanelManager panelManager) {
		super(panelManager);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JLabel searchLabel = new JLabel("Cerca: ");
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		backButton = createToolBarButton("/image/back.png", "Esci");
		nameField = new JTextField();
		nameField.setMaximumSize(new Dimension(4096*2, 24));
		categoryCombo = new JComboBox<String>();
		categoryCombo.setMaximumSize(new Dimension(4096, 24));

		searchButton = createToolBarButton("/image/search.png", "Cerca");
		filterButton = createToolBarButton("/image/filter.png", "Applica filtri di ricerca");
		removeFilterButton = createToolBarButton("/image/removefilter.png", "Rimuovi tutti i filtri di ricerca");

		basketButton = createToolBarButton("/image/basket.png", "Carrello");
		DropTarget dTarget = new DropTarget(basketButton, this);
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(searchLabel);
		toolBar.add(nameField);
		toolBar.add(categoryCombo);
		toolBar.add(searchButton);
		toolBar.add(filterButton);
		toolBar.add(removeFilterButton);
		toolBar.add(Box.createHorizontalGlue()); // After this every component will be added to the right
		toolBar.add(basketButton);
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridBagLayout());		

		add(toolBar, BorderLayout.PAGE_START);		
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
	}

	/**
	 * @brief Carica tutti i prodotti
	 */
	@Override
	public void onEnter() {
		//If product manager is dirty
		if(ProductManager.isDirty()){
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.weightx = 1f;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(4, 8, 4, 8);
			
			//Remove all products
			productsPanel.removeAll();
			
			//Load all products
			for(int i = 0; i < ProductManager.getProductCount(); i++){
				c.gridy = i;
				productsPanel.add(new BuyProductPanel(ProductManager.getProduct(i)), c);
			}
			
			//Load categories list
			Vector<String> categories = ProductManager.getProductCategoryList();
			categories.add(0, CategoryFilter.ALL_CATEGORIES);
			categoryCombo.setModel(new DefaultComboBoxModel<String>(categories));
			
			//Clean the product manager
			ProductManager.setDirty(false);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Go back
		if(e.getSource().equals(backButton)){
			BasketManager.clear();
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		//Apply quick search by name and category
		else if(e.getSource().equals(searchButton)){
			Vector<ProductFilter> filters = new Vector<ProductFilter>();
			filters.add(new NameFilter(nameField.getText()));
			filters.add(new CategoryFilter((String)categoryCombo.getSelectedItem()));
			applyFilters(filters);
		}
		//Apply advanced search filters
		else if(e.getSource().equals(filterButton)){
			FilterProductDialog fpDialog = new FilterProductDialog();
			fpDialog.setVisible(true);
			if(fpDialog.getFilters() != null && !fpDialog.getFilters().isEmpty()){
				applyFilters(fpDialog.getFilters());
			}
		}
		//Remove all applied filters
		else if(e.getSource().equals(removeFilterButton)){
			removeAllFilters();
		}
		//Go to basket panel
		else if(e.getSource().equals(basketButton)){
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
	}
	
	/**
	 * @brief Nasconde tutti i prodotti che non rispettano i filtri applicati
	 * @param filters Vettore di filtri
	 */
	private void applyFilters(Vector<ProductFilter> filters) {
		Product p;
		
		showAllProducts();
		
		for(ProductFilter filter : filters){
			for(Component c : productsPanel.getComponents()){
				if(c.isVisible()){
					p = ((BuyProductPanel)c).getProduct();
					c.setVisible(filter.match(p));
				}
			}
		}
	}

	/**
	 * @brief Rimuove tutti i filtri applicati
	 */
	private void removeAllFilters(){
		nameField.setText("");
		categoryCombo.setSelectedIndex(0);
		
		showAllProducts();
	}
	
	/**
	 * @brief Mostra tutti i prodotti nascosti
	 */
	private void showAllProducts() {
		for(Component c : productsPanel.getComponents()){
			c.setVisible(true);
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}

	@Override
	public void dragExit(DropTargetEvent dte) {}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		Transferable t = dtde.getTransferable();
        DataFlavor[] df = t.getTransferDataFlavors();
        
        //Check if the dnd data meta-class correspond to the expected
        if(df[0].getRepresentationClass().equals(Product.class) && df[1].getRepresentationClass().equals(Integer.class)){
        	int res = JOptionPane.showConfirmDialog(this, "Vuoi aggiungere questo oggetto al carrello?", "Aggiungere?", JOptionPane.YES_NO_OPTION);
    		
        	if(res == JOptionPane.YES_OPTION){ 
                try {                	
    				int pIndex = (int) t.getTransferData(df[0]);
    				int qt = (int) t.getTransferData(df[1]);
    				
    				//Add product to the basket
    				BasketManager.addProduct(ProductManager.getProduct(pIndex), qt);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                dtde.dropComplete(true);
    		}
    		else{
    			dtde.rejectDrop();
    		}
        }
        else{
        	dtde.rejectDrop();
        }
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {}

}
