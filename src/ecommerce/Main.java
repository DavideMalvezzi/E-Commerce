package ecommerce;


import ecommerce.panel.LoginPanel;

import ecommerce.user.UserManager;

public class Main {

	public static void main(String[] args) {
		//Load users from configuration file
		UserManager.loadUsers();
		
		//Create login panel
		PanelManager pm = new PanelManager();
		pm.setCurrentPanel(new LoginPanel(pm));
		
	}
	
}
