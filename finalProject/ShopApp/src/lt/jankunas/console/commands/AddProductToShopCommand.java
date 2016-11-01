package lt.jankunas.console.commands;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;

public class AddProductToShopCommand implements ShopCommand {

    private Shop shop;
    private String[] entries;
    private User currentUser;

    public AddProductToShopCommand(String[] entries, Shop shop, User currentUser) {
        this.entries = entries;
        this.shop = shop;
        this.currentUser = currentUser;
    }

    public void execute() {
        if (entries.length != 4) {
            System.out.println("Invalid number of arguments");
        } else {
            String name = entries[1];
            String quantity = entries[2];
            String price = entries[3];
            if (InputValidator.isNameQuantityPriceValid(name, quantity, price)) {
                String productName = name;
                int productQuantity = Integer.parseInt(quantity);
                float productPrice = Float.parseFloat(price);
                shop.add(new Product(productName, productQuantity, productPrice));
            } else
                System.out.println("Second argument should be string, third - integer and third - integer of float");
        }
    }
    
    public User getUser(){
        return currentUser;
    }
}
