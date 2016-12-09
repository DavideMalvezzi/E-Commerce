package ecommerce.panel.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ecommerce.product.Product;

public class ProductViewCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		if(value != null){
			value = "<html>" + value + "</html>";
		}
		
		JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		c.setFont(new Font(c.getFont().getFontName(), Font.PLAIN, 24));

		return c;
	}
	
}
