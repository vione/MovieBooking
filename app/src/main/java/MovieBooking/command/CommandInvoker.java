package MovieBooking.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MovieBooking.exceptions.NoSuchCommandFoundException;

public class CommandInvoker {

    private final Map<String, ICommand> commandMap = new HashMap<>();

    public void register(String CommandName, ICommand command){
        commandMap.put(CommandName, command);
    }

    private ICommand getCommand(String commandName){
        return commandMap.get(commandName);        
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandFoundException{
        ICommand command = this.getCommand(commandName);
        if(command == null) throw new NoSuchCommandFoundException();
        command.execute(tokens);
    }
    
}
