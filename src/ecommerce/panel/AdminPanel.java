package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ecommerce.Configuration;
import ecommerce.PanelManager;
import ecommerce.panel.dialog.CreateProductDialog;
import ecommerce.product.ProductManager;

public class AdminPanel extends CustomPanel implements ActionListener{

	public static final String TAG = "admin";
	
	private JButton backButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;


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
		
		add(toolBar, BorderLayout.PAGE_START);
	}
	
	@Override
	public void onEnter() {
		//Load the products
		if(!ProductManager.loadProducts()){
			JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei prodotti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JButton createToolBarButton(String image, String toolTip){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(AdminPanel.class.getResource(image)));
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		
		return button;
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
					JOptionPane.showMessageDialog(this, "Il file scelto non è stato caricato correttamente.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Configuration.save();
					//TODO: reload products on table
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
		}
		
	}
	
}
