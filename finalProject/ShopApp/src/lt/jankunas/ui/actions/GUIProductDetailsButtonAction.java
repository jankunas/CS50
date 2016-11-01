package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import lt.jankunas.shop.*;

public class GUIProductDetailsButtonAction implements ActionListener{
	
	private JFrame frame;
	private ShoppingCart shoppingCart;
	private Shop shop;
	private JList shoppingCartList;
	private JList inventoryList;
	private JLabel productSummaryLabel;
	
	public GUIProductDetailsButtonAction(JFrame frame, ShoppingCart shoppingCart, Shop shop, JList shoppingCartList, JList inventoryList, JLabel productSummaryLabel){
		this.frame = frame;
		this.shop = shop;
		this.shoppingCart = shoppingCart;
		this.shoppingCartList = shoppingCartList;
		this.inventoryList = inventoryList;
		this.productSummaryLabel = productSummaryLabel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(inventoryList.isSelectionEmpty() && shoppingCartList.isSelectionEmpty()){
			JOptionPane.showMessageDialog(frame, "You must select a product for which you'd like to view the details for");
		} else if(!inventoryList.isSelectionEmpty()){
			Product selectedProduct = shop.getProductByName(inventoryList.getSelectedValue().toString());
			productSummaryLabel.setText("Product name: " + selectedProduct.getName() + " | QTY: " + selectedProduct.getQuantity() + " | Price: " + selectedProduct.getPrice() );
		} else if(!shoppingCartList.isSelectionEmpty()){
			Product selectedProduct = shoppingCart.getCartProductByName(shoppingCartList.getSelectedValue().toString());
			productSummaryLabel.setText("Product name: " + selectedProduct.getName() + " | QTY: " + selectedProduct.getQuantity() + " | Price: " + selectedProduct.getPrice() );
		}	
	}
	
}
