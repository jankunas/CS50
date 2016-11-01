package lt.jankunas.console.commands;

import lt.jankunas.shop.*;

public class ShowProductsInCartCommand implements ShopCommand{
    
    private ShoppingCart shoppingCart;
    private User currentUser;
    private String[] entries;

	public ShowProductsInCartCommand(String[] entries, ShoppingCart shoppingCart, User currentUser){
		this.shoppingCart = shoppingCart;
		this.currentUser = currentUser;
		this.entries = entries;
	}

    @Override
    public void execute() throws IllegalArgumentException {
        if(entries.length != 1)
            System.out.println("Invalid number of arguments");
        else
            shoppingCart.show(currentUser);
    }
    
    public User getUser(){
        return currentUser;
    }
}
