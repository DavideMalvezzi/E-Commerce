package ecommerce.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ecommerce.panel.dialog.CreateUserDialog;
import ecommerce.user.User;
import ecommerce.user.UserManager;

public class LoginPanel extends JPanel implements ActionListener {
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;
	private JButton loginButton;

	public LoginPanel(JFrame parentWindow) {	
		//Check if at least one user exists
		checkUsers();
		//Create GUI components
		createGUI();
	}

	private void createGUI(){
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
	    
	    //Set internal layout
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(welcomeLable)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(usernameLabel)
						.addComponent(usernameField, 0, 20, 20)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(passwordLabel)
						.addComponent(passwordField, 0, 20, 20)
					)
					.addComponent(errorLabel)
					.addComponent(loginButton)
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
								.addComponent(loginButton)
					)
		);
	}
	
	
	private void checkUsers(){
		if(UserManager.getUserCount() == 0){
			JOptionPane.showMessageDialog(this, "Benvenuto!\nAl momento non esistono utenti.\nInizia creando un utente amministratore");

			CreateUserDialog cuDialog = new CreateUserDialog();
			cuDialog.setVisible(true);
			
			UserManager.addAdmin(cuDialog.getUsername(), cuDialog.getPassword());
			UserManager.saveUsers();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(loginButton)){
			usernameField.setEnabled(false);
			passwordField.setEnabled(false);
			User loginUser = UserManager.getUser(usernameField.getText(), new String(passwordField.getPassword()));
			if(loginUser != null){
				JOptionPane.showConfirmDialog(this, "Login ok");
			}
			else{
				usernameField.setEnabled(true);
				passwordField.setEnabled(true);
				errorLabel.setText("Username o password invalid");
			}
		}
		
	}
}
