package lt.jankunas.ui.actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIInventoryListSelectionAction implements ListSelectionListener {

	private JList shoppingCartList;
	public GUIInventoryListSelectionAction(JList shoppingCartList){
		this.shoppingCartList = shoppingCartList;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!shoppingCartList.isSelectionEmpty())
			shoppingCartList.clearSelection();
		
	}
	
}
