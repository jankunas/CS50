package lt.jankunas.console.commands;

import lt.jankunas.console.inmemory.InMemoryLogIn;
import lt.jankunas.console.inmemory.InMemoryShop;
import lt.jankunas.console.inmemory.InMemoryShopManager;
import lt.jankunas.console.inmemory.InMemoryShoppingCart;
import lt.jankunas.shop.ConfigurationManager;
import lt.jankunas.shop.InitializeCommand;
import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;

public class InMemorySelectionCommand implements InitializeCommand {

    private Shop shop;
    private ShoppingCart shoppingCart;
    private LogIn login;
    private ShopManager shopManager;
    private ConfigurationManager config;
    
    public InMemorySelectionCommand(ConfigurationManager config){
        this.config = config;
    }
    
    public void execute() {
        this.shop = new InMemoryShop();
        this.shoppingCart = new InMemoryShoppingCart();
        this.login = new InMemoryLogIn(config);
        this.shopManager = new InMemoryShopManager();
        System.out.println("Memory was selected");
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
