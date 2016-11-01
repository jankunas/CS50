package lt.jankunas.shop;


public interface LogIn {

	public boolean isLoggedIn(String username, String password);
	
	public User getUser();
}
