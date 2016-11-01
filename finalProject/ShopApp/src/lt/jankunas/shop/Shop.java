package lt.jankunas.shop;

import java.util.List;

public interface Shop {
	
	public void add(Product product);
	public List<Product> getListOfProducts();
	public void remove(String productName);
	public void update(String productName, int quantity);
	public Product getProductByName(String product);
	public void show();
}
