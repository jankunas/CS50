package lt.jankunas.shop.helpers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lt.jankunas.console.commands.*;
import lt.jankunas.shop.*;

public class ShopInitializer {

    ConfigurationManager config = new InFileConfigurationManager();
    CommandFactory factory = new CommandFactory(config);
    private Map<String, String> commands = new HashMap<String, String>();
    private Shop shop;
    private ShoppingCart shoppingCart;
    private LogIn login;
    private ShopManager shopManager;
    private Scanner in;

    public ShopInitializer(Scanner in) {
        this.in = in;
        initialize();
    }

    private void initialize() {
        commands.put("1", "In Memory");
        commands.put("2", "In Database");
    }

    public void execute() {
        while (true) {
            System.out.println("Please enter <1> to use memory or <2> to use database");
            String entry = in.nextLine();
            String selection = commands.get(entry);
            if (selection == null) {
                System.out.println(String.format("Command %s not found", entry));
            } else {
                try {
                    InitializeCommand command = factory.getCommand(selection);
                    command.execute();
                    this.shop = command.getShop();
                    this.shoppingCart = command.getShoppingCart();
                    this.login = command.getLogin();
                    this.shopManager = command.getShopManager();
                    break;
                } catch (SQLException e) {
                    System.out.println("There was a problem connecting to database");
                } 
            }
        }
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
