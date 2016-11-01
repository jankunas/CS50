package lt.jankunas.console;

import java.util.Scanner;

import lt.jankunas.shop.LogIn;
import lt.jankunas.shop.LoggedInState;
import lt.jankunas.shop.LoggedOutState;
import lt.jankunas.shop.Shop;
import lt.jankunas.shop.ShopManager;
import lt.jankunas.shop.ShoppingCart;
import lt.jankunas.shop.User;
import lt.jankunas.shop.helpers.CommandLineParser;
import lt.jankunas.shop.helpers.ShopInitializer;

public class MainShopApplication {
    
    public static void main(String[] args) {
        System.out.println("Shop Application started...");
        User currentUser = null;
        Scanner in = new Scanner(System.in);
        CommandLineParser parser = new CommandLineParser();
        ShopInitializer init = new ShopInitializer(in);
        init.execute();
        Shop shop = init.getShop();
        ShoppingCart shoppingCart = init.getShoppingCart();
        LogIn login = init.getLogin();
        ShopManager shopManager = init.getShopManager();
        
        LoggedOutState loggedOutState = new InMemoryLoggedOutState(login, in, parser);
        LoggedInState loggedInState = new InMemoryLoggedInState(shop, shoppingCart, shopManager, in, parser);

        while (true) {
            if (currentUser == null)
                currentUser = loggedOutState.getUser();
            else{
                currentUser = loggedInState.getUser(currentUser);
            }    
        }
    }
}
