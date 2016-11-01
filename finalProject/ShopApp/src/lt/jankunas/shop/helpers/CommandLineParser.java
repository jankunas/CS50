package lt.jankunas.shop.helpers;

public class CommandLineParser {
    
    public String[] parse(String userInput){
        String[] entries = userInput.split(" ");
        return entries;
    }
    
    public String getCommand(String[] entries){
        return entries[0];
    }
}
