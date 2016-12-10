package ecommerce.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ecommerce.basket.BasketManager;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.utils.ImageLoader;

public class ProductPanel extends JPanel implements ActionListener, MouseListener{

	private Product product;
	private JSpinner qtSpinner;
	private JButton addCartButton;
	
	public ProductPanel(Product product) {
		this.product = product;
		
		JLabel nameLabel = new JLabel();
		JLabel brandLabel = new JLabel();
		JLabel categoryLabel = new JLabel();
		JLabel priceLabel = new JLabel();
		JLabel imgLabel = new JLabel();
		JLabel offerLabel = new JLabel();
		JLabel qtLabel = new JLabel("Quantità:");
		
		
		nameLabel.setText(product.getName());
		nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.BOLD, 36));
		
		brandLabel.setText("Prodotto da " + product.getBrand());

		categoryLabel.setText("in " + product.getCategory());
		
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		offerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		if(product.getDiscount() > 0){
			offerLabel.setText(
					String.format(
						"<html>"
						+ "<font size='4'><strike>€ %.2f</strike><font color='red'>&nbsp;&nbsp;-%d%%&nbsp;&nbsp;&nbsp;</font></font><br>"
						+ "</html>", 
						product.getPrice(), product.getDiscount()
					)
			);
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getFinalPrice()));
		}
		else if(product instanceof Product3x2){
			offerLabel.setText(
					String.format(
						"<html>"
						+ "<font size='4' color='red'>Offerta 3x2 &nbsp;&nbsp;<br></font>"
						+ "</html>"
					)
			);
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getPrice()));
		}
		else{
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getPrice()));
		}

		
		priceLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.PLAIN, 28));

		
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setVerticalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imgLabel.setPreferredSize(new Dimension(192, 192));
		imgLabel.setMinimumSize(imgLabel.getPreferredSize());
		imgLabel.setMaximumSize(imgLabel.getPreferredSize());
		
		Image img = ImageLoader.loadImage(product.getImg(), imgLabel.getPreferredSize());
		if(img != null){
			imgLabel.setIcon(new ImageIcon(img));
		}
		else{
			imgLabel.setText("<html><p align='center'>Nessuna immagine<br>disponibile</p></html>");
		}
		
		qtSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Short.MAX_VALUE, 1));
		
		addCartButton = new JButton(new ImageIcon(ProductPanel.class.getResource("/image/addbasket.png")));
		addCartButton.addActionListener(this);

		
		GroupLayout gLayout = new GroupLayout(this);
		setLayout(gLayout);
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(
						gLayout.createParallelGroup()
							.addComponent(imgLabel)
							.addGroup(
									gLayout.createSequentialGroup()
										.addComponent(nameLabel)
										.addComponent(categoryLabel)
										.addGap(8)
										.addGroup(
												gLayout.createParallelGroup()
													.addComponent(brandLabel)
													.addComponent(offerLabel)
										)
										.addComponent(priceLabel)
										.addGap(16)
										.addGroup(
												gLayout.createParallelGroup(Alignment.CENTER)
													.addComponent(qtLabel)
													.addComponent(qtSpinner, 32, 32, 32)
													.addComponent(addCartButton)
										)
										
							)
					)
					.addGap(16)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(imgLabel)	
					.addGap(8)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(categoryLabel)
								.addComponent(brandLabel)
					)
					.addGroup(
							gLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(offerLabel)
											.addComponent(priceLabel)
								)
								.addGroup(
										gLayout.createSequentialGroup()
											.addComponent(qtLabel)
											.addComponent(qtSpinner, 48, 48, 48)
											.addComponent(addCartButton)
								)
					)
					.addGap(16)
		);
		
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addMouseListener(this);
	}
	
	public Product getProduct(){
		return product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addCartButton)){
			BasketManager.addProduct(product, (int) qtSpinner.getValue());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		setBackground(UIManager.getColor("List.selectionBackground"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(UIManager.getColor("Panel.background"));
		
	}
	
}
