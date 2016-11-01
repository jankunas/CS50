package lt.jankunas.shop;

import java.sql.SQLException;

public interface InitializeCommand {
    
    public void execute() throws SQLException;
    
    public Shop getShop();

    public ShoppingCart getShoppingCart();

    public LogIn getLogin();

    public ShopManager getShopManager();

}
