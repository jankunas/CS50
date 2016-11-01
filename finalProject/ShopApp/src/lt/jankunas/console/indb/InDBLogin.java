package lt.jankunas.console.indb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lt.jankunas.shop.*;

public class InDBLogin implements LogIn {

	private Connection conn;
	private User currentUser;
	private PreparedStatement statement;
	
	public InDBLogin(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean isLoggedIn(String username, String password) {
		try
		{
		    InDBStatementHandling statementHandling = new InDBStatementHandling(conn);
		    statement = statementHandling.prepareLogInCommand(username, password);
			try(ResultSet result = statement.executeQuery();){
    			if(result.next()){
    			    this.currentUser = new User(result.getString("username"), result.getString("password"), result.getFloat("cash"));
    				System.out.format("%s has been successfully logged in!", result.getString("username"));
    				return true;
    			} else
    				System.out.println("Incorrect username or password");
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
		    try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
		}
		return false;
	}

	public User getUser() {
		return currentUser;
	}

	
}
