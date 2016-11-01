package lt.jankunas.ui.commands;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lt.jankunas.shop.GUICommand;
import lt.jankunas.shop.helpers.GUIEmptyFieldValidator;
import lt.jankunas.shop.utils.InputValidator;
import lt.jankunas.ui.selections.GUIInventoryComboBoxSelection;

public class GUIRemoveCommand implements GUICommand{

    private GUIInventoryComboBoxSelection inventorySelection;
    private JTextField productNameField;
    private JFrame frame;
    private String productName;
    private GUIEmptyFieldValidator fields;

    public GUIRemoveCommand(GUIInventoryComboBoxSelection inventorySelection, JTextField productNameField, JFrame frame, GUIEmptyFieldValidator fields) {
        this.inventorySelection = inventorySelection;
        this.productNameField = productNameField;
        this.frame = frame;
        this.fields = fields;
    }
    
    public void execute() {
        if(fields.isRemoveInventoryFieldsEmpty())
            JOptionPane.showMessageDialog(frame, "All mandatory fields must be filled");
        else{
            convertNameFieldTextToString();
            if(InputValidator.isNameValid(productName))
                inventorySelection.removeFromInventory(productNameField);
            else
                JOptionPane.showMessageDialog(frame, "Please check your entries");
        }
    }
    
    private void convertNameFieldTextToString(){
        productName = productNameField.getText().toString();
    }
    

}
