package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import lt.jankunas.shop.*;
import lt.jankunas.ui.views.*;

public class GUIBuyButtonAction implements ActionListener {
	
	private ShoppingCart shoppingCart;
	private ShopManager shopManager;
	private Shop shop;
	private DefaultListModel inventoryModel;
	private DefaultListModel shoppingCartModel;
	private JLabel totalSumLabel;
	private JLabel userCashLabel;
	private JFrame frame;
	private GUIRenderInventoryListView prepareInventoryList;
	private GUIRenderShoppingCartListView prepareShoppingCartList;
	private LogIn login;
	private User currentUser;

	public GUIBuyButtonAction(ShoppingCart shoppingCart, ShopManager shopManager, Shop shop, DefaultListModel inventoryModel, DefaultListModel shoppingCartModel, JLabel totalSumLabel, JLabel userCashLabel, JFrame frame, GUIRenderInventoryListView prepareInventoryList, GUIRenderShoppingCartListView prepareShoppingCartList, LogIn login, User currentUser){
		this.shoppingCart = shoppingCart;
		this.shopManager = shopManager;
		this.shop = shop;
		this.inventoryModel = inventoryModel;
		this.shoppingCartModel = shoppingCartModel;
		this.totalSumLabel = totalSumLabel;
		this.userCashLabel = userCashLabel;
		this.frame = frame;
		this.prepareInventoryList = prepareInventoryList;
		this.prepareShoppingCartList = prepareShoppingCartList;
		this.login = login;
		this.currentUser = currentUser;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(currentUser.getCash() >= shoppingCart.getSum() && !shoppingCart.getShoppingCartList().isEmpty()){
			shopManager.buy(shoppingCart, shop, currentUser);
			prepareInventoryList.render();
			prepareShoppingCartList.render();
			totalSumLabel.setText("Total amount to pay: " + Float.toString(shoppingCart.getSum()));
			userCashLabel.setText("Cash left: " + currentUser.getCash());
			JOptionPane.showMessageDialog(frame, "Items were successfully bought!");
		} else
			JOptionPane.showMessageDialog(frame, "Not Enough Cash or not items were added to cart");
	}
}
