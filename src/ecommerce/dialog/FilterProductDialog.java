package ecommerce.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JDialog;

import ecommerce.product.filter.ProductFilter;

public class FilterProductDialog extends JDialog implements ActionListener{

	private Vector<ProductFilter> filters = null;

	
	public FilterProductDialog() {
		setSize(270, 160);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setTitle("Seleziona filtri di ricerca");
	}
	
	public Vector<ProductFilter> getFilters(){
		return filters;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
