package ecommerce.widget.model;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import ecommerce.panel.AdminPanel;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.product.ProductManager;
import ecommerce.utils.ImageLoader;

/**
 * Classe che implementa il modello della tabella di gestione dei prodotti inserita nell' {@link AdminPanel}
 * @author Davide Malvezzi
 */
public class AdminViewModel extends AbstractTableModel {

	/**
	 * @var IMG_COL
	 * Indice della colonna contenente le immagini dei prodotti
	 */
	public static final int IMG_COL = 0;
	
	/**
	 * @var CODE_COL
	 * Indice della colonna contenente il codice dei prodotti
	 */
	public static final int CODE_COL = 1;
	
	/**
	 * @var NAME_COL
	 * Indice della colonna contenente il nome dei prodotti
	 */
	public static final int NAME_COL = 2;
	
	/**
	 * @var BRAND_COL
	 * Indice della colonna contenente la marca dei prodotti
	 */
	public static final int BRAND_COL = 3;
	
	/**
	 * @var CATEGORY_COL
	 * Indice della colonna contenente la categoria dei prodotti
	 */
	public static final int CATEGORY_COL = 4;
	
	/**
	 * @var PRICE_COL
	 * Indice della colonna contenente il prezzo dei prodotti
	 */
	public static final int PRICE_COL = 5;
	
	/**
	 * @var OFFER_COL
	 * Indice della colonna contenente le offerte dei prodotti 
	 */
	public static final int OFFER_COL = 6;
	
	/**
	 * @var productsImgCache
	 * HashMap che ad ogni path l'immagina associa l'immagine relativa.
	 */
	private HashMap<String, ImageIcon> productsImgCache;

	/**
	 * @brief Costruttore
	 */
	public AdminViewModel() {
		super();
		productsImgCache = new HashMap<String, ImageIcon>();
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di righe della tabella
	 */
	@Override
	public int getRowCount() {
		return ProductManager.getProductCount();
	}

	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di colonne della tabella
	 */
	@Override
	public int getColumnCount() {
		return 7;
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param columnIndex Indice della colonna
	 * @return Tipologia di classe contenuta in una specifica colonna
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == IMG_COL){
			return ImageIcon.class;
		}
		return String.class;
	}

	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param rowIndex Indice della riga
	 * @param columnIndex Indice della colonna
	 * @return Ritorna l'oggetto contenuto in una cella della tabella
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product product = ProductManager.getProduct(rowIndex);
		
		if(product != null){
			switch (columnIndex) {
				case IMG_COL:
					//If the icon is already loaded
					if(productsImgCache.containsKey(product.getImg())){
						//Return the cached img
						return productsImgCache.get(product.getImg());
					}
					
					//Else try to load the image
					Image img = ImageLoader.loadImage(product.getImg(), new Dimension(100, 100));
					//If the img exists
					if(img != null){
						//Add the img to the cache
						ImageIcon icon = new ImageIcon(img);
						productsImgCache.put(product.getImg(), icon);
						return icon;
					}
					return null;
					
				case CODE_COL:
					return product.getCode();
					
				case NAME_COL:
					return product.getName();
					
				case BRAND_COL:
					return product.getBrand();
					
				case CATEGORY_COL:
					return product.getCategory();
					
				case PRICE_COL:
					return "€ " + String.format("%.2f", product.getPrice());
					
				case OFFER_COL:
					if(product instanceof Product3x2){
						return "3x2";
					}
					else if(product.getDiscount() > 0){
						return Integer.toString(product.getDiscount()) + "%";
					}
					return "";
			}
		}
		
		return "";
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param columnIndex Indice della colonna
	 * @return Ritorna il nome della colonna 
	 */
	@Override
	public String getColumnName(int column) {
		switch (column) {
			case IMG_COL:
				return "Immagine";
		
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
				
			case OFFER_COL:
				return "Offerta";
		}	
		
		return "";
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Disabilita la modifica delle celle
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
