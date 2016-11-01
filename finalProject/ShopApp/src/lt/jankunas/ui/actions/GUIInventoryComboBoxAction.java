package lt.jankunas.ui.actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIInventoryComboBoxAction implements ActionListener {
	
	private JLabel productPriceLabel;
	private JTextField productPriceField;
	private JLabel productQuantityLabel;
	private JTextField productQuantityField;
	private JComboBox searchComboBox;
	
	public GUIInventoryComboBoxAction(JLabel productPriceLabel, JTextField productPriceField, JLabel productQuantityLabel, JTextField productQuantityField, JComboBox searchComboBox){
		this.productPriceLabel = productPriceLabel;
		this.productPriceField = productPriceField;
		this.productQuantityLabel = productQuantityLabel;
		this.productQuantityField = productQuantityField;
		this.searchComboBox = searchComboBox;
	}

	public void actionPerformed(ActionEvent e){
		if(searchComboBox.getSelectedItem().equals("UPDATE")){
			productPriceLabel.setVisible(false);
			productPriceField.setVisible(false);
			productQuantityLabel.setVisible(true);
			productQuantityField.setVisible(true);
		} else if(searchComboBox.getSelectedItem().equals("REMOVE")){
			productPriceLabel.setVisible(false);
			productPriceField.setVisible(false);
			productQuantityLabel.setVisible(false);
			productQuantityField.setVisible(false);
		} else if(searchComboBox.getSelectedItem().equals("ADD")){
			productPriceLabel.setVisible(true);
			productPriceField.setVisible(true);
			productQuantityLabel.setVisible(true);
			productQuantityField.setVisible(true);
		}
	}
}
