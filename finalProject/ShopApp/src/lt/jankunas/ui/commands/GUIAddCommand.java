package lt.jankunas.ui.commands;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lt.jankunas.shop.GUICommand;
import lt.jankunas.shop.helpers.GUIEmptyFieldValidator;
import lt.jankunas.shop.utils.InputValidator;
import lt.jankunas.ui.selections.GUIInventoryComboBoxSelection;

public class GUIAddCommand implements GUICommand {

    private GUIInventoryComboBoxSelection inventorySelection;
    private JTextField productNameField;
    private JTextField productQuantityField;
    private JTextField productPriceField;
    private JFrame frame;
    private String productName;
    private String productQuantity;
    private String productPrice;
    private GUIEmptyFieldValidator fields;

    public GUIAddCommand(GUIInventoryComboBoxSelection inventorySelection, JTextField productPriceField,
            JTextField productQuantityField, JTextField productNameField, JFrame frame, GUIEmptyFieldValidator fields) {
        this.inventorySelection = inventorySelection;
        this.productNameField = productNameField;
        this.productQuantityField = productQuantityField;
        this.productPriceField = productPriceField;
        this.frame = frame;
        this.fields = fields;
    }

    @Override
    public void execute() {
        if(fields.isAddToInventoryFieldsEmpty())
            JOptionPane.showMessageDialog(frame, "All mandatory fields must be filled");
        else{
            convertNameQuantityPriceFieldTextToString();
            if(InputValidator.isNameQuantityPriceValid(productName, productQuantity, productPrice))
                inventorySelection.addToInventory(productNameField, productQuantityField, productPriceField);
            else
                JOptionPane.showMessageDialog(frame, "Please check your entries");
        }
    }

    private void convertNameQuantityPriceFieldTextToString() {
        productName = productNameField.getText().toString();
        productQuantity = productQuantityField.getText().toString();
        productPrice = productPriceField.getText().toString();
    }

}
