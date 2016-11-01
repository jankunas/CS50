package lt.jankunas.console.indb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lt.jankunas.shop.*;


public class InDBStatementHandling {

    private PreparedStatement statement;

    private Connection conn;
    
    public InDBStatementHandling(Connection conn){
        this.conn = conn;
    }
    
    public PreparedStatement prepareAddToInventoryCommand(Product product) throws Exception{
        this.statement = conn.prepareStatement("INSERT INTO inventory (name, quantity, price) values (?,?,?) ON DUPLICATE KEY UPDATE quantity = quantity + ?, price=?");
        this.statement.setString(1, product.getName());
        this.statement.setInt(2, product.getQuantity());
        this.statement.setFloat(3, product.getPrice());
        this.statement.setInt(4, product.getQuantity());
        this.statement.setFloat(5, product.getPrice());
        return statement;
    }
    
    public PreparedStatement prepareAddToCartCommand(Product product) throws SQLException{
        
        this.statement = conn.prepareStatement("INSERT INTO shopping_cart (name, quantity, price) values (?,?,?) ON DUPLICATE KEY UPDATE quantity = quantity + ?");
        this.statement.setString(1, product.getName());
        this.statement.setInt(2, product.getQuantity());
        this.statement.setFloat(3, product.getPrice());
        this.statement.setInt(4, product.getQuantity());
        return statement;
    }
    
    public PreparedStatement prepareBuyCommand() throws SQLException{
        this.statement = conn.prepareStatement("UPDATE inventory, shopping_cart SET inventory.quantity=inventory.quantity-shopping_cart.quantity, shopping_cart.quantity='0' WHERE inventory.name=shopping_cart.name");
        return statement;
    }
    
    public PreparedStatement prepareDeleteShoppingCartCommand() throws SQLException{
        this.statement = conn.prepareStatement("DELETE inventory , shopping_cart FROM inventory INNER JOIN shopping_cart WHERE inventory.quantity='0' and shopping_cart.quantity='0'");
        return statement;
    }
    
    public PreparedStatement prepareUpdateUserCashCommand(ShoppingCart shoppingCart, User currentUser) throws SQLException {
        this.statement = conn.prepareStatement("UPDATE users SET cash = cash - ? WHERE username = ?");
        statement.setFloat(1, shoppingCart.getSum());
        statement.setString(2, currentUser.getUsername());
        return statement;
    }
    
    public PreparedStatement prepareUpdateRemoveFromCartCommand(String product, int quantity) throws SQLException{
        this.statement = conn.prepareStatement("UPDATE shopping_cart SET quantity = quantity - ? WHERE name = ?");
        statement.setInt(1, quantity);
        statement.setString(2, product);
        return statement;
    }
    
    public PreparedStatement prepareDeleteRemoveFromCartCommand() throws SQLException{
        this.statement = conn.prepareStatement("DELETE FROM shopping_cart WHERE quantity <= 0");
        return statement;
    }
    
    public PreparedStatement prepareCashShowCommand(User currentUser) throws SQLException{
        this.statement = conn.prepareStatement("SELECT cash FROM users WHERE username = ?");
        statement.setString(1, currentUser.getUsername());
        return statement;
    }
    
    public PreparedStatement prepareCartShowCommand() throws SQLException{
        this.statement = conn.prepareStatement("SELECT name, quantity, price FROM shopping_cart");
        return statement;
    }
    
    public PreparedStatement prepareUpdateInventoryCommand(String productName, int quantity) throws SQLException{
        this.statement = conn.prepareStatement("UPDATE inventory SET quantity = ? WHERE name = ?");
        statement.setInt(1, quantity);
        statement.setString(2, productName);
        return statement;
    }
    
    public PreparedStatement prepareLogInCommand(String username, String password) throws SQLException{
        this.statement = conn.prepareStatement("SELECT username, password, cash FROM users WHERE username = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        return statement;
    }
    
}
