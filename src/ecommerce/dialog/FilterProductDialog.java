package ecommerce.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ecommerce.product.ProductManager;
import ecommerce.product.filter.BrandFilter;
import ecommerce.product.filter.CategoryFilter;
import ecommerce.product.filter.DiscountFilter;
import ecommerce.product.filter.NameFilter;
import ecommerce.product.filter.PriceFilter;
import ecommerce.product.filter.ProductFilter;
import ecommerce.product.filter.ThreexTwoFilter;

public class FilterProductDialog extends JDialog implements ActionListener, ItemListener, ChangeListener{

	private Vector<ProductFilter> filters = null;

	private JTextField nameField;
	private JComboBox<String> categoryCombo;
	private JComboBox<String> brandsCombo;
	private JCheckBox priceCheck;
	private JSpinner minPriceSpinner, maxPriceSpinner;
	private JRadioButton normalCheck;
	private JRadioButton discountCheck;
	private JRadioButton threextwoCheck;
	
	private JButton applyButton;
	private JButton cancelButton;
	
	
	public FilterProductDialog() {
		JPanel jp = new JPanel();
		GroupLayout gLayout = new GroupLayout(jp);
		jp.setLayout(gLayout);
		
		JLabel nameLabel = new JLabel("Nome:");
		JLabel categoryLabel = new JLabel("Categoria:");
		JLabel brandLabel = new JLabel("Marca:");
		JLabel priceLabel = new JLabel("Prezzo:");
		JLabel sepLabel = new JLabel("-");
		JLabel offerLabel = new JLabel("Offerte:");

		nameField = new JTextField();
		
		Vector<String> categories = ProductManager.getProductCategoryList();
		categories.add(0, CategoryFilter.ALL_CATEGORIES);
		categoryCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(categories));
		
		Vector<String> brands = ProductManager.getProductBrandList();
		brands.add(0, BrandFilter.ALL_BRANDS);
		brandsCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(brands));
		
		priceCheck = new JCheckBox();
		priceCheck.addItemListener(this);
		minPriceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Short.MAX_VALUE, 1));
		minPriceSpinner.setEnabled(false);
		minPriceSpinner.addChangeListener(this);
		maxPriceSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Short.MAX_VALUE, 1));
		maxPriceSpinner.setEnabled(false);
		maxPriceSpinner.addChangeListener(this);
		
		normalCheck = new JRadioButton("Nessuna", true);
		discountCheck = new JRadioButton("Scontato");
		threextwoCheck = new JRadioButton("3x2");
		ButtonGroup bt = new ButtonGroup();
		bt.add(normalCheck);
		bt.add(discountCheck);
		bt.add(threextwoCheck);
	
		applyButton = new JButton("Applica");
		applyButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(nameField)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(categoryLabel)
								.addComponent(categoryCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(brandLabel)
								.addComponent(brandsCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(priceLabel)
								.addComponent(priceCheck)
								.addComponent(minPriceSpinner)
								.addComponent(sepLabel)
								.addComponent(maxPriceSpinner)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(offerLabel)
								.addComponent(normalCheck)
								.addComponent(discountCheck)
								.addComponent(threextwoCheck)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(applyButton)
								.addComponent(cancelButton)
					)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createParallelGroup(Alignment.CENTER)
					.addGroup(
						gLayout.createSequentialGroup()
							.addGroup(
									gLayout.createParallelGroup()
									.addComponent(nameLabel)
									.addComponent(categoryLabel)
									.addComponent(brandLabel)
									.addComponent(priceLabel)
									.addComponent(offerLabel)
							)
							.addGroup(
									gLayout.createParallelGroup()
									.addComponent(nameField)
									.addComponent(categoryCombo)
									.addComponent(brandsCombo)
									.addGroup(
											gLayout.createSequentialGroup()
												.addComponent(priceCheck)
												.addComponent(minPriceSpinner, 64, 64, 64)
												.addComponent(sepLabel)
												.addComponent(maxPriceSpinner, 64, 64, 64)
												
									)
									.addGroup(
											gLayout.createSequentialGroup()
												.addComponent(normalCheck)
												.addComponent(discountCheck)
												.addComponent(threextwoCheck)	
									)
						)
					)
					.addGroup(
							gLayout.createSequentialGroup()
								.addComponent(applyButton)
								.addComponent(cancelButton)
					)
				
		);
	
		jp.add(nameLabel);
		jp.add(categoryLabel);
		jp.add(brandLabel);
		jp.add(priceLabel);
		jp.add(sepLabel);
		jp.add(offerLabel);
		
		jp.add(nameField);
		jp.add(categoryCombo);
		jp.add(brandsCombo);
		jp.add(priceCheck);
		jp.add(minPriceSpinner);
		jp.add(maxPriceSpinner);
		jp.add(normalCheck);
		jp.add(discountCheck);
		jp.add(threextwoCheck);
		
		jp.add(applyButton);
		jp.add(cancelButton);
		
		
		add(jp);
		
		pack();
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
		if(e.getSource().equals(cancelButton)){
			dispose();
		}
		else if(e.getSource().equals(applyButton)){
			filters = new Vector<ProductFilter>();
			filters.add(new NameFilter(nameField.getText()));
			filters.add(new BrandFilter((String)brandsCombo.getSelectedItem()));
			filters.add(new CategoryFilter((String)categoryCombo.getSelectedItem()));
			
			if(priceCheck.isSelected()){
				filters.add(new PriceFilter((int)minPriceSpinner.getValue(), (int)maxPriceSpinner.getValue()));
			}
			
			if(discountCheck.isSelected()){
				filters.add(new DiscountFilter());
			}
			
			if(threextwoCheck.isSelected()){
				filters.add(new ThreexTwoFilter());
			}		
			
			dispose();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(minPriceSpinner)){
			if((int)minPriceSpinner.getValue() > (int)maxPriceSpinner.getValue()){
				maxPriceSpinner.setValue((int)minPriceSpinner.getValue() + 1);
			}
		}
		else if(e.getSource().equals(maxPriceSpinner)){
			if((int)minPriceSpinner.getValue() > (int)maxPriceSpinner.getValue()){
				minPriceSpinner.setValue((int)maxPriceSpinner.getValue() - 1);
			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(priceCheck)){
			minPriceSpinner.setEnabled(priceCheck.isSelected());
			maxPriceSpinner.setEnabled(priceCheck.isSelected());
		}
	}
	
}
