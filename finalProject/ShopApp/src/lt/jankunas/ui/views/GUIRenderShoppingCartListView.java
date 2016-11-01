package lt.jankunas.ui.views;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import lt.jankunas.shop.*;

public class GUIRenderShoppingCartListView {
	
	private ShoppingCart shoppingCart;
	private DefaultListModel shoppingCartModel;
	private JList shoppingCartList;
	
	public GUIRenderShoppingCartListView(ShoppingCart shoppingCart, DefaultListModel shoppingCartModel, JList shoppingCartList){
		this.shoppingCart = shoppingCart;
		this.shoppingCartModel = shoppingCartModel;
		this.shoppingCartList = shoppingCartList;
	}
	
	public void render(){
		shoppingCartModel.clear();
		for(Product product : shoppingCart.getShoppingCartList()) {
			shoppingCartModel.addElement(product.getName());
		}
		shoppingCartList.setModel(shoppingCartModel);
	}
}