package bot.janb.generaldiscordbot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{
    public void onMessageReceived(MessageReceivedEvent event){
        //Stops messages from other bot's to trigger commands.
        if(event.getAuthor().isBot()) return;
        
        //Isolates comand prefix from the rest of the raw message.
        String rawMessage = event.getMessage().getContentRaw();
        String commandPrefix = getCommandPrefix(rawMessage);
        rawMessage = rawMessage.substring(rawMessage.indexOf(" ") + 1);
        String command = getCommand(rawMessage);
        
        
        //Checks if the command prefix has been included.
        if(!commandPrefix.equals("!apx")) return;
        
        MessageChannel channel = event.getChannel();
        Message message;
        switch(command){
            case "mimic":
                try{
                    rawMessage = rawMessage.substring(rawMessage.indexOf(" "));
                    message = new MessageBuilder()
                            .append(rawMessage)
                            .build();
                }catch(StringIndexOutOfBoundsException e){
                    message = new MessageBuilder()
                            .append("Please enter arguments after the mimic command.")
                            .build();
                }
                break;
            case "spam":
                try{
                    rawMessage = rawMessage.substring(rawMessage.indexOf(" "));
                    message = new MessageBuilder()
                            .append(rawMessage)
                            .build();
                }catch(StringIndexOutOfBoundsException e){
                    message = new MessageBuilder()
                            .append("Please enter arguments after the mimic command.")
                            .build();
                }
                break;
            case "joke":
                try{
                    rawMessage = rawMessage.substring(rawMessage.indexOf(" "));
                    message = new MessageBuilder()
                            .append(rawMessage)
                            .build();
                }catch(StringIndexOutOfBoundsException e){
                    message = new MessageBuilder()
                            .append("Please enter arguments after the mimic command.")
                            .build();
                }
                break;
            default:
                try{
                    rawMessage = rawMessage.substring(rawMessage.indexOf(" "));
                    message = new MessageBuilder()
                            .append(rawMessage)
                            .build();
                }catch(StringIndexOutOfBoundsException e){
                    message = new MessageBuilder()
                            .append("Please enter arguments after the mimic command.")
                            .build();
                }
                break;
        }
        
        channel.sendMessage(message).queue();
    }
    
    public String getCommandPrefix(String rawMessage){
        String commandPrefix;
        if(!rawMessage.contains(" ")) return "none";
        commandPrefix = rawMessage.substring(0, rawMessage.indexOf(" "));
        return commandPrefix;
    }
    
    public String getCommand(String rawMessage){
        String command;
        if(!rawMessage.contains(" ")){
            command = rawMessage;
        }
        else{
            command = rawMessage.substring(0, rawMessage.indexOf(" "));
        }
        return command;
    }
}
