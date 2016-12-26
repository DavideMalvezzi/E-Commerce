package ecommerce.panel;

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

/**
 * Classe che implementa la schermata di acquisto dei prodotti
 * @author Davide Malvezzi
 */
public class BuyPanel extends CustomPanel implements KeyListener {
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "buy";

	/**
	 * @var userField
	 * Casella di testo per l'immissione del nome e cognome
	 */
	private JTextField userField;
	
	/**
	 * @var addressField
	 * Casella di testo per l'immissione dell'indirizzo di spedizione
	 */
	private JTextField addressField;
	
	/**
	 * @var cityField
	 * Casella di testo per l'immissione della città di spedizione
	 */
	private JTextField cityField;
	
	/**
	 * @var provinceField
	 * Casella di testo per l'immissione della provincia di spedizione
	 */
	private JTextField provinceField;
	
	/**
	 * @var capField
	 * Casella di testo per l'immissione del CAP
	 */
	private JTextField capField;
	
	/**
	 * @var phoneField
	 * Casella di testo per l'immissione del numero di telefono
	 */
	private JTextField phoneField;
	
	/**
	 * @var paymentCombo
	 * Combobox per la scelta del metodo di pagamento
	 */
	private JComboBox<String> paymentCombo;
	
	/**
	 * @var ownerField
	 * Casella di testo per l'immissione del proprietario della carta
	 */
	private JTextField ownerField;
	
	/**
	 * @var cardField
	 * Casella di testo per l'immissione del numero della carta
	 */
	private JTextField cardField;
	
	/**
	 * @var monthCombo
	 * Combobox per la scelta del mese di scadenza della carta
	 */
	private JComboBox<String> monthCombo;
	
	/**
	 * @var yearCombo
	 * Combobox per la scelta dell'anno di scadenza della carta
	 */
	private JComboBox<String> yearCombo;

	/**
	 * @var okButton
	 * Bottone di conferma dell'acquisto
	 */
	private JButton okButton;
	
	/**
	 * @var cancelButton
	 * Bottone per annullare l'acquisto e tornare al carrello 
	 */
	private JButton cancelButton;
	
	/**
	 * @brief Costruttore
	 * @param panelManager Finestra
	 */
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
		c.insets = new Insets(32, 64, 32, 64); 
		c.weightx = 0.8f;
		c.anchor = GridBagConstraints.PAGE_START;
		add(jp, c);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Go back to basket panel
		if(e.getSource().equals(cancelButton)){
			panelManager.setCurrentPanel(BasketPanel.TAG);
		}
		
		else if(e.getSource().equals(okButton)){
			//Check if at least a field is empty 
			if(userField.getText().replaceAll(" ", "").length() == 0 ||
					addressField.getText().replaceAll(" ", "").length() == 0 ||
					cityField.getText().replaceAll(" ", "").length() == 0 ||
					provinceField.getText().replaceAll(" ", "").length() == 0 ||
					capField.getText().replaceAll(" ", "").length() == 0 ||
					phoneField.getText().replaceAll(" ", "").length() == 0){
				
				JOptionPane.showMessageDialog(this, "Uno dei campi relativi al punto di consegna è vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			//Check if at least a field is empty 
			else if(ownerField.getText().replaceAll(" ", "").length() == 0 || cardField.getText().replaceAll(" ", "").length() == 0){
				JOptionPane.showMessageDialog(this, "Uno dei campi relativi al metodo di pagamento è vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(this, "L'ordine è stato inoltrato con successo");
				BasketManager.clear();
				panelManager.setCurrentPanel(ClientPanel.TAG);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//Max 5 digit in the cap field
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
