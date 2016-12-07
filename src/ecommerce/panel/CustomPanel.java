package ecommerce.panel;

import javax.swing.JPanel;

import ecommerce.PanelManager;

public class CustomPanel extends JPanel{

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
		if(aFlag){
			onEnter();
		}
		else{
			onExit();
		}
		super.setVisible(aFlag);
	}
	
}
