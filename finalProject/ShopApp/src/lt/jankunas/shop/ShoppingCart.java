package lt.jankunas.shop;

import java.util.List;

public interface ShoppingCart {

	public void add(Product product);
	public void remove(String product, int quantity);
	public List<Product> getShoppingCartList();
	public float getSum();
	public void delete();
	public Product getCartProductByName(String product);
	public void show(User user);
}
