package lt.jankunas.console.inmemory;

import java.util.ArrayList;
import java.util.List;

import lt.jankunas.shop.*;

public class InMemoryLogIn implements LogIn {

	List<User> userList = new ArrayList<User>();
	private User currentUser;
	
	public InMemoryLogIn(ConfigurationManager config) {
		String username = config.getParameter("shopUsername");
		String password = config.getParameter("shopPassword");
		float userCash = Float.valueOf(config.getParameter("cash"));
		User user = new User(username,password,userCash);
		userList.add(user);
	}
	
	public boolean isLoggedIn(String username, String password) {
		for(User user : userList){
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
			    currentUser = user;
				System.out.format("%s has been successfully logged in", username);
				return true;
			}
		}
		System.out.println("Username or password incorrect");
		return false;
	}

	public User getUser() {
		return currentUser;
	}
}
