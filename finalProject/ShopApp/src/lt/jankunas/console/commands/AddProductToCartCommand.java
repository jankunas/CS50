package lt.jankunas.console.commands;

import lt.jankunas.shop.*;
import lt.jankunas.shop.utils.*;

public class AddProductToCartCommand implements ShopCommand{

    private Shop shop;
    private ShoppingCart shoppingCart;
    private User currentUser;
    private String[] entries;
    
	public AddProductToCartCommand(Shop shop, ShoppingCart shoppingCart, String[] entries, User currentUser) {
            this.entries = entries;
            this.shop = shop;
            this.shoppingCart = shoppingCart;
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
                if(isProductFound(productName)){
                    if(shoppingCart.getShoppingCartList().isEmpty())
                        addProductToEmptyCart(productName, productQuantity);
                    else 
                        addProductToExistingCart(productName, productQuantity);
                } else
                    System.out.format("%s does not exist in inventory", productName);
            }
	    }
	}
	
	private void addProductToEmptyCart(String productName, int productQuantity){
	    String productInShop = shop.getProductByName(productName).getName();
        float productInShopPrice = shop.getProductByName(productName).getPrice();
        int productInShopQuantity = shop.getProductByName(productName).getQuantity();
        if (productName.equalsIgnoreCase(productInShop) && productInShopQuantity >= productQuantity)
            shoppingCart.add(new Product(productName, productQuantity, productInShopPrice));
        else
            System.out.format("There are(is) currenty %d %s in inventory and you are trying to add %d", productInShopQuantity, productInShop, productQuantity);
	}
	
	private void addProductToExistingCart(String productName, int productQuantity){
	    String productInShop = shop.getProductByName(productName).getName();
        float productInShopPrice = shop.getProductByName(productName).getPrice();
        int productInShopQuantity = shop.getProductByName(productName).getQuantity();
        int cartQuantity = shoppingCart.getCartProductByName(productName).getQuantity();
        if (productName.equalsIgnoreCase(productInShop) && productInShopQuantity >= cartQuantity + productQuantity)
            shoppingCart.add(new Product(productName, productQuantity, productInShopPrice));
        else
            System.out.format("There are(is) currenty %d %s in inventory and you are trying to add %d", productInShopQuantity, productInShop, cartQuantity + productQuantity);
	}
	
	private boolean isProductFound(String productName){
	    Product shopProduct = shop.getProductByName(productName);
	    if(shopProduct != null){
	        return true;
	    }
	    return false;
	}
	
	public User getUser(){
        return currentUser;
    }
}
