package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ecommerce.PanelManager;
import ecommerce.product.ProductManager;

public class AdminPanel extends JPanel implements ActionListener{

	private JButton backButton;
	
	private JButton loadButton;
	private JButton saveButton;
	
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;


	public AdminPanel(PanelManager pm) {
		ProductManager.loadProducts();
		
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		loadButton = new JButton();
		loadButton.setIcon(new ImageIcon(AdminPanel.class.getResource("/image/open.png")));
		loadButton.addActionListener(this);
		toolBar.add(loadButton);
		toolBar.addSeparator();
		
		
		
		add(toolBar, BorderLayout.PAGE_START);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
