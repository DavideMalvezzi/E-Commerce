package ecommerce.panel.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import com.sun.xml.internal.org.jvnet.mimepull.DecodingException;

import ecommerce.product.Product;
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
		this.product = product;
		
		setSize(512, 280);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setTitle("Modifica un prodotto");
		
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
		priceField.setText("0.0");
		priceField.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
		discountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		discountSpinner.setEnabled(false);
		
		/*
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("path-to-file"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		imageLabel = new JLabel();
		imageLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imageLabel.setMinimumSize(new Dimension(170, 170));
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
											.addComponent(codeField)
											.addComponent(nameField)
											.addComponent(brandField)
											.addComponent(categoryField)
											.addComponent(priceField)
											.addComponent(discountSpinner)
								)
								.addComponent(imageLabel, 200, 200, 200)
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
			
			dispose();
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(discountedRadio)){
			discountSpinner.setEnabled(discountedRadio.isSelected());
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
