package lt.jankunas.ui;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.ui.actions.*;
import lt.jankunas.ui.selections.*;
import lt.jankunas.ui.views.*;

public class MainGUIShopApplication {
    
    public static void main(String[] args) {
        ShopInitializerWindow shopInitializer = new ShopInitializerWindow();
        shopInitializer.createInitializerUI();
    }
}
