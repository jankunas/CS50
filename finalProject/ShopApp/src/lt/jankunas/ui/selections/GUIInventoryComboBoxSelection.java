package lt.jankunas.ui.selections;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.ui.views.*;

public class GUIInventoryComboBoxSelection {
	
	private String productName;
	private int productQuantity;
	private float productPrice;
	private Shop shop;
	private DefaultListModel inventoryModel;
	private GUIRenderInventoryListView prepareInventoryList;
	
	public GUIInventoryComboBoxSelection(Shop shop, DefaultListModel inventoryModel, GUIRenderInventoryListView prepareInventoryList){
		this.shop = shop;
		this.inventoryModel = inventoryModel;
		this.prepareInventoryList = prepareInventoryList;
	}
	
	public void addToInventory(JTextField productNameField, JTextField productQuantityField, JTextField productPriceField){
		productName = productNameField.getText().toString();
		productQuantity = Integer.parseInt(productQuantityField.getText());
		productPrice = Float.parseFloat(productPriceField.getText());
		Product newProduct = new Product(productName, productQuantity, productPrice);
		shop.add(newProduct);
		prepareInventoryList.render();
	}
	public void removeFromInventory(JTextField productNameField){
		productName = productNameField.getText().toString();
		shop.remove(productName);
		prepareInventoryList.render();
	}
	public void updateInventory(JTextField productNameField, JTextField productQuantityField){
		productName = productNameField.getText().toString();
		productQuantity = Integer.parseInt(productQuantityField.getText());
		shop.update(productName, productQuantity);
		prepareInventoryList.render();
	}
}
