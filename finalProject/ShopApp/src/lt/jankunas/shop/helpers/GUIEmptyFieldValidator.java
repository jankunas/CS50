package lt.jankunas.shop.helpers;
import javax.swing.JTextField;

public class GUIEmptyFieldValidator {

	private JTextField productNameField;
	private JTextField productQuantityField;
	private JTextField productPriceField;
	
	public GUIEmptyFieldValidator(JTextField productPriceField, JTextField productQuantityField, JTextField productNameField){
		this.productNameField = productNameField;
		this.productQuantityField = productQuantityField;
		this.productPriceField = productPriceField;
	}
	
	public boolean isAddToInventoryFieldsEmpty(){
		if(productNameField.getText().isEmpty() || productQuantityField.getText().isEmpty() || 
		productPriceField.getText().isEmpty())
			return true;
		else return false;
	}
	
	public boolean isUpdateInventoryFieldsEmpty(){
		if(productNameField.getText().isEmpty() || productQuantityField.getText().isEmpty())
			return true;
		else return false;
	}
	
	public boolean isRemoveInventoryFieldsEmpty(){
		if(productNameField.getText().isEmpty())
			return true;
		else return false;
	}
}
