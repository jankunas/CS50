package lt.jankunas.console.commands;

import lt.jankunas.shop.*;

public class ShowProductsInShopCommand implements ShopCommand {

    private Shop shop;
    private String[] entries;
    private User currentUser;
    
	public ShowProductsInShopCommand(String[] entries, Shop shop, User currentUser) {
		this.shop = shop;
		this.entries = entries;
		this.currentUser = currentUser;
	}

    public void execute() {
        if(entries.length != 1)
            System.out.println("Invalid number of arguments");
        else
            shop.show();
    }
    
    public User getUser(){
        return currentUser;
    }
}
