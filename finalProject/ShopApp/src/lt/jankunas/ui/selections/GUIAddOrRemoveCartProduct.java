package lt.jankunas.ui.selections;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import lt.jankunas.shop.*;
import lt.jankunas.ui.views.*;

public class GUIAddOrRemoveCartProduct {

	private DefaultListModel shoppingCartModel;
	private JLabel totalSumLabel;
	private JFrame frame;
	private ShoppingCart shoppingCart;
	private GUIRenderShoppingCartListView prepareShoppingCartList;
	
	public GUIAddOrRemoveCartProduct(ShoppingCart shoppingCart, DefaultListModel shoppingCartModel, JLabel totalSumLabel, JFrame frame, GUIRenderShoppingCartListView prepareShoppingCartList){
		this.shoppingCartModel = shoppingCartModel;
		this.totalSumLabel = totalSumLabel;
		this.frame = frame;
		this.shoppingCart = shoppingCart;
		this.prepareShoppingCartList = prepareShoppingCartList;
	}
	
	public void addToExistingCart(String selectedProduct, int quantityInShop, int quantityToBuy, int quantityInCart, float productPrice){
			if(quantityInShop >= quantityToBuy + quantityInCart){
				shoppingCart.add(new Product(selectedProduct, quantityToBuy, productPrice));
				prepareShoppingCartList.render();
				totalSumLabel.setText("Total amount to pay: " + Float.toString(shoppingCart.getSum()));
			} else
				JOptionPane.showMessageDialog(frame, "You cannot add more items to your cart than it is in inventory");
	}
	
	public void addToEmptyCart(String selectedProduct, int quantityInShop, int quantityToBuy, float productPrice){
        if(quantityInShop >= quantityToBuy){
            shoppingCart.add(new Product(selectedProduct, quantityToBuy, productPrice));
            prepareShoppingCartList.render();
            totalSumLabel.setText("Total amount to pay: " + Float.toString(shoppingCart.getSum()));
        } else
            JOptionPane.showMessageDialog(frame, "You cannot add more items to your cart than it is in inventory");
}
	
	public void remove(String selectedProduct, int quantityToRemove){
		shoppingCart.remove(selectedProduct, quantityToRemove);
		prepareShoppingCartList.render();
		totalSumLabel.setText("Total amount to pay: " + Float.toString(shoppingCart.getSum()));
	}
}
