package lt.jankunas.ui.views;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import lt.jankunas.shop.*;

public class GUIRenderInventoryListView {

	private Shop shop;
	private DefaultListModel inventoryModel;
	private JList inventoryList;
	
	public GUIRenderInventoryListView(Shop shop, DefaultListModel inventoryModel, JList inventoryList){
		this.shop = shop;
		this.inventoryModel = inventoryModel;
		this.inventoryList = inventoryList;
	}
	
	public void render(){
		inventoryModel.clear();
		for(Product product : shop.getListOfProducts()) {
		    inventoryModel.addElement(product.getName());
		}
		inventoryList.setModel(inventoryModel);
	}
}
