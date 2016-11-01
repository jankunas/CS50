package lt.jankunas.console.inmemory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.jankunas.shop.*;

public class InMemoryShoppingCart implements ShoppingCart {
	
	public List<Product> shoppingCart = new ArrayList<Product>();
	
	public void add(Product product) {
		Product tempProduct = null;
		String productName = product.getName();
        int productQuantity = product.getQuantity();
        Float productPrice = product.getPrice();
		for (Product productInCart : shoppingCart) {
			if (productName.equals(productInCart.getName())){
				tempProduct = productInCart;
				break;
			}
		}
		if (tempProduct == null)
			shoppingCart.add(product);
		else 
			tempProduct.setQuantity(tempProduct.getQuantity() + productQuantity);
		System.out.format("%d %s for %.2f each has(have) been successfully added to your shopping cart", productQuantity, productName, productPrice);
	}

	public void remove(String productName, int quantity) {
		Product tempProduct = null;
		if(!shoppingCart.isEmpty()){
			for (Product productInCart : shoppingCart) {
				if (productName.equalsIgnoreCase(productInCart.getName()) && productInCart.getQuantity() >= quantity){
					productInCart.setQuantity(productInCart.getQuantity() - quantity); 
					tempProduct = productInCart;
				}
			}
			if (tempProduct != null && tempProduct.getQuantity() == 0)
					shoppingCart.remove(tempProduct);
			System.out.format("%s has been successfully removed from the inventory", productName);
		} else
			System.out.println("Your shopping cart is empty, there is nothing to remove");
	}

	public List<Product> getShoppingCartList() {
		if(!shoppingCart.isEmpty())
			return shoppingCart;
		else
			return Collections.<Product>emptyList();
	}

	public float getSum() {
		float total = 0;
		for (Product product : shoppingCart) {
		    total = computeSum(total, computeProduct(product.getPrice(), product.getQuantity()));
		}
		return total;
	}

	@Override
	public void delete() {
		shoppingCart.clear();
	}

	@Override
	public Product getCartProductByName(String product) {
		if(!shoppingCart.isEmpty()){
			for(Product productInCart : shoppingCart){
				if(product.equalsIgnoreCase(productInCart.getName()))
					return productInCart;
			}
		}
		return null;
	}

	public void show(User user) {
		System.out.format("Current items in your cart%n-----------------------------------------------%n");
		for(Product productInCart : shoppingCart){
		    String productName = productInCart.getName();
		    int productQuantity = productInCart.getQuantity();
		    float productPrice = productInCart.getPrice();
		    System.out.format("Name: %-8s|Quantity: %-4d|Price: %-4.2f%n-----------------------------------------------%n", productName, productQuantity, productPrice);
		}
		System.out.format("Total payable amount: %.2f%n-----------------------------------------------%n", getSum());
		System.out.format("Cash left: %.2f", user.getCash());
	}
	
	private float computeProduct(Float price, int quantity){
        String productPrice = Float.toString(price);
        String productQuantity = Integer.toString(quantity);
        BigDecimal toComputePrice = new BigDecimal(productPrice);
        BigDecimal toComputeQuantity = new BigDecimal(productQuantity);
        BigDecimal product = toComputePrice.multiply(toComputeQuantity);
        
        return product.floatValue();
    }
    
    private float computeSum(Float total, float addition){
        String totalAmount = Float.toString(total);
        String additionAmount = Float.toString(addition);
        BigDecimal toComputeTotal = new BigDecimal(totalAmount);
        BigDecimal toComputeAddition = new BigDecimal(additionAmount);
        BigDecimal sum = toComputeTotal.add(toComputeAddition);
        
        return sum.floatValue();
    }
}
