package lt.jankunas.ui.actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lt.jankunas.shop.*;
import lt.jankunas.shop.helpers.GUIEmptyFieldValidator;
import lt.jankunas.shop.utils.*;
import lt.jankunas.ui.commands.GUIAddCommand;
import lt.jankunas.ui.commands.GUIInDBCommand;
import lt.jankunas.ui.commands.GUIInMemoryCommand;
import lt.jankunas.ui.commands.GUIRemoveCommand;
import lt.jankunas.ui.commands.GUIUpdateCommand;
import lt.jankunas.ui.selections.*;
import lt.jankunas.ui.views.*;

public class GUIDoInventoryButtonAction implements ActionListener{
	
	private JComboBox searchComboBox;
	private JTextField productNameField;
	private JTextField productQuantityField;
	private JTextField productPriceField;
	private Shop shop;
	private DefaultListModel inventoryModel;
	private JFrame frame;
	private GUIRenderInventoryListView prepareInventoryList;
	private GUIInventoryComboBoxSelection inventorySelection;
	private GUIEmptyFieldValidator fields;
	
	private Map<String, GUICommand> commands = new HashMap<String, GUICommand>();
	
	public GUIDoInventoryButtonAction(JComboBox searchComboBox, Shop shop, JTextField productPriceField, JTextField productQuantityField, JTextField productNameField, DefaultListModel inventoryModel, JFrame frame, GUIRenderInventoryListView prepareInventoryList){
		this.searchComboBox = searchComboBox;
		this.productNameField = productNameField;
		this.productQuantityField = productQuantityField;
		this.productPriceField = productPriceField;
		this.shop = shop;
		this.inventoryModel = inventoryModel;
		this.frame = frame;
		this.prepareInventoryList = prepareInventoryList;
		this.inventorySelection = new GUIInventoryComboBoxSelection(shop, inventoryModel, prepareInventoryList);
		this.fields = new GUIEmptyFieldValidator(productPriceField, productQuantityField, productNameField);
		initialize();
	}
	
	private void initialize(){
	    commands.put("ADD", new GUIAddCommand(inventorySelection, productPriceField, productQuantityField, productNameField, frame, fields));
        commands.put("UPDATE", new GUIUpdateCommand(inventorySelection, productQuantityField, productNameField, frame, fields));
        commands.put("REMOVE", new GUIRemoveCommand(inventorySelection, productNameField, frame, fields));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    GUICommand command = commands.get(searchComboBox.getSelectedItem());
        command.execute();	
		productNameField.setText("");
		productPriceField.setText("");
		productQuantityField.setText("");
	}
}
