package lt.jankunas.shop;

public interface ShopCommand {

    public void execute();
    
    public User getUser();
}
