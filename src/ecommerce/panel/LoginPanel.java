package ecommerce.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ecommerce.panel.dialog.CreateUserDialog;
import ecommerce.user.User;
import ecommerce.user.UserManager;

public class LoginPanel extends CustomPanel {
	
	public static final String TAG = "login";
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;
	private JButton loginButton;
	private JButton createButton;

	public LoginPanel(PanelManager panelManager) {	
		super(panelManager);
		
		//Check if at least one user exists
		checkUsers();

		//Create GUI components
		JLabel welcomeLable = new JLabel("Benvenuto");
		welcomeLable.setFont(new Font(welcomeLable.getFont().getFontName(), Font.BOLD, 32));
		JLabel usernameLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		createButton = new JButton("Crea utente");
		createButton.addActionListener(this);
		
		//Create internal panel and layout
		JPanel jp = new JPanel();
		GroupLayout layout = new GroupLayout(jp);
		jp.setLayout(layout);
		
		jp.add(welcomeLable);
		jp.add(usernameLabel);
		jp.add(usernameField);
		jp.add(passwordLabel);
		jp.add(passwordField);
		jp.add(errorLabel);
		jp.add(loginButton);
		
		//Create layout
		GridBagLayout gbLayout = new GridBagLayout();
		setLayout(gbLayout);
		add(jp);
		
		//Add auto gaps to layout
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.linkSize(createButton, loginButton);
	    
	    //Set internal layout
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(welcomeLable)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(usernameLabel)
								.addComponent(usernameField, 0, 20, 20)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(passwordLabel)
								.addComponent(passwordField, 0, 20, 20)
					)
					.addComponent(errorLabel)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(loginButton)
							.addComponent(createButton)
					)
		);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(welcomeLable)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(usernameLabel)
											.addComponent(passwordLabel)
									)
									.addGroup(layout.createParallelGroup()
											.addComponent(usernameField, 0, 32, 128)
											.addComponent(passwordField, 0, 32, 128)
									)
								)
								.addComponent(errorLabel)
								.addGroup(layout.createSequentialGroup()
										.addComponent(createButton)
										.addComponent(loginButton)
								)
								
					)
		);
	}

	@Override
	public void onEnter() {
		//Check if at least one user exists
		checkUsers();
		
		//Reset fields
		usernameField.setEnabled(true);
		passwordField.setEnabled(true);
		passwordField.setText("");
		errorLabel.setText("");
	}

	private void checkUsers(){
		//If there aren't users create the admin user first
		if(UserManager.getUserCount() == 0){
			JOptionPane.showMessageDialog(this, "Benvenuto!\nAl momento non esistono utenti.\nInizia creando un utente amministratore");

			//Show the create users dialog
			CreateUserDialog cuDialog = new CreateUserDialog();
			cuDialog.setVisible(true);
			
			//Add admin and save users file
			UserManager.addAdmin(cuDialog.getUsername(), cuDialog.getPassword());
			if(!UserManager.saveUsers()){
				JOptionPane.showMessageDialog(this, "Errore durante il salvataggio del file degli utenti.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If login button is pressed
		if(e.getSource().equals(loginButton)){
			//Disable username and password field
			usernameField.setEnabled(false);
			passwordField.setEnabled(false);
			
			//Check if password is not empty
			if(passwordField.getPassword().length != 0){
				//Get the user
				User loginUser = UserManager.getUser(usernameField.getText(), new String(passwordField.getPassword()));
				
				//If user exists
				if(loginUser != null){
					//If user is admin open admin panel
					if(loginUser.isAdmin()){
						panelManager.setCurrentPanel(AdminPanel.TAG);
					}
					//Else open user panel
					else{
						panelManager.setCurrentPanel(ClientPanel.TAG);
					}
				}
				else{
					//Login error
					errorLabel.setText("Username o password invalidi");
				}
			}
			else{
				//Login error
				errorLabel.setText("Username o password invalidi");

			}
			//Enable username and password field
			usernameField.setEnabled(true);
			passwordField.setEnabled(true);
			
		}
		
		else if(e.getSource().equals(createButton)){
			CreateUserDialog cuDialog = new CreateUserDialog();
			cuDialog.setVisible(true);

			UserManager.addUser(cuDialog.getUsername(), cuDialog.getPassword());
			if(!UserManager.saveUsers()){
				JOptionPane.showMessageDialog(this, "Errore durante il salvataggio del file degli utenti.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
