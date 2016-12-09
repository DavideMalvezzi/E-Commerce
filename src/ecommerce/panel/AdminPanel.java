package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import ecommerce.Configuration;
import ecommerce.panel.dialog.CreateProductDialog;
import ecommerce.panel.widget.ProductView;
import ecommerce.panel.widget.admin.AdminProductView;
import ecommerce.panel.widget.admin.AdminViewModel;
import ecommerce.panel.widget.client.ClientViewModel;
import ecommerce.product.Product;
import ecommerce.product.ProductManager;

public class AdminPanel extends CustomPanel {

	public static final String TAG = "admin";
	
	private JButton backButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;
	
	private AdminProductView productView;


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
		
		productView = new AdminProductView();
		
		add(toolBar, BorderLayout.PAGE_START);		
		add(productView, BorderLayout.CENTER);
	
	}
	
	@Override
	public void onEnter() {
		//Load the products
		ProductManager.loadProducts();
		productView.refresh();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		
		else if(e.getSource().equals(loadButton)){
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				Configuration.getInstance().productFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				if(!ProductManager.loadProducts()){
					JOptionPane.showMessageDialog(this, "Il file scelto non � stato caricato correttamente.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Configuration.save();
					productView.refresh();
				}
			}
		}
		
		else if(e.getSource().equals(saveButton)){
			if(!ProductManager.saveProducts()){
				JOptionPane.showMessageDialog(this, "Errore durante il salvattagio del file dei prodotti.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource().equals(addButton)){
			CreateProductDialog cpDialog = new CreateProductDialog();
			cpDialog.setVisible(true);
			if(cpDialog.getProduct() != null){
				ProductManager.addProduct(cpDialog.getProduct());
				productView.refresh();
			}
		}
		
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
