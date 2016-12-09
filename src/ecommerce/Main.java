package ecommerce;



import ecommerce.dialog.CreateProductDialog;
import ecommerce.panel.AdminPanel;
import ecommerce.panel.ClientPanel;
import ecommerce.panel.LoginPanel;
import ecommerce.panel.PanelManager;
import ecommerce.product.DiscountedProduct;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.product.ProductManager;
import ecommerce.user.UserManager;

public class Main {

	public static void main(String[] args) {
		//Load configuration from config file
		Configuration.load();
		
		//Load users from configuration file
		UserManager.loadUsers();
		
		//Create login panel
		PanelManager pm = new PanelManager();
		pm.setCurrentPanel(ClientPanel.TAG);
		
		/*
		CreateProductDialog cpDialog = new CreateProductDialog();
		cpDialog.setVisible(true);
		
		ProductManager.loadProducts();
		ProductManager.addProduct(cpDialog.getProduct());
		ProductManager.saveProducts();

		
		ProductManager.loadProducts();
		cpDialog = new CreateProductDialog(ProductManager.getProduct(0));
		cpDialog.setVisible(true);
		*/

		
	}
	
}
