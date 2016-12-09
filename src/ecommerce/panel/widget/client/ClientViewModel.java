package ecommerce.panel.widget.client;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import ecommerce.product.Product;
import ecommerce.product.ProductManager;

public class ClientViewModel extends AbstractTableModel {

	public static final int CODE_COL = 0;
	public static final int NAME_COL = 1;
	public static final int BRAND_COL = 2;
	public static final int CATEGORY_COL = 3;
	public static final int PRICE_COL = 4;
	public static final int IMG_COL = 5;
	
	@Override
	public int getRowCount() {
		return ProductManager.getProductCount();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product product = ProductManager.getProduct(rowIndex);
		
		if(product != null){
			switch (columnIndex) {
				case CODE_COL:
					return product.getCode();
					
				case NAME_COL:
					return product.getName();
					
				case BRAND_COL:
					return product.getBrand();
					
				case CATEGORY_COL:
					return product.getCategory();
					
				case PRICE_COL:
					if(product.getDiscount() > 0){
						return "<strike>" + String.format("%.2f", product.getPrice()) + "€</strike>"
								+ "<font color='red'> -" + product.getDiscount() + "%</font> -> " 
								+ String.format("%.2f", product.getFinalPrice()) + "€";
					}
					return Float.toString(product.getPrice());
			}
		}
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
			case CODE_COL:
				return "Codice";
				
			case NAME_COL:
				return "Nome";
				
			case BRAND_COL:
				return "Marca";
				
			case CATEGORY_COL:
				return "Categoria";
				
			case PRICE_COL:
				return "Prezzo";
		}		
		return "";
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
