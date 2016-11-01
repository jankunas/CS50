package lt.jankunas.console.commands;

import lt.jankunas.shop.ConfigurationManager;
import lt.jankunas.shop.InitializeCommand;

public class CommandFactory {
    
    private ConfigurationManager config;
    
    public CommandFactory(ConfigurationManager config){
        this.config = config;
    }
    
    public InitializeCommand getCommand(String selection){
        if(selection.equals("In Memory")){
            return new InMemorySelectionCommand(config);
        } else if(selection.equals("In Database"))
            return new InDBSelectionCommand(config);
        else
            return null;
    }
}
