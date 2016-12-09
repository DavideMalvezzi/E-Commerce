package ecommerce.panel.widget.admin;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import ecommerce.panel.widget.ProductView;

public class AdminProductView extends ProductView {

	public AdminProductView() {
		super(new AdminViewModel());
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		rightRenderer.setFont(new Font(rightRenderer.getFont().getFontName(), Font.PLAIN, 24));
		
		table.getColumnModel().getColumn(AdminViewModel.PRICE_COL).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(AdminViewModel.OFFER_COL).setCellRenderer(rightRenderer);
	}

}
