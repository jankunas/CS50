package lt.jankunas.ui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.shop.User;
import lt.jankunas.shop.utils.FrameCreator;
import lt.jankunas.ui.actions.GUIAddToCartButtonAction;
import lt.jankunas.ui.actions.GUIBuyButtonAction;
import lt.jankunas.ui.actions.GUIDoInventoryButtonAction;
import lt.jankunas.ui.actions.GUIInventoryComboBoxAction;
import lt.jankunas.ui.actions.GUIInventoryListSelectionAction;
import lt.jankunas.ui.actions.GUILogOutButtonAction;
import lt.jankunas.ui.actions.GUIProductDetailsButtonAction;
import lt.jankunas.ui.actions.GUIRemoveFromCartButtonAction;
import lt.jankunas.ui.views.GUIRenderInventoryListView;
import lt.jankunas.ui.views.GUIRenderShoppingCartListView;

public class MainWindow {
    
    JPanel mainPanel = new JPanel();
    DefaultListModel inventoryModel = new DefaultListModel();
    DefaultListModel shoppingCartModel = new DefaultListModel();
    JLabel inventoryLabel = new JLabel("Items In Shop");
    JLabel shoppingCartLabel = new JLabel("Items In Shopping Cart");
    JLabel userInfoLabel = new JLabel("User's information");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel userCashLabel = new JLabel("Cash: ");
    JLabel totalSumLabel = new JLabel("Total amount: ");
    JLabel productNameLabel = new JLabel("Product's Name");
    JLabel productQuantityLabel = new JLabel("Product's Quantity");
    JLabel productPriceLabel = new JLabel("Product's Price");
    JLabel productSummaryLabel = new JLabel();
    JLabel quantityToCartLabel = new JLabel("Quantity");
    JLabel productDetailsLabel = new JLabel("Product Details:");
    JTextField productNameField = new JTextField();
    JTextField productQuantityField = new JTextField();
    JTextField productPriceField = new JTextField();
    JTextField productToCartQuantityField = new JTextField();
    JList inventoryList = new JList();
    JList shoppingCartList = new JList();
    JScrollPane inventoryListScroller = new JScrollPane(inventoryList);
    JScrollPane shoppingCartListScroller = new JScrollPane(shoppingCartList);
    JButton inventoryButton = new JButton("Do!");
    JButton addToCartButton = new JButton("Add >>");
    JButton buyButton = new JButton("Buy");
    JButton removeFromCartButton = new JButton("<< Remove");
    JButton productDetailsButton = new JButton("Details");
    JButton logoutButton = new JButton("Logout");
    JComboBox searchComboBox = new JComboBox(new String[] { "ADD", "REMOVE", "UPDATE" });
    
    private JFrame frame;
    
    public MainWindow(){
        this.frame = new JFrame("E - Shop");
    }
    
    public void createMainUI(LogIn login, Shop shop, ShoppingCart shoppingCart, ShopManager shopManager, User currentUser){
        FrameCreator.createFrame(frame);
        createMainPanel(currentUser);
        GUIRenderInventoryListView prepareInventoryList = new GUIRenderInventoryListView(shop, inventoryModel, inventoryList);
        GUIRenderShoppingCartListView prepareShoppingCartList = new GUIRenderShoppingCartListView(shoppingCart, shoppingCartModel, shoppingCartList);
        prepareInventoryList.render();
        prepareShoppingCartList.render();

        //Listeners
        inventoryList.getSelectionModel().addListSelectionListener(new GUIInventoryListSelectionAction(shoppingCartList));
        shoppingCartList.getSelectionModel().addListSelectionListener(new GUIInventoryListSelectionAction(inventoryList));
        productDetailsButton.addActionListener(new GUIProductDetailsButtonAction(frame, shoppingCart, shop, shoppingCartList, inventoryList, productSummaryLabel));
        logoutButton.addActionListener(new GUILogOutButtonAction(shoppingCart, login, shop, shopManager, mainPanel, shoppingCartModel, frame, currentUser));
        searchComboBox.addActionListener(new GUIInventoryComboBoxAction(productPriceLabel, productPriceField, productQuantityLabel, productQuantityField, searchComboBox));
        inventoryButton.addActionListener(new GUIDoInventoryButtonAction(searchComboBox, shop, productPriceField, productQuantityField, productNameField, inventoryModel, frame, prepareInventoryList));
        addToCartButton.addActionListener(new GUIAddToCartButtonAction(productToCartQuantityField, inventoryList, shoppingCartModel, shop, shoppingCart, totalSumLabel, frame, prepareShoppingCartList));
        removeFromCartButton.addActionListener(new GUIRemoveFromCartButtonAction(productToCartQuantityField, shoppingCartModel, shoppingCart, totalSumLabel, frame, shoppingCartList, prepareShoppingCartList));
        buyButton.addActionListener(new GUIBuyButtonAction(shoppingCart, shopManager, shop, inventoryModel, shoppingCartModel, totalSumLabel, userCashLabel, frame, prepareInventoryList, prepareShoppingCartList, login, currentUser));
    }
    
    private void createMainPanel(User currentUser){
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(null);
        usernameLabel.setText("Current user: "  + currentUser.getUsername());
        userCashLabel.setText("Cash left: " + currentUser.getCash());
        inventoryLabel.setBounds(200, 50, 200, 30);
        inventoryListScroller.setBounds(200, 80, 200, 200);
        productNameLabel.setBounds(25, 80, 150, 30);
        productNameField.setBounds(25, 110, 150, 30);
        productQuantityLabel.setBounds(25, 140, 150, 30);
        productQuantityField.setBounds(25, 170, 150, 30);
        productPriceLabel.setBounds(25, 200, 150, 30);
        productPriceField.setBounds(25, 230, 150, 30);
        searchComboBox.setBounds(25, 50, 150, 30);
        inventoryButton.setBounds(25, 290, 100, 30);
        addToCartButton.setBounds(425, 80, 100, 30);
        shoppingCartLabel.setBounds(550, 50, 200, 30);
        userInfoLabel.setBounds(780, 100, 200, 30);
        usernameLabel.setBounds(780, 140, 200, 30);
        userCashLabel.setBounds(780,180,200,30);
        totalSumLabel.setBounds(550, 280, 200, 30);
        shoppingCartListScroller.setBounds(550, 80, 200, 200);
        productToCartQuantityField.setBounds(425, 200, 100, 30);
        removeFromCartButton.setBounds(425, 120, 100, 30);
        quantityToCartLabel.setBounds(425, 160, 100, 30);
        productSummaryLabel.setBounds(200, 310, 300, 50);
        productDetailsButton.setBounds(425, 250, 100, 30);
        logoutButton.setBounds(780, 220, 100, 30);
        buyButton.setBounds(570, 320, 100, 30);
        productDetailsLabel.setBounds(200, 280, 300, 50);
        mainPanel.add(addToCartButton);
        mainPanel.add(inventoryButton);
        mainPanel.add(productNameLabel);
        mainPanel.add(productNameField);
        mainPanel.add(productQuantityLabel);
        mainPanel.add(productQuantityField);
        mainPanel.add(productPriceLabel);
        mainPanel.add(productPriceField);
        mainPanel.add(inventoryLabel);
        mainPanel.add(shoppingCartLabel);
        mainPanel.add(userInfoLabel);
        mainPanel.add(usernameLabel);
        mainPanel.add(userCashLabel);
        mainPanel.add(totalSumLabel);
        mainPanel.add(inventoryListScroller);
        mainPanel.add(shoppingCartListScroller);
        mainPanel.add(productToCartQuantityField);
        mainPanel.add(removeFromCartButton);
        mainPanel.add(productSummaryLabel);
        mainPanel.add(quantityToCartLabel);
        mainPanel.add(searchComboBox);
        mainPanel.add(productDetailsButton);
        mainPanel.add(logoutButton);
        mainPanel.add(buyButton);
        mainPanel.add(productDetailsLabel); 
    }

}
