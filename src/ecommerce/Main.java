package ecommerce;

import ecommerce.panel.ClientPanel;
import ecommerce.panel.PanelManager;
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
		
	
		
	}
	
}
