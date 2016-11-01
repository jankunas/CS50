package lt.jankunas.shop;

public interface LoggedInState {

    public User getUser(User currentUser);
    
    public void initialize();
}
