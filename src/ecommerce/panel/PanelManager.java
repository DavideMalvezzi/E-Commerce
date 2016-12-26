package ecommerce.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ecommerce.utils.ImageLoader;

/**
 * Classe che implementa la finestra principale del programma
 * @author Davide Malvezzi
 */
public class PanelManager extends JFrame {
	
	/**
	 * @var container
	 * Panello principale in cui verranno mostrate le varie schermate del programma
	 */
	private JPanel container;
	
	public PanelManager() {

		//Create the container and add all the panel
		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		container.add(new ClientPanel(this), ClientPanel.TAG);
		container.add(new BasketPanel(this), BasketPanel.TAG);
		container.add(new BuyPanel(this), BuyPanel.TAG);

		add(container);
		
		//Set windows settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(PanelManager.class.getResource("/image/icon32x32.png")).getImage());
		setSize(800, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("E-Commerce");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Cambia la schermata corrente con un'altra
	 * @param panelName Tag associato alla schermata da mostrare
	 */
	public void setCurrentPanel(String panelName){
		CardLayout cl = (CardLayout)(container.getLayout());
        cl.show(container, panelName);   
	}
	
	

}
