package lt.jankunas.console.inmemory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.jankunas.shop.*;

public class InMemoryShop implements Shop {

	public List<Product> listOfProducts = new ArrayList<Product>();

	public void add(Product product) {
		Product tempProduct = null;
		String productName = product.getName();
		int productQuantity = product.getQuantity();
		Float productPrice = product.getPrice();
		for(Product productInList : listOfProducts) {
			if(productName.equals(productInList.getName())){
				tempProduct = productInList;
				break;
			}
		}
		if(tempProduct == null)
			listOfProducts.add(product);
		else {
			tempProduct.setPrice(productPrice);
			tempProduct.setQuantity(productQuantity + tempProduct.getQuantity());
		}
		System.out.format("%d %s for %.2f each has(have) been successfully added to inventory", productQuantity, productName, productPrice);
	}

	public List<Product> getListOfProducts() {
		if(!listOfProducts.isEmpty())
			return listOfProducts;
		else
			return Collections.<Product>emptyList();
	}
	
	public void show(){
		if(!listOfProducts.isEmpty()){
			System.out.format("Current items in inventory%n-----------------------------------------------%n");
			for(Product product : listOfProducts) {
			    String productName = product.getName();
			    int productQuantity = product.getQuantity();
			    float productPrice = product.getPrice();
			    System.out.format("Name: %-8s|Quantity: %-4d|Price: %-4.2f%n-----------------------------------------------%n", productName, productQuantity, productPrice);
			}
		} else
			System.out.println("There are no items in inventory to show");
	}

	@Override
	public void remove(String productName) {
		if(!listOfProducts.isEmpty()){
			Product tempProduct = null;
			for(Product productInList : listOfProducts){
				if(productName.equalsIgnoreCase(productInList.getName())){
					tempProduct = productInList;
					break;
				}
			}
			if(tempProduct != null){
				listOfProducts.remove(tempProduct);
				System.out.format("%s has been successfully removed from the inventory", productName);
			}
		} else
			System.out.println("There are no items to remove");	
	}

	public void update(String productName, int quantity) {
		boolean isProductFound = false;
		if(!listOfProducts.isEmpty()){
			for(Product productInList : listOfProducts){
				if(productName.equalsIgnoreCase(productInList.getName())){
					productInList.setQuantity(quantity);
					isProductFound = true;
					System.out.format("%s quantity has been successfully updated to %d", productName, quantity);
					break;
				}
			}
			if(!isProductFound)
				System.out.format("%s does not exist in inventory", productName);
		} else
			System.out.println("Inventory is empty and there is nothing to update");
	}

	@Override
	public Product getProductByName(String product) {
		for(Product productInList : listOfProducts){
			if(product.equalsIgnoreCase(productInList.getName()))
				return productInList;
		}
		return null;
	}
}
