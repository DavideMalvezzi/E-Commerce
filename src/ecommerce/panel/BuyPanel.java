package ecommerce.panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ecommerce.basket.BasketManager;
import ecommerce.utils.JTextFieldFilter;

public class BuyPanel extends CustomPanel implements KeyListener {
	
	public static final String TAG = "buy";

	private JTextField userField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField capField;
	private JTextField phoneField;
	
	private JComboBox<String> paymentCombo;
	private JTextField ownerField;
	private JTextField cardField;
	private JComboBox<String> monthCombo;
	private JComboBox<String> yearCombo;

	private JButton okButton;
	private JButton cancelButton;
	
	public BuyPanel(PanelManager panelManager) {
		super(panelManager);
		
		JLabel infoLabel = new JLabel(
				  "<html>"
				+	"<i><font size='4'>"
				+ 		"Inserisci le informazioni del punto di consegna e al metodo di pagamento per completare l'acquisto:"
				+  "</font></i>"
				+ "</html>"
				);
		
		JLabel userLabel = new JLabel("Nome e Cognome:");
		JLabel addressLabel = new JLabel("Indirizzo:");
		JLabel cityLabel = new JLabel("Città:");
		JLabel provinceLabel = new JLabel("Provincia:");
		JLabel capLabel = new JLabel("CAP:");
		JLabel phoneLabel = new JLabel("Telefono:");
		
		userField = new JTextField();
		addressField = new JTextField();
		cityField = new JTextField();	
		provinceField = new JTextField();
		capField = new JTextField();
		capField.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		capField.addKeyListener(this);
		phoneField = new JTextField();
		phoneField.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		
		JLabel paymentLabel = new JLabel("Tipo carta:");
		JLabel ownerLabel = new JLabel("Titolare:");
		JLabel cardLabel = new JLabel("Numero carta:");
		JLabel monthLabel = new JLabel("Mese scandenza:");
		JLabel yearLabel = new JLabel("Anno scandenza:");
		
		paymentCombo = new JComboBox<String>(new String[]{"Visa", "MasterCard", "PayPal", "PostePay", "American Express"});
		ownerField = new JTextField();
		cardField = new JTextField();
		cardField.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		monthCombo = new JComboBox<String>();
		for(int i = 1; i <= 12; i++){
			monthCombo.addItem(String.format("%02d", i));
		}
		yearCombo = new JComboBox<String>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = year; i < year + 20; i++){
			yearCombo.addItem(String.format("%d", i));
		}

		okButton = new JButton("OK");
		okButton.addActionListener(this);
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gbLayout);
		
		JPanel jp = new JPanel();
		GroupLayout gLayout = new GroupLayout(jp);
		jp.setLayout(gLayout);
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addComponent(infoLabel)
					.addGap(16)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(userLabel)
							.addComponent(userField)
							.addComponent(paymentLabel)
							.addComponent(paymentCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(addressLabel)
							.addComponent(addressField)
							.addComponent(ownerLabel)
							.addComponent(ownerField)
					)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(cityLabel)
							.addComponent(cityField)
							.addComponent(cardLabel)
							.addComponent(cardField)
					)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(provinceLabel)
							.addComponent(provinceField)
							.addComponent(monthLabel)
							.addComponent(monthCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(capField)
							.addComponent(capLabel)
							.addComponent(yearLabel)
							.addComponent(yearCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(phoneLabel)
							.addComponent(phoneField)
					)
					.addGap(16)
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(okButton)
							.addComponent(cancelButton)
					)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createParallelGroup()
					.addComponent(infoLabel)
					.addGroup(
							gLayout.createSequentialGroup()
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(userLabel)
											.addComponent(addressLabel)
											.addComponent(cityLabel)
											.addComponent(provinceLabel)
											.addComponent(capLabel)
											.addComponent(phoneLabel)
								)
								.addGroup(
										gLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(userField)
											.addComponent(addressField)
											.addComponent(cityField)
											.addComponent(provinceField)
											.addComponent(capField)
											.addComponent(phoneField)
											.addComponent(okButton)
								)
								.addGroup(
										gLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(paymentLabel)
											.addComponent(ownerLabel)
											.addComponent(cardLabel)
											.addComponent(monthLabel)
											.addComponent(yearLabel)
											.addComponent(cancelButton)
								)
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(paymentCombo)
											.addComponent(ownerField)
											.addComponent(cardField)
											.addComponent(monthCombo)
											.addComponent(yearCombo)
								)
							)
				
		);
		
		gLayout.linkSize(okButton, cancelButton);
		
		c.fill = GridBagConstraints.BOTH ;
		c.insets = new Insets(32, 32, 32, 32); 
		c.weightx = 0.8f;
		c.anchor = GridBagConstraints.PAGE_START;
		add(jp, c);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancelButton)){
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
		else if(e.getSource().equals(okButton)){
			//TODO: check if fields are empty
			JOptionPane.showMessageDialog(this, "L'ordine è stato inoltrato con successo");
			BasketManager.clear();
			panelManager.setCurrentPanel(ClientPanel.TAG);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource().equals(capField)){
			if(capField.getText().length() > 4){
				capField.setText(capField.getText().substring(0, 4));
				e.consume();
			}
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
