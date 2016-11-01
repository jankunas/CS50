package lt.jankunas.console.indb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.jankunas.shop.*;

public class InDBShop implements Shop {
	
	private Connection conn;
	private InDBStatementHandling statementHandling;
	private PreparedStatement statement;
	
	public InDBShop(Connection connection){
		this.conn = connection;
	}
	
	public void add(Product product) {
		try{
		    statementHandling = new InDBStatementHandling(conn);
		    statement = statementHandling.prepareAddToInventoryCommand(product);
			statement.executeUpdate();
			System.out.format("%d %s for %.2f each have(has) been added to your inventory", product.getQuantity(), product.getName(), product.getPrice());
		} catch(Exception e){
			e.printStackTrace();
		} finally {
		    try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

	public List<Product> getListOfProducts() {
		List<Product> listOfProducts = new ArrayList<Product>();
		try(PreparedStatement statement = conn.prepareStatement("SELECT name, quantity, price FROM inventory");){
			try(ResultSet result = statement.executeQuery();){
    			while(result.next()){
    				listOfProducts.add(new Product(result.getString("name"), result.getInt("quantity"), result.getFloat("price")));
    			}
			}
			return listOfProducts;
		} catch(Exception e){
			e.printStackTrace();
		}
		return Collections.<Product>emptyList();
	}

	@Override
	public void remove(String productName) {
		try(PreparedStatement statement = conn.prepareStatement("DELETE FROM inventory WHERE name = ?");){	
			statement.setString(1, productName);
			int count = statement.executeUpdate();
			if(count>0)
				System.out.format("%s has been removed from inventory", productName);
			else
				System.out.format("%s does not exist in inventory", productName);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(String productName, int quantity) {
		try{
		    statementHandling = new InDBStatementHandling(conn);
			statement = statementHandling.prepareUpdateInventoryCommand(productName, quantity);
			if(statement.executeUpdate()>0)
				System.out.format("Quantity for %s has been successfully updated to %d", productName, quantity);
			else
				System.out.format("%s does not exist in inventory", productName);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
		    try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}

	@Override
	public Product getProductByName(String product) {
		try(PreparedStatement statement = conn.prepareStatement("SELECT name, quantity, price FROM inventory WHERE name = ?");){
			statement.setString(1, product);
			ResultSet result = statement.executeQuery();
			if(result.next())
				return new Product(result.getString("name"), result.getInt("quantity"), result.getFloat("price"));
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void show() {
		try(PreparedStatement statement = conn.prepareStatement("SELECT name, quantity, price FROM inventory");){
			try(ResultSet result = statement.executeQuery();){
    			System.out.format("Current items in inventory:%n-----------------------------------------------%n");
    			while(result.next()){
    			    String productName = result.getString("name");
    			    int productQuantity = result.getInt("quantity");
    			    float productPrice = result.getFloat("price");
    				System.out.format("Name: %-8s|Quantity: %-4d|Price: %-4.2f%n-----------------------------------------------%n", productName, productQuantity, productPrice);
    			}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
