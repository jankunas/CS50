package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.ui.LogInWindow;

public class GUILogOutButtonAction implements ActionListener{

    LogInWindow logInWindow = new LogInWindow();
	private LogIn login;
    private Shop shop;
    private ShoppingCart shoppingCart;
    private ShopManager shopManager;
	private JPanel mainPanel;
	private DefaultListModel shoppingCartModel;
	private JFrame frame;
	private User currentUser;
	
	
	public GUILogOutButtonAction(ShoppingCart shoppingCart, LogIn login, Shop shop, ShopManager shopManager, JPanel mainPanel, DefaultListModel shoppingCartModel, JFrame frame, User currentUser){
		this.shoppingCart = shoppingCart;
		this.mainPanel = mainPanel;
		this.shoppingCartModel = shoppingCartModel;
		this.frame = frame;
		this.currentUser = currentUser;
		this.shop = shop;
		this.shopManager = shopManager;
		this.login = login;
	}
	
	public void actionPerformed(ActionEvent e){
		shoppingCart.delete();
		currentUser = null;
		mainPanel.setVisible(false);
		shoppingCartModel.clear();
		logInWindow.createLogInUI(login, shop, shoppingCart, shopManager);
		frame.dispose();
		JOptionPane.showMessageDialog(frame, "You have successfully logged out!");
	}
}
