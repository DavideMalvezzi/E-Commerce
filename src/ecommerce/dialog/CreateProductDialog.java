package ecommerce.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ecommerce.product.DiscountedProduct;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.utils.ImageLoader;
import ecommerce.utils.JTextFieldFilter;

public class CreateProductDialog extends JDialog implements ActionListener, MouseListener, ItemListener{
	
	private Product product;
	
	private JTextField codeField;
	private JTextField nameField;
	private JTextField brandField;
	private JTextField categoryField;
	private JTextField priceField;
	private JSpinner discountSpinner;
	private JLabel imageLabel;
	private String imagePath;
	
	private JRadioButton normalRadio;
	private JRadioButton discountedRadio;
	private JRadioButton threextwoRadio;
	
	private JButton saveButton;
	private JButton cancelButton;
	
	
	public CreateProductDialog() {
		this(null);
		setTitle("Inserisci nuovo prodotto");
	}
	
	public CreateProductDialog(Product product) {		
		//Create GUI components
		JLabel codeLabel = new JLabel("Codice:");
		JLabel nameLabel = new JLabel("Nome:");
		JLabel brandLabel = new JLabel("Marca:");
		JLabel categoryLabel = new JLabel("Categoria:");
		JLabel priceLabel = new JLabel("Prezzo:");
		JLabel discountLabel = new JLabel("Sconto:");
		
		codeField = new JTextField();
		nameField = new JTextField();
		brandField = new JTextField();
		categoryField = new JTextField();
		priceField = new JTextField();
		priceField.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
		priceField.setText("0.0");
		discountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		discountSpinner.setEnabled(false);

		imageLabel = new JLabel("Click per selezionare un'immagine");
		imageLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imageLabel.setMinimumSize(new Dimension(200, 150));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setVerticalAlignment(SwingConstants.CENTER);
		imageLabel.addMouseListener(this);
		
	    ButtonGroup group = new ButtonGroup();
	    
		normalRadio = new JRadioButton("No offerte", true);
		normalRadio.addItemListener(this);
		
		discountedRadio = new JRadioButton("Sconto");
		discountedRadio.addItemListener(this);
		
		threextwoRadio = new JRadioButton("3x2");
		threextwoRadio.addItemListener(this);
		
		group.add(normalRadio);
		group.add(discountedRadio);
		group.add(threextwoRadio);

		saveButton = new JButton("Salva");
		saveButton.addActionListener(this);
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		JPanel container = new JPanel();
		GroupLayout gLayout = new GroupLayout(container);
		container.setLayout(gLayout);
		
		//Add auto gaps to layout
		gLayout.setAutoCreateGaps(true);
		gLayout.setAutoCreateContainerGaps(true);
		
		//Create the layout
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGroup(
						gLayout.createParallelGroup()
							.addGroup(
								gLayout.createSequentialGroup()
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(codeLabel)
												.addComponent(codeField)
									)
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(nameLabel)
												.addComponent(nameField)
									)
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(brandLabel)
												.addComponent(brandField)
									)
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(categoryLabel)
												.addComponent(categoryField)
									)
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(priceLabel)
												.addComponent(priceField)
									)
									.addGroup(
											gLayout.createParallelGroup()
												.addComponent(discountLabel)
												.addComponent(discountSpinner)
									)
								)
							.addComponent(imageLabel)	
					)

					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(normalRadio)
								.addComponent(discountedRadio)
								.addComponent(threextwoRadio)		
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(saveButton)
								.addComponent(cancelButton)
					)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGroup(
							gLayout.createSequentialGroup()
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(codeLabel) 
											.addComponent(nameLabel)
											.addComponent(brandLabel) 
											.addComponent(categoryLabel)
											.addComponent(priceLabel) 
											.addComponent(discountLabel) 
								)
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(codeField, 128, 128, 128)
											.addComponent(nameField, 128, 128, 128)
											.addComponent(brandField, 128, 128, 128)
											.addComponent(categoryField, 128, 128, 128)
											.addComponent(priceField, 128, 128, 128)
											.addComponent(discountSpinner, 128, 128, 128)
								)
								.addComponent(imageLabel)
					)
					.addGroup(
							gLayout.createSequentialGroup()
								.addComponent(normalRadio)
								.addComponent(discountedRadio)
								.addComponent(threextwoRadio)
					)
					.addGroup(
							gLayout.createSequentialGroup()
								.addComponent(saveButton)
								.addComponent(cancelButton)
					)
		);
		
		//Add all to the panel
		container.add(codeLabel);
		container.add(codeField);
		
		container.add(nameLabel);
		container.add(nameField);
		
		container.add(brandLabel);
		container.add(brandField);
		
		container.add(categoryLabel);
		container.add(categoryField);
		
		container.add(priceLabel);
		container.add(priceField);
		
		container.add(discountLabel);
		container.add(discountSpinner);
		
		container.add(imageLabel);
		
		container.add(normalRadio);
		container.add(discountedRadio);
		container.add(threextwoRadio);
		
		container.add(saveButton);
		container.add(cancelButton);
		
		add(container);
		
		//Load product attributes if is not null
		if(product != null){
			codeField.setText(product.getCode());
			nameField.setText(product.getName());
			brandField.setText(product.getBrand());
			categoryField.setText(product.getCategory());
			priceField.setText(Float.toString(product.getPrice()));
			discountSpinner.setValue(product.getDiscount());
			
			imagePath = product.getImg();
			if(product.getImg() != null){
				imageLabel.setIcon(new ImageIcon(ImageLoader.loadImage(product.getImg(), imageLabel.getMinimumSize())));
				imageLabel.setText("");
			}
			
			if(product instanceof Product3x2){
				threextwoRadio.setSelected(true);
			}
			else if(product instanceof DiscountedProduct){
				discountedRadio.setSelected(true);
			}
			else{
				normalRadio.setSelected(true);
			}
			
		}
		product = null;
		
		
		//Set dialog options
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Modifica un prodotto");
		setLocationRelativeTo(null);
		
	}
	
	public Product getProduct(){
		return product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cancelButton)) {
			product = null;
			dispose();
		}
		else if(e.getSource().equals(saveButton)){
			//Create product instance based on the type selection
			if(normalRadio.isSelected()){
				product = new Product();
			}
			else if(discountedRadio.isSelected()){
				product = new DiscountedProduct();
			}
			else{
				product = new Product3x2();
			}
			
			//TODO: check if fields are empty
			
			//Set product attributes
			product.setCode(codeField.getText());
			product.setName(nameField.getText());
			product.setCategory(categoryField.getText());
			product.setBrand(brandField.getText());
			product.setPrice(Float.parseFloat(priceField.getText()));
			product.setDiscount((Integer)discountSpinner.getValue());
			product.setImg(imagePath);
			dispose();
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//Enable discount spinner if the product has discount
		if(e.getSource().equals(discountedRadio)){
			discountSpinner.setEnabled(discountedRadio.isSelected());
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Load image if the image label is clicked
		JFileChooser fChooser = new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		fChooser.setFileFilter(imageFilter);
		
		if(fChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			imagePath = fChooser.getSelectedFile().getAbsolutePath();
			try {
				imageLabel.setIcon(new ImageIcon(ImageLoader.loadImage(imagePath, imageLabel.getSize())));
				imageLabel.setText("");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
