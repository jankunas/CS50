package lt.jankunas.console.commands;

import lt.jankunas.shop.*;

public class BuyCommand implements ShopCommand {

    private ShoppingCart shoppingCart;
    private ShopManager shopManager;
    private Shop shop;
    private User currentUser;
    private String[] entries;

    public BuyCommand(String[] entries, ShoppingCart shoppingCart, ShopManager shopManager, Shop shop,
            User currentUser) {
        this.shoppingCart = shoppingCart;
        this.shopManager = shopManager;
        this.shop = shop;
        this.currentUser = currentUser;
        this.entries = entries;
    }

    @Override
    public void execute() throws IllegalArgumentException {
        if (entries.length != 1)
            System.out.println("Invalid number of arguments");
        else {
            if (currentUser.getCash() >= shoppingCart.getSum())
                shopManager.buy(shoppingCart, shop, currentUser);
            else
                System.out.println("Not enought cash!");
        }
    }
    
    public User getUser(){
        return currentUser;
    }
}
