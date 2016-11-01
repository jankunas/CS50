package lt.jankunas.shop;


public class User {

	private String username;
	
	private String password;
	
	private float cash;
	
	private int id;
	
	public User(String username, String password, float cash){
		this.username = username;
		this.password = password;
		this.cash = cash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
