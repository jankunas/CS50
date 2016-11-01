package lt.jankunas.console.commands;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;

public class RemoveProductFromCartCommand implements ShopCommand {

    private String[] entries;
    private ShoppingCart shoppingCart;
    private User currentUser;

    public RemoveProductFromCartCommand(ShoppingCart shoppingCart, String[] entries, User currentUser) {
        this.entries = entries;
        this.shoppingCart = shoppingCart;
        this.currentUser = currentUser;
    }

    public void execute() throws IllegalArgumentException {
        if(entries.length != 3){
            System.out.println("Invalid number of arguments");
        } else {
            String name = entries[1];
            String quantity = entries[2];
            if (InputValidator.isNameQuantityValid(name, quantity)) {
                String productName = name;
                int productQuantity = Integer.parseInt(quantity);
                shoppingCart.remove(productName, productQuantity);
            }
        }
    }
    
    public User getUser(){
        return currentUser;
    }
}
