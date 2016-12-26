package ecommerce.panel;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe che implmenta una generica schermata del programma
 * @author Davide Malvezzi
 */
public abstract class CustomPanel extends JPanel implements ActionListener{
	
	/**
	 * @var panelManager
	 * Riferimento alla finestra del programma
	 */
	protected PanelManager panelManager;
	
	/**
	 * Costruttore
	 * @param panelManager Riferimento alla finestra del programma
	 */
	public CustomPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	/**
	 * @brieft Funzione chiamata in automatico alla visualizzazione della schermata
	 */
	public void onEnter(){
		
	}
	
	/**
	 * @brieft Funzione chiamata in automatico all'uscita della schermata
	 */
	public void onExit(){
		
	}
	
	/**
	 * @brief Override per implementare la chiamata automatica di onEnter e onExit
	 */
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(aFlag){
			onEnter();
		}
		else{
			onExit();
		}
	}
	
	/**
	 * @brief Funzione utilizzata per creare bottoni con icone per le toolbar
	 * @param image Icona
	 * @param toolTip Descizione bottone
	 * @return Bottone
	 */
	protected JButton createToolBarButton(String image, String toolTip){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(CustomPanel.class.getResource(image)));
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		
		return button;
	}
	
}
