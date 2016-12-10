package ecommerce.panel;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class CustomPanel extends JPanel implements ActionListener{

	protected PanelManager panelManager;
	
	public CustomPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	public void onEnter(){
		
	}
	
	public void onExit(){
		
	}
	
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
	
	
	protected JButton createToolBarButton(String image, String toolTip){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(CustomPanel.class.getResource(image)));
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		
		return button;
	}
	
}
