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

import ecommerce.user.User;
import ecommerce.user.UserManager;

public class CreateUserDialog extends JDialog implements ActionListener {

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;
	private JButton saveButton;
	private JButton cancelButton;
	
	private User user = null;
	
	
	public CreateUserDialog() {	
		//Create GUI components
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
		saveButton = new JButton("Salva");
		saveButton.addActionListener(this);
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
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
		jp.add(cancelButton);
		
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
								.addComponent(usernameField)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(passwordLabel)
								.addComponent(passwordField)
					)
					.addComponent(errorLabel)
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
										.addComponent(usernameLabel)
										.addComponent(passwordLabel)
							)
							.addGroup(
									gLayout.createParallelGroup()
										.addComponent(usernameField, 100, 100, 100)
										.addComponent(passwordField, 100, 100, 100)
							)
					)
					.addComponent(errorLabel)
					.addGroup(
							gLayout.createSequentialGroup()
								.addComponent(saveButton)
								.addComponent(cancelButton)
					)
			);
		gLayout.linkSize(cancelButton, saveButton);
		
		
		//Set dialog parameters
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setTitle("Inserisci nuovo utente");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(cancelButton)){
			user = null;
			dispose();
		}
		
		else if(e.getSource().equals(saveButton)){
			//Print error if the username field is empty
			if(usernameField.getText().length() == 0){
				errorLabel.setText("L'username non può essere vuoto");
				return;
			}
			//Print error if the password field is empty
			if(getPassword().length() == 0){
				errorLabel.setText("La password non  essere vuota");
				return;
			}
			
			if(UserManager.userExists(usernameField.getText(), getPassword())){
				errorLabel.setText("L'utente è già esistente");
				return;
			}
			user = new User(usernameField.getText(), getPassword());
			
			//Close the dialog
			dispose();
		}
	}
	
	public User getUser(){
		return user;
	}
	
	private String getPassword(){
		return new String(passwordField.getPassword());
	}
	
}
