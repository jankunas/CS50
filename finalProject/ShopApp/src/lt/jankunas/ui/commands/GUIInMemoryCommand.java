package lt.jankunas.ui.commands;

import javax.swing.JFrame;

import lt.jankunas.console.inmemory.InMemoryLogIn;
import lt.jankunas.console.inmemory.InMemoryShop;
import lt.jankunas.console.inmemory.InMemoryShopManager;
import lt.jankunas.console.inmemory.InMemoryShoppingCart;
import lt.jankunas.shop.ConfigurationManager;
import lt.jankunas.shop.GUICommand;
import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.ui.LogInWindow;

public class GUIInMemoryCommand implements GUICommand{

    private ConfigurationManager config;
    private JFrame frame;
    private LogInWindow logInWindow;
    
    public GUIInMemoryCommand(ConfigurationManager config, LogInWindow logInWindow, JFrame frame){
        this.config = config;
        this.logInWindow = logInWindow;
        this.frame = frame;
    }   
    
    public void execute() {
        Shop shop = new InMemoryShop();
        ShoppingCart shoppingCart = new InMemoryShoppingCart();
        LogIn login = new InMemoryLogIn(config);
        ShopManager shopManager = new InMemoryShopManager();
        logInWindow.createLogInUI(login, shop, shoppingCart, shopManager);
        frame.dispose();
    }

}
