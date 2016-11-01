package lt.jankunas.console.commands;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;

public class UpdateShopProductCommand implements ShopCommand{

    private Shop shop;
    private String[] entries;
    private User currentUser;
    
	public UpdateShopProductCommand(Shop shop, String[] entries, User currentUser){
	    this.entries = entries;
        this.shop = shop;
        this.currentUser = currentUser;
	}
	
	public void execute() {
	    if(entries.length != 3){
	        System.out.println("Invalid number of arguments");
	    } else {
	        String name = entries[1];
            String quantity = entries[2];
	        if(InputValidator.isNameQuantityValid(name, quantity)){
	            String productName = name;
	            int productQuantity = Integer.parseInt(quantity);
	            shop.update(productName, productQuantity);
	        } 
	    }
	}
	
	public User getUser(){
        return currentUser;
    }
}
