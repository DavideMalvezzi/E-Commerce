package ecommerce;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ecommerce.panel.AdminPanel;
import ecommerce.panel.LoginPanel;


public class PanelManager extends JFrame {
	
	private JPanel container;
	
	public PanelManager() {

		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		
		add(container);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setSize(800, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("E-Commerce");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setCurrentPanel(String panelName){
		CardLayout cl = (CardLayout)(container.getLayout());
		
        cl.show(container, panelName);
        
        
	}
	
	

}
