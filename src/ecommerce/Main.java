package ecommerce;


import ecommerce.panel.BuyPanel;
import ecommerce.panel.LoginPanel;
import ecommerce.panel.PanelManager;
import ecommerce.product.ProductManager;
import ecommerce.user.UserManager;

public class Main {

	public static void main(String[] args) {
		//Load configuration from config file
		Configuration.load();
		
		//Load users from configuration file
		UserManager.loadUsers();
		
		//Load the products
		ProductManager.loadProducts();
		
		//Create the main program window and all the panels
		PanelManager pm = new PanelManager();
		pm.setCurrentPanel(BuyPanel.TAG);
			
	}
	
}
