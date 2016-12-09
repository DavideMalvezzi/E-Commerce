package ecommerce.panel.widget;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public abstract class ProductView extends JScrollPane {

	private AbstractTableModel model;
	protected JTable table;
		
	public ProductView(AbstractTableModel m) {
		super();
		
		model = m;
		table = new JTable(model);
		table.setDefaultRenderer(String.class, new ProductViewCellRenderer());
		
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setRowHeight(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		setViewportView(table);
		
		refresh();
	}
	
	public void refresh(){
		model.fireTableDataChanged();
		for (int i = 0; i < table.getColumnCount(); i++) {
			adjustColumnSizes(table, i, 0);
        }	
	}
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	
	
	private static void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, column), false, false, r, column);
            int currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        width += 2 * margin;

        col.setPreferredWidth(width);
    }
	
	
}
