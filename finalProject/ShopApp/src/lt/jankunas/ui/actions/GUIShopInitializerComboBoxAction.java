package lt.jankunas.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import lt.jankunas.console.indb.*;
import lt.jankunas.console.inmemory.*;
import lt.jankunas.shop.*;
import lt.jankunas.shop.helpers.ConnectionManager;
import lt.jankunas.shop.helpers.InFileConfigurationManager;
import lt.jankunas.shop.utils.*;
import lt.jankunas.ui.*;
import lt.jankunas.ui.commands.GUIInDBCommand;
import lt.jankunas.ui.commands.GUIInMemoryCommand;

public class GUIShopInitializerComboBoxAction implements ActionListener {

    ConfigurationManager config = new InFileConfigurationManager();
    private Map<String, GUICommand> commands = new HashMap<String, GUICommand>();
    LogInWindow logInWindow = new LogInWindow();
    private JComboBox initializerComboBox;
    private JFrame frame;

    public GUIShopInitializerComboBoxAction(JComboBox initializerComboBox, JFrame frame) {
        this.initializerComboBox = initializerComboBox;
        this.frame = frame;
        initialize();
    }

    private void initialize() {
        commands.put("IN_DATABASE", new GUIInDBCommand(config, logInWindow, frame));
        commands.put("IN_MEMORY", new GUIInMemoryCommand(config, logInWindow, frame));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUICommand command = commands.get(initializerComboBox.getSelectedItem());
        command.execute();
    }
}
