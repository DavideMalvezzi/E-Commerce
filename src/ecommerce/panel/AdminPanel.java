package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import ecommerce.Configuration;
import ecommerce.dialog.CreateProductDialog;
import ecommerce.product.Product;
import ecommerce.product.ProductManager;
import ecommerce.widget.model.AdminViewModel;
import ecommerce.widget.view.AdminProductView;

/**
 * Classe che implementa la schermata di amministrazione dei prodotti
 * @author Davide Malvezzi
 */
public class AdminPanel extends CustomPanel {

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "admin";
	
	/**
	 * @var backButton
	 * Bottone per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var loadButton
	 * Bottone per caricare file dei prodotti
	 */
	private JButton loadButton;
	
	/**
	 * @var saveButton
	 * Bottone per salvare file dei prodotti
	 */
	private JButton saveButton;
	
	/**
	 * @var addButton
	 * Bottone per aggiungere un prodotto
	 */
	private JButton addButton;
	
	/**
	 * @var editButton
	 * Bottone per modificare un prodotto
	 */
	private JButton editButton;

	/**
	 * @var removeButton
	 * Bottone per rimuovere un prodotto
	 */
	private JButton removeButton;
	
	/**
	 * @var productView
	 * Tabella dei prodotti
	 */
	private AdminProductView productView;


	/**
	 * Costruttore
	 * @param panelManager Finestra
	 */
	public AdminPanel(PanelManager panelManager) {
		super(panelManager);
				
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		backButton = createToolBarButton("/image/back.png", "Esci");
		loadButton = createToolBarButton("/image/load.png", "Carica file dei prodotti");
		saveButton = createToolBarButton("/image/save.png", "Salva file dei prodotti");
		addButton = createToolBarButton("/image/add.png", "Aggiungi prodotto");
		editButton = createToolBarButton("/image/edit.png", "Modifica prodotto");
		removeButton = createToolBarButton("/image/remove.png", "Elimina prodotto");

		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(loadButton);
		toolBar.add(saveButton);
		toolBar.addSeparator();
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(removeButton);
		
		productView = new AdminProductView(new AdminViewModel());
		
		add(toolBar, BorderLayout.PAGE_START);		
		add(productView, BorderLayout.CENTER);
	
	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti
	 */
	@Override
	public void onEnter() {
		//Load the products
		ProductManager.loadProducts();
		productView.refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Go back to login panel
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		//Load a new product file
		else if(e.getSource().equals(loadButton)){
			//Load a product file and set new file path in the configurations file
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				Configuration.getInstance().productFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				if(!ProductManager.loadProducts()){
					JOptionPane.showMessageDialog(this, "Il file scelto non � stato caricato correttamente.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Configuration.save();
				}
				productView.refresh();
			}
		}
		//Save the products file
		else if(e.getSource().equals(saveButton)){
			if(!ProductManager.saveProducts()){
				JOptionPane.showMessageDialog(this, "Errore durante il salvataggio del file dei prodotti.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		//Add a product
		else if(e.getSource().equals(addButton)){
			CreateProductDialog cpDialog = new CreateProductDialog();
			cpDialog.setVisible(true);
			if(cpDialog.getProduct() != null){
				ProductManager.addProduct(cpDialog.getProduct());
				productView.refresh();
			}
		}
		//Edit the current selected product
		else if(e.getSource().equals(editButton)){
			int index = productView.getSelectedRow();
			if(index != -1){
				Product p = ProductManager.getProduct(index);
				CreateProductDialog cpDialog = new CreateProductDialog(p);
				cpDialog.setVisible(true);
				if(cpDialog.getProduct() != null){
					ProductManager.replaceProduct(p, cpDialog.getProduct());
					productView.refresh();
				}
			}
		}
		//Remove the current selected product
		else if(e.getSource().equals(removeButton)){
			int index = productView.getSelectedRow();
			if(index != -1){
				int res = JOptionPane.showConfirmDialog(this, "Vuoi cancellare questo prodotto?", "Cancellare?", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
					ProductManager.removeProduct(index);
					productView.refresh();
				}
			}
		}
		
	}
	
}
