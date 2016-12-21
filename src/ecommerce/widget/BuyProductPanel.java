package ecommerce.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import ecommerce.basket.BasketManager;
import ecommerce.product.Product;
import ecommerce.product.Product3x2;
import ecommerce.product.ProductManager;
import ecommerce.utils.ImageLoader;

public class BuyProductPanel extends JPanel implements ActionListener, MouseListener, Transferable, DragSourceListener, DragGestureListener{

	private Product product;
	private JSpinner qtSpinner;
	private JButton addCartButton;

    private DragSource source;
    
    public static final String TRANSFER_HANDLER_PROP = "ProductPanel";
	
	public BuyProductPanel(Product product) {
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
						+ "<font size='4'><strike>€ %.2f</strike><font color='red'>&nbsp;&nbsp;-%d%%</font></font>"
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
						+ "<font size='4' color='red'>Offerta 3x2</font>"
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
		
		addCartButton = new JButton(new ImageIcon(BuyProductPanel.class.getResource("/image/addbasket.png")));
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
	
		//Create new DnD source
		source = new DragSource();
		source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
	
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(UIManager.getColor("List.selectionBackground"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(UIManager.getColor("Panel.background"));	
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{
				new DataFlavor(Product.class, "Product"),
				new DataFlavor(Integer.class, "Qt"),
			};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if(flavor.getRepresentationClass().equals(Product.class)){
			return ProductManager.getProductIndex(product);
		}
		else if(flavor.getRepresentationClass().equals(Integer.class)){
			return qtSpinner.getValue();
		}
		
		return null;
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		source.startDrag(dge, DragSource.DefaultMoveDrop, this, this);  
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {}

	@Override
	public void dragExit(DragSourceEvent dse) {}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		setBackground(UIManager.getColor("Panel.background"));	
		repaint();
	}
	
}
