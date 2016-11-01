package lt.jankunas.ui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lt.jankunas.shop.utils.FrameCreator;
import lt.jankunas.ui.actions.GUIShopInitializerComboBoxAction;

public class ShopInitializerWindow {

    JPanel initializerPanel = new JPanel();
    JComboBox initializerComboBox = new JComboBox(new String[] { "IN_MEMORY", "IN_DATABASE" });

    private JFrame frame;

    public ShopInitializerWindow() {
        this.frame = new JFrame("Make a selection");

    }

    public void createInitializerUI() {
        createPanel();
        FrameCreator.createFrame(frame);
        initializerComboBox
                .addActionListener(new GUIShopInitializerComboBoxAction(initializerComboBox, frame));
    }

    private void createPanel() {
        frame.getContentPane().add(initializerPanel);
        initializerPanel.setLayout(null);
        initializerComboBox.setBounds(400, 300, 200, 30);
        initializerPanel.add(initializerComboBox);
    }
}
