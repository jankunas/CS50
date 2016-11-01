package lt.jankunas.console.inmemory;

import java.math.BigDecimal;
import java.util.List;

import lt.jankunas.shop.*;


public class InMemoryShopManager implements ShopManager {
	
	public void buy(ShoppingCart shoppingCart, Shop shop, User currentUser) {
		List<Product> shoppingCartList = shoppingCart.getShoppingCartList();
		if(!shoppingCartList.isEmpty()){
			List<Product> inventoryList = shop.getListOfProducts();
			updateUserBalance(currentUser, shoppingCart);
			buyProducts(shoppingCartList, inventoryList);
			shoppingCart.delete();
			System.out.println("Items were successfully bought!");
		} else
			System.out.println("There are no items in the shopping cart to buy");
	}
	
	private void buyProducts(List<Product> shoppingCartList, List<Product> inventoryList){
		Product tempProductInInventory = null;
		for(Product productInCart : shoppingCartList){
			for(Product productInInventory : inventoryList){
				if(productInInventory.getName().equalsIgnoreCase(productInCart.getName())){
					productInInventory.setQuantity(productInInventory.getQuantity() - productInCart.getQuantity());
					tempProductInInventory = productInInventory;
				}
			}
			if(tempProductInInventory != null && tempProductInInventory.getQuantity() == 0)
				inventoryList.remove(tempProductInInventory);
		}
	}
	
	private void updateUserBalance(User currentUser, ShoppingCart shoppingCart){
		String userCash = Float.toString(currentUser.getCash());
		String amountToPay = Float.toString(shoppingCart.getSum());
		BigDecimal userAmount = new BigDecimal(userCash);
		BigDecimal cartAmount = new BigDecimal(amountToPay);
		BigDecimal remainder = userAmount.subtract(cartAmount);
		currentUser.setCash(remainder.floatValue());
	}
	
	
}
