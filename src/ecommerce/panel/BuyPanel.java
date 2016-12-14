package ecommerce.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
		
		GroupLayout gLayout = new GroupLayout(this);
		setLayout(gLayout);
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
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
					.addGroup(
							gLayout.createParallelGroup()
							.addComponent(okButton)
							.addComponent(cancelButton)
					)
		);
		
		gLayout.setHorizontalGroup(
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
				);
		
		gLayout.linkSize(okButton, cancelButton);
		
		add(userLabel);
		add(addressLabel);
		add(cityLabel);
		add(provinceLabel);
		add(capLabel);
		add(phoneLabel);

		add(userField);
		add(addressField);
		add(cityField);
		add(provinceField);
		add(capField);
		add(phoneField);
		
		add(paymentLabel);
		add(ownerLabel);
		add(cardLabel);
		add(monthLabel);
		add(yearLabel);

		add(userField);
		add(addressField);
		add(cityField);
		add(provinceField);
		add(capField);
		add(phoneField);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancelButton)){
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
		else if(e.getSource().equals(okButton)){
			BasketManager.clear();
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
