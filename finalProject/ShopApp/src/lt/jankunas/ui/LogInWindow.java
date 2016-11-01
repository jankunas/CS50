package lt.jankunas.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.shop.utils.FrameCreator;
import lt.jankunas.ui.actions.GUILoginButtonAction;

public class LogInWindow {

    JPanel loginPanel = new JPanel();
    JLabel loginLabel = new JLabel("Login Name: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JTextField loginTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    JButton loginButton = new JButton("Sign In");

    private JFrame frame;

    public LogInWindow() {
        this.frame = new JFrame("Login Window");
    }

    public void createLogInUI(LogIn login, Shop shop, ShoppingCart shoppingCart, ShopManager shopManager) {
        FrameCreator.createFrame(frame);
        createLogInPanel();
        loginButton.addActionListener(new GUILoginButtonAction(loginTextField, passwordTextField, loginPanel, frame, login, shop, shoppingCart, shopManager));

    }

    private void createLogInPanel() {
        frame.getContentPane().add(loginPanel);
        loginPanel.setLayout(null);
        loginLabel.setBounds(300, 200, 200, 30);
        loginTextField.setBounds(400, 200, 200, 30);
        passwordLabel.setBounds(300, 230, 200, 30);
        passwordTextField.setBounds(400, 230, 200, 30);
        loginButton.setBounds(400, 270, 200, 30);
        loginPanel.add(loginLabel);
        loginPanel.add(loginTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordTextField);
        loginPanel.add(loginButton);
    }

}
