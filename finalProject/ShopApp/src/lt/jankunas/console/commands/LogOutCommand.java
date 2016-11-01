package lt.jankunas.console.commands;

import lt.jankunas.shop.*;

public class LogOutCommand implements ShopCommand {

    private User currentUser;
    private String[] entries;
    private ShoppingCart shoppingCart;

    public LogOutCommand(String[] entries, ShoppingCart shoppingCart, User currentUser) {
        this.entries = entries;
        this.shoppingCart = shoppingCart;
        this.currentUser = currentUser;
    }


    @Override
    public void execute() throws IllegalArgumentException{
        if (entries.length != 2) {
            System.out.println("Invalid number of arguments");
        } else {
            String username = entries[1];
            if (username.equals(currentUser.getUsername())) {
                shoppingCart.delete();
                currentUser = null;
                System.out.println("You have successfully logged out");
            } else {
                System.out.println("Incorrect username");
            }
        }
    }
    
    public User getUser(){
        return currentUser;
    }
}
