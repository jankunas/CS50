package lt.jankunas.ui.commands;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lt.jankunas.shop.GUICommand;
import lt.jankunas.shop.helpers.GUIEmptyFieldValidator;
import lt.jankunas.shop.utils.InputValidator;
import lt.jankunas.ui.selections.GUIInventoryComboBoxSelection;

public class GUIUpdateCommand implements GUICommand{

    private GUIInventoryComboBoxSelection inventorySelection;
    private JTextField productNameField;
    private JTextField productQuantityField;
    private JFrame frame;
    private String productName;
    private String productQuantity;
    private GUIEmptyFieldValidator fields;
    
    public GUIUpdateCommand(GUIInventoryComboBoxSelection inventorySelection, JTextField productQuantityField, JTextField productNameField, JFrame frame, GUIEmptyFieldValidator fields) {
        this.inventorySelection = inventorySelection;
        this.productNameField = productNameField;
        this.productQuantityField = productQuantityField;
        this.frame = frame;
        this.fields = fields;
    }
    
    public void execute() {
        if(fields.isUpdateInventoryFieldsEmpty())
            JOptionPane.showMessageDialog(frame, "All mandatory fields must be filled");
        else{
            convertNameQuantityFieldTextToString();
            if(InputValidator.isNameQuantityValid(productName, productQuantity))
                inventorySelection.updateInventory(productNameField, productQuantityField);
            else
                JOptionPane.showMessageDialog(frame, "Please check your entries");
        }
    }
    
    private void convertNameQuantityFieldTextToString(){
        productName = productNameField.getText().toString();
        productQuantity = productQuantityField.getText().toString();
    }

}
