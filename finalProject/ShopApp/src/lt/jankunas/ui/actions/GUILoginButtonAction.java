package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.ui.MainGUIShopApplication;
import lt.jankunas.ui.MainWindow;

public class GUILoginButtonAction implements ActionListener {
	
    MainWindow mainUI = new MainWindow();
	private JTextField loginTextField;
	private JPasswordField passwordTextField;
	private JPanel loginPanel;
	private JFrame frame;
	private LogIn login;
	private User currentUser;
	private Shop shop;
	private ShoppingCart shoppingCart;
	private ShopManager shopManager;
	
	public GUILoginButtonAction(JTextField loginTextField, JPasswordField passwordTextField, JPanel loginPanel, JFrame frame, LogIn login, Shop shop, ShoppingCart shoppingCart, ShopManager shopManager){
		this.loginTextField = loginTextField;
		this.passwordTextField = passwordTextField;
		this.loginPanel = loginPanel;
		this.frame = frame;
		this.login = login;
		this.shop = shop;
		this.shoppingCart = shoppingCart;
		this.shopManager = shopManager;
	}

	public void actionPerformed(ActionEvent e){
		String username = loginTextField.getText();
		String password = passwordTextField.getText();
		if(login.isLoggedIn(username, password)){
			currentUser = login.getUser();
			loginPanel.setVisible(false);
			JOptionPane.showMessageDialog(frame, "You have successfully logged in");
			mainUI.createMainUI(login, shop, shoppingCart, shopManager, currentUser);
            frame.dispose();
		}else
			JOptionPane.showMessageDialog(frame, "Username or password are incorrect");
	}
	
}
