package ecommerce;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PanelManager extends JFrame {
	
	private JPanel currentPanel;
	
	public PanelManager() {
		currentPanel = null;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setSize(800, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("E-Commerce");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setCurrentPanel(JPanel panel){
		if(currentPanel != null){
			remove(currentPanel);
		}
		currentPanel = panel;
		add(panel);
	}
	
	

}
