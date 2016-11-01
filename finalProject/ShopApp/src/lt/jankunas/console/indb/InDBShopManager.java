package lt.jankunas.console.indb;
import java.sql.Connection;
import java.sql.PreparedStatement;

import lt.jankunas.shop.*;

public class InDBShopManager implements ShopManager {

	private Connection conn;
	private InDBStatementHandling statementHandling;
	private PreparedStatement statement;
	
	public InDBShopManager(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void buy(ShoppingCart shoppingCart, Shop shop, User currentUser) {
		try{
		    statementHandling = new InDBStatementHandling(conn);
		    statement = statementHandling.prepareUpdateUserCashCommand(shoppingCart, currentUser);
		    statement.executeUpdate();
		    
		    statement = statementHandling.prepareBuyCommand();
            statement.executeUpdate();
            
            statement = statementHandling.prepareDeleteShoppingCartCommand();
            statement.executeUpdate();
            System.out.println("Items were successfully bought");
		}catch(Exception e){
			e.printStackTrace();
		} finally{
		    try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
}
