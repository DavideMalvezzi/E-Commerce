package ecommerce.panel.widget;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class AdminProductView extends ProductView {

	public AdminProductView() {
		super(new AdminViewModel());
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table.getColumnModel().getColumn(AdminViewModel.PRICE_COL).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(AdminViewModel.OFFER_COL).setCellRenderer(rightRenderer);
	}

}
