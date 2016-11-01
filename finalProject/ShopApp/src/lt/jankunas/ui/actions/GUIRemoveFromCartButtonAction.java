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

public class GUIRemoveFromCartButtonAction implements ActionListener{

	private JTextField productToCartQuantityField;
	private DefaultListModel shoppingCartModel;
	private ShoppingCart shoppingCart;
	private JLabel totalSumLabel;
	private JFrame frame;
	private JList shoppingCartList;
	private GUIRenderShoppingCartListView prepareShoppingCartList;
	private String productQuantity;
	
	public GUIRemoveFromCartButtonAction(JTextField productToCartQuantityField, DefaultListModel shoppingCartModel, ShoppingCart shoppingCart, JLabel totalSumLabel, JFrame frame, JList shoppingCartList, GUIRenderShoppingCartListView prepareShoppingCartList){
		this.productToCartQuantityField = productToCartQuantityField;
		this.shoppingCartModel = shoppingCartModel;
		this.shoppingCart = shoppingCart;
		this.totalSumLabel = totalSumLabel;
		this.frame = frame;
		this.shoppingCartList = shoppingCartList;
		this.prepareShoppingCartList = prepareShoppingCartList;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		GUIAddOrRemoveCartProduct selectedInventoryItem = new GUIAddOrRemoveCartProduct(shoppingCart, shoppingCartModel, totalSumLabel, frame, prepareShoppingCartList);
		if(!productToCartQuantityField.getText().isEmpty() && !shoppingCartList.isSelectionEmpty()){
		    convertQuantityFieldTextToString();
			if(InputValidator.isQuantityValid(productQuantity)){
				String selectedProduct = shoppingCartList.getSelectedValue().toString();
				int quantityToRemove = Integer.parseInt(productToCartQuantityField.getText());
				selectedInventoryItem.remove(selectedProduct, quantityToRemove);
			} else
			    JOptionPane.showMessageDialog(frame, "Please check your entries");
		}
	}
	
	private void convertQuantityFieldTextToString(){
        productQuantity = productToCartQuantityField.getText().toString();
    }
}
