package ecommerce.widget.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecommerce.panel.AdminPanel;
import ecommerce.widget.model.AdminViewModel;

/**
 * Classe che implementa la vista della tabella di gestione dei prodotti inserita nell' {@link AdminPanel.java}
 * @author Davide Malvezzi
 */
public class AdminProductView extends JScrollPane {

	/**
	 * @var model
	 * Modello utilizzato dalla vista
	 */
	private AbstractTableModel model;
	
	/**
	 * @var table
	 * Tabella a cui è applicata la vista
	 */
	protected JTable table;
		
	/**
	 * @brief Costruttore
	 * @param m Modello da utilizzare
	 */
	public AdminProductView(AbstractTableModel m) {
		super();
		
		model = m;
		table = new JTable(model);
		
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setRowHeight(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
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
		
		setViewportView(table);
		
		refresh();
	}
	
	/**
	 * @brief Ricarica i valori contenuti nella tabella ed effettua resize intelligente delle colonne
	 */
	public void refresh(){
		model.fireTableDataChanged();
		for (int i = 0; i < table.getColumnCount(); i++) {
			adjustColumnSizes(table, i, 0);
        }	
	}
	
	/**
	 * @brief Ritorna l'indice della riga selezionata
	 * @return Indice riga selezionata o -1 se nessuna riga è selezionata
	 */
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	
	/**
	 * @brief Effettua resize intelligente delle colonne per adattarle al contenuto
	 * @param table Tabella a cui effettuare il resize
	 * @param column Colonna a cui effettuare il resize
	 * @param margin Margine
	 */
	private static void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        int currentWidth;
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, column), false, false, r, column);
            currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        col.setPreferredWidth(width);
    }
	
	
}
