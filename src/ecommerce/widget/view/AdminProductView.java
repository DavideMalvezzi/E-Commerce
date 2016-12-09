package ecommerce.widget.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import ecommerce.widget.model.AdminViewModel;

public class AdminProductView extends ProductView {

	public AdminProductView() {
		super(new AdminViewModel());
		
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				if(value != null){
					value = "<html>" + value + "</html>";
				}
				
				JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font(c.getFont().getFontName(), Font.PLAIN, 24));
				
				if(column == AdminViewModel.PRICE_COL || column == AdminViewModel.OFFER_COL){
					c.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				else{
					c.setHorizontalAlignment(SwingConstants.LEFT);
				}

				return c;
			}

		});
	}

}
