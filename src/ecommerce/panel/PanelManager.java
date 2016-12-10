package ecommerce.panel;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PanelManager extends JFrame {
	
	private JPanel container;
	
	public PanelManager() {

		//Create the container and add all the panel
		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		container.add(new ClientPanel(this), ClientPanel.TAG);
		container.add(new BasketPanel(this), BasketPanel.TAG);
		
		add(container);
		
		//Set windows settings
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
