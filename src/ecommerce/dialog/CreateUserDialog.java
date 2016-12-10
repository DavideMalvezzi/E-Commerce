package ecommerce.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ecommerce.user.UserManager;

public class CreateUserDialog extends JDialog implements ActionListener {

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;
	private JButton saveButton;
	
	public CreateUserDialog() {	
		//Set dialog parameters
		setSize(270, 160);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setTitle("Inserisci nuovo utente");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//Create GUI components
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		saveButton = new JButton("Salva");
		saveButton.addActionListener(this);
		
		JLabel usernameLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");
		
		//Create internal layout
		JPanel jp = new JPanel();
		GroupLayout gLayout = new GroupLayout(jp);
		jp.setLayout(gLayout);
		
		jp.add(usernameLabel);
		jp.add(usernameField);
		jp.add(passwordLabel);
		jp.add(passwordField);
		jp.add(saveButton);
		
		add(jp);
		
		//Add auto gaps to layout
		gLayout.setAutoCreateGaps(true);
		gLayout.setAutoCreateContainerGaps(true);
	    
	    //Set internal layout
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(usernameLabel)
								.addComponent(usernameField, 0, 20, 20)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(passwordLabel)
								.addComponent(passwordField, 0, 20, 20)
					)
					.addComponent(errorLabel)
					.addComponent(saveButton)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGroup(
							gLayout.createSequentialGroup()
							.addGroup(
									gLayout.createParallelGroup()
										.addComponent(usernameLabel)
										.addComponent(passwordLabel)
							)
							.addGroup(
									gLayout.createParallelGroup()
										.addComponent(usernameField)
										.addComponent(passwordField)
							)
					)
					.addComponent(errorLabel)
					.addComponent(saveButton)
			);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If save button is pressed
		if(e.getSource().equals(saveButton)){
			//Print error if the username field is empty
			if(getUsername().length() == 0){
				errorLabel.setText("L'username non può essere vuoto");
				return;
			}
			//Print error if the password field is empty
			if(getPassword().length() == 0){
				errorLabel.setText("La password non  essere vuota");
				return;
			}
			
			if(UserManager.userExists(getUsername(), getPassword())){
				errorLabel.setText("L'utente è già esistente");
				return;
			}
			
			//Close the dialog
			dispose();
		}
	}
	
	public String getUsername(){
		return usernameField.getText();
	}
	
	public String getPassword(){
		return new String(passwordField.getPassword());
	}
	
}
