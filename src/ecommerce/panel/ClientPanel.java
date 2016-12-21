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

public class ClientPanel extends CustomPanel implements DropTargetListener{

	public static final String TAG = "client";
	
	private JButton backButton;
	private JTextField nameField;
	private JComboBox<String> categoryCombo;
	private JButton searchButton;
	private JButton filterButton;
	private JButton removeFilterButton;
	private JButton basketButton;
	private JPanel productsPanel;
	
		
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

	@Override
	public void onEnter() {
		if(ProductManager.isDirty()){
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.weightx = 1f;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(4, 8, 4, 8);
			
			productsPanel.removeAll();
			for(int i = 0; i < ProductManager.getProductCount(); i++){
				c.gridy = i;
				productsPanel.add(new BuyProductPanel(ProductManager.getProduct(i)), c);
			}
			
			//Load categories list
			Vector<String> categories = ProductManager.getProductCategoryList();
			categories.add(0, CategoryFilter.ALL_CATEGORIES);
			categoryCombo.setModel(new DefaultComboBoxModel<String>(categories));
			
			ProductManager.setDirty(false);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			BasketManager.clear();
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		
		else if(e.getSource().equals(searchButton)){
			Vector<ProductFilter> filters = new Vector<ProductFilter>();
			filters.add(new NameFilter(nameField.getText()));
			filters.add(new CategoryFilter((String)categoryCombo.getSelectedItem()));
			applyFilters(filters);
		}
		
		else if(e.getSource().equals(filterButton)){
			FilterProductDialog fpDialog = new FilterProductDialog();
			fpDialog.setVisible(true);
			if(fpDialog.getFilters() != null && !fpDialog.getFilters().isEmpty()){
				applyFilters(fpDialog.getFilters());
			}
		}
		
		else if(e.getSource().equals(removeFilterButton)){
			removeAllFilters();
		}
		
		else if(e.getSource().equals(basketButton)){
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
	}
	
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

	private void removeAllFilters(){
		nameField.setText("");
		categoryCombo.setSelectedIndex(0);
		
		showAllProducts();
	}
	
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
        
        if(df[0].getRepresentationClass().equals(Product.class) && df[1].getRepresentationClass().equals(Integer.class)){
        	int res = JOptionPane.showConfirmDialog(this, "Vuoi aggiungere questo oggetto al carrello?", "Aggiungere?", JOptionPane.YES_NO_OPTION);
    		if(res == JOptionPane.YES_OPTION){ 
                try {                	
    				int pIndex = (int) t.getTransferData(df[0]);
    				int qt = (int) t.getTransferData(df[1]);
    				
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
