package lt.jankunas.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lt.jankunas.console.commands.AddProductToCartCommand;
import lt.jankunas.console.commands.AddProductToShopCommand;
import lt.jankunas.console.commands.BuyCommand;
import lt.jankunas.console.commands.LogOutCommand;
import lt.jankunas.console.commands.RemoveProductFromCartCommand;
import lt.jankunas.console.commands.RemoveProductFromShopCommand;
import lt.jankunas.console.commands.ShowProductsInCartCommand;
import lt.jankunas.console.commands.ShowProductsInShopCommand;
import lt.jankunas.console.commands.UpdateShopProductCommand;
import lt.jankunas.shop.*;
import lt.jankunas.shop.helpers.CommandLineParser;

public class InMemoryLoggedInState implements LoggedInState {

    private Map<String, ShopCommand> commands = new HashMap<String, ShopCommand>();
    private Shop shop;
    private ShoppingCart shoppingCart;
    private ShopManager shopManager;
    private User currentUser;
    private String userInput;
    private Scanner in;
    private CommandLineParser parser;
    private String[] entries;
    private String userCommand;

    public InMemoryLoggedInState(Shop shop, ShoppingCart shoppingCart, ShopManager shopManager, Scanner in,
            CommandLineParser parser) {
        this.shop = shop;
        this.shoppingCart = shoppingCart;
        this.shopManager = shopManager;
        this.in = in;
        this.parser = parser;
    }

    public void initialize() {
        commands.put("ADD", new AddProductToShopCommand(entries, shop, currentUser));
        commands.put("SHOW", new ShowProductsInShopCommand(entries, shop, currentUser));
        commands.put("REMOVE", new RemoveProductFromShopCommand(entries, shop, currentUser));
        commands.put("UPDATE", new UpdateShopProductCommand(shop, entries, currentUser));
        commands.put("ADD_CART", new AddProductToCartCommand(shop, shoppingCart, entries, currentUser));
        commands.put("REMOVE_CART", new RemoveProductFromCartCommand(shoppingCart, entries, currentUser));
        commands.put("SHOW_CART", new ShowProductsInCartCommand(entries, shoppingCart, currentUser));
        commands.put("LOGOUT", new LogOutCommand(entries, shoppingCart, currentUser));
        commands.put("BUY", new BuyCommand(entries, shoppingCart, shopManager, shop, currentUser));
    }

    public User getUser(User currentUser) {
        this.currentUser = currentUser;
        userInput = in.nextLine();
        entries = parser.parse(userInput);
        userCommand = parser.getCommand(entries);
        initialize();
        ShopCommand command = commands.get(userCommand);
        if (command == null) {
            System.out.format("Command %s not found", userCommand);
        } else {
            command.execute();
            this.currentUser = command.getUser();
        }
        return currentUser;
    }
}
