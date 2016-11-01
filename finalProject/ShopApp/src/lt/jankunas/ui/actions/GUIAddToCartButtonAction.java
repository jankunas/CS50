package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;
import lt.jankunas.ui.selections.*;
import lt.jankunas.ui.views.*;

public class GUIAddToCartButtonAction implements ActionListener{

	private JTextField productToCartQuantityField;
	private JList inventoryList;
	private DefaultListModel shoppingCartModel;
	private Shop shop;
	private ShoppingCart shoppingCart;
	private JLabel totalSumLabel;
	private JFrame frame;
	private GUIRenderShoppingCartListView prepareShoppingCartList;
	private String productQuantity;
	
	public GUIAddToCartButtonAction(JTextField productToCartQuantityField, JList inventoryList, DefaultListModel shoppingCartModel, Shop shop, ShoppingCart shoppingCart, JLabel totalSumLabel, JFrame frame, GUIRenderShoppingCartListView prepareShoppingCartList){
		this.productToCartQuantityField = productToCartQuantityField;
		this.inventoryList = inventoryList;
		this.shoppingCartModel = shoppingCartModel;
		this.shop = shop;
		this.shoppingCart = shoppingCart;
		this.totalSumLabel = totalSumLabel;
		this.frame = frame;
		this.prepareShoppingCartList = prepareShoppingCartList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		GUIAddOrRemoveCartProduct selectedInventoryItem = new GUIAddOrRemoveCartProduct(shoppingCart, shoppingCartModel, totalSumLabel, frame, prepareShoppingCartList);
		if(!productToCartQuantityField.getText().isEmpty() && !inventoryList.isSelectionEmpty()){
		    convertQuantityFieldTextToString();
			if(InputValidator.isQuantityValid(productQuantity)){
			    if(shoppingCart.getShoppingCartList().isEmpty()){
			        addToEmptyCart(selectedInventoryItem);
			    } else
			        addToExistingCart(selectedInventoryItem);
			} else
			    JOptionPane.showMessageDialog(frame, "Please check your entries");
		}else
			JOptionPane.showMessageDialog(frame, "You must select a product and enter quantity to add the product to your cart");
	}
	
	private void addToEmptyCart(GUIAddOrRemoveCartProduct selectedInventoryItem){
	    String selectedProduct = inventoryList.getSelectedValue().toString();
        int quantityInShop = shop.getProductByName(selectedProduct).getQuantity();
        int quantityToBuy = Integer.parseInt(productToCartQuantityField.getText());
        float productPrice = shop.getProductByName(selectedProduct).getPrice();
        selectedInventoryItem.addToEmptyCart(selectedProduct, quantityInShop, quantityToBuy, productPrice);
	}
	
	private void addToExistingCart(GUIAddOrRemoveCartProduct selectedInventoryItem){
	    String selectedProduct = inventoryList.getSelectedValue().toString();
        int quantityInShop = shop.getProductByName(selectedProduct).getQuantity();
        int quantityToBuy = Integer.parseInt(productToCartQuantityField.getText());
        int quantityInCart = shoppingCart.getCartProductByName(selectedProduct).getQuantity();
        float productPrice = shop.getProductByName(selectedProduct).getPrice();
        selectedInventoryItem.addToExistingCart(selectedProduct, quantityInShop, quantityToBuy, quantityInCart, productPrice);
	}
	
	private void convertQuantityFieldTextToString(){
        productQuantity = productToCartQuantityField.getText().toString();
    }
}
