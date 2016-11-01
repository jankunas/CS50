package lt.jankunas.shop.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lt.jankunas.shop.ConfigurationManager;

public class ConnectionManager {

    private Connection conn = null;
    private ConfigurationManager config;
    
    public ConnectionManager(ConfigurationManager config){
        this.config = config;
    }

    public void execute() throws SQLException {
        String url = config.getParameter("url");
        String username = config.getParameter("username");
        String password = config.getParameter("password");
        this.conn = DriverManager.getConnection(url, username, password);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
