package lt.jankunas.console.commands;

import java.sql.SQLException;

import lt.jankunas.console.indb.InDBLogin;
import lt.jankunas.console.indb.InDBShop;
import lt.jankunas.console.indb.InDBShopManager;
import lt.jankunas.console.indb.InDBShoppingCart;
import lt.jankunas.shop.ConfigurationManager;
import lt.jankunas.shop.InitializeCommand;
import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.shop.helpers.*;

public class InDBSelectionCommand implements InitializeCommand {
        
    private ConnectionManager connection;
    private Shop shop;
    private ShoppingCart shoppingCart;
    private LogIn login;
    private ShopManager shopManager;
    
    public InDBSelectionCommand(ConfigurationManager config){
        this.connection = new ConnectionManager(config);
    }

    public void execute() throws SQLException {
        connection.execute();
        this.shop = new InDBShop(connection.getConn());
        this.shoppingCart = new InDBShoppingCart(connection.getConn());
        this.login = new InDBLogin(connection.getConn());
        this.shopManager = new InDBShopManager(connection.getConn());
        System.out.println("Database was selected");
    }
    
    public Shop getShop() {
        return shop;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public LogIn getLogin() {
        return login;
    }

    public ShopManager getShopManager() {
        return shopManager;
    }

}
