package lt.jankunas.ui.commands;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import lt.jankunas.console.indb.InDBLogin;
import lt.jankunas.console.indb.InDBShop;
import lt.jankunas.console.indb.InDBShopManager;
import lt.jankunas.console.indb.InDBShoppingCart;
import lt.jankunas.shop.ConfigurationManager;
import lt.jankunas.shop.GUICommand;
import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.shop.helpers.ConnectionManager;
import lt.jankunas.ui.LogInWindow;

public class GUIInDBCommand implements GUICommand{
    
    private ConfigurationManager config;
    private LogInWindow logInWindow;
    private JFrame frame;

    public GUIInDBCommand(ConfigurationManager config, LogInWindow logInWindow, JFrame frame){
        this.config = config;
        this.logInWindow = logInWindow;
        this.frame = frame;
    }

    public void execute() {
        SwingWorker<ConnectionManager, Void> worker = new SwingWorker<ConnectionManager, Void>() {
            protected ConnectionManager doInBackground() throws SQLException   {
                ConnectionManager connection = new ConnectionManager(config);
                connection.execute();
                return connection;
            }
            protected void done(){
                try {
                    ConnectionManager connection = get();
                    Shop shop = new InDBShop(connection.getConn());
                    ShoppingCart shoppingCart = new InDBShoppingCart(connection.getConn());
                    LogIn login = new InDBLogin(connection.getConn());
                    ShopManager shopManager = new InDBShopManager(connection.getConn()); 
                    logInWindow.createLogInUI(login, shop, shoppingCart, shopManager);
                    frame.dispose();
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "There was en error connecting to database");
                }
            }
        };
        worker.execute();
    }
}
