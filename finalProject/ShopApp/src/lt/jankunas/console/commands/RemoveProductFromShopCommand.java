package lt.jankunas.console.commands;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;

public class RemoveProductFromShopCommand implements ShopCommand {

    private Shop shop;
    private String[] entries;
    private User currentUser;

    public RemoveProductFromShopCommand(String[] entries, Shop shop, User currentUser) {
        this.entries = entries;
        this.shop = shop;
        this.currentUser = currentUser;
    }

    public void execute() {
        if(entries.length !=2){
            System.out.println("Invalid number of arguments");
        } else {
            String name = entries[1];
            if (InputValidator.isNameValid(name)) {
                String productName = name;
                shop.remove(productName);
            }
        }
    }
    
    public User getUser(){
        return currentUser;
    }
}
