package lt.jankunas.ui.actions;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIShoppingCartListSelectionAction implements ListSelectionListener{
	
	private JList inventoryList;
	
	public GUIShoppingCartListSelectionAction(JList inventoryList){
		this.inventoryList = inventoryList;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!inventoryList.isSelectionEmpty()){
			inventoryList.clearSelection();
		}
	}
}
