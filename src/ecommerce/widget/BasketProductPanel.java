package ecommerce.widget;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import ecommerce.basket.BasketManager;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.utils.ImageLoader;

public class BasketProductPanel extends JPanel implements ActionListener{


	private JButton removeButton;
	private Product product;
	
	
	public BasketProductPanel(Product product, int qt) {
		this.product = product;
		
		JLabel imgLabel = new JLabel();
		
		imgLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imgLabel.setPreferredSize(new Dimension(150, 150));
		imgLabel.setMinimumSize(imgLabel.getPreferredSize());
		imgLabel.setMaximumSize(imgLabel.getPreferredSize());
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		Image img = ImageLoader.loadImage(product.getImg(), imgLabel.getPreferredSize());
		if(img != null){
			imgLabel.setIcon(new ImageIcon(img));
		}
		else{
			imgLabel.setText("<html><p align='center'>Nessuna immagine<br>disponibile</p></html>");
		}
		
		JLabel nameLabel = new JLabel(product.getName());
		nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.BOLD, 28));
		
		JLabel priceLabel = new JLabel();
		
		if(product.getDiscount() > 0){
			priceLabel.setText(
					String.format(
						"<html>"
						+ "Prezzo: <font size='2'><strike>€ %.2f</strike>"
						+ "<font color='red'>&nbsp;&nbsp;-%d%%&nbsp;&nbsp;&nbsp;</font>"
						+ "</font>"
						+ "€ %.2f"
						+ "</html>", 
						product.getPrice(), product.getDiscount(), product.getFinalPrice()
					)
			);
		}
		else if(product instanceof Product3x2){
			priceLabel.setText(
					String.format(
						"<html>"
						+ "Prezzo: <font color='red'>Offerta 3x2 &nbsp;&nbsp;</font>"
						+ "€ %.2f"
						+ "</html>",
						product.getPrice()
					)
			);
		}
		else{
			priceLabel.setText(String.format("<html>Prezzo: € %.2f<html>", product.getPrice()));
		}		
		
		JLabel qtLabel = new JLabel("Quantità: " + qt);
		
		JLabel totLabel = new JLabel(String.format("Totale: € %.2f", product.getTotal(qt)));
		totLabel.setFont(new Font(totLabel.getFont().getFontName(), Font.PLAIN, 20));

		removeButton = new JButton("Rimuovi dal carrello");
		removeButton.addActionListener(this);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(imgLabel);
		add(nameLabel);
		add(priceLabel);
		add(qtLabel);
		add(totLabel);
		add(removeButton);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BasketManager.removeProduct(product);
		Container parent = getParent();
		parent.remove(this);
		parent.revalidate();
		parent.repaint();
	}

}
