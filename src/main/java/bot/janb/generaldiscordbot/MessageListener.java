package bot.janb.generaldiscordbot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{
    
    private Message message;
    private MessageReceivedEvent event;
    
    public void onMessageReceived(MessageReceivedEvent event){
        //Stops messages from other bot's to trigger commands.
        if(event.getAuthor().isBot()) return;
        
        //Isolates comand prefix from the rest of the raw message.
        String rawMessage = event.getMessage().getContentRaw();
        String commandPrefix = getCommandPrefix(rawMessage);
        //Checks if the command prefix has been included.
        if(!commandPrefix.equals("!apx")) return;
        
        //Isolates command from the rest of the raw message.
        rawMessage = rawMessage.substring(rawMessage.indexOf(" ") + 1);
        String command = getCommand(rawMessage);
        
        //Calls the command menu.
        this.event = event;
        commandMenu(command, rawMessage);
        
        MessageChannel channel = event.getChannel();
        
        channel.sendMessage(message).queue();
    }
    
    public void commandMenu(String command, String rawMessage){
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
                String [] jokes = {
                    "Knock! Knock! Who’s there? Robin. Robin who? Robin you—hand over the cash!",
                    event.getAuthor().getAsMention() + "! Get it? " + event.getAuthor().getAsMention() + " is the joke!",
                    "When you look really closely, all mirrors look like eyeballs.",
                    "I know a lot of jokes about unemployed people but none of them work.",
                    "As I suspected, someone has been adding soil to my garden. The plot thickens.",
                    "A horse walks into a bar, and the bartender asks, \"Why the long face?\"",
                    "A Man walks into a bar.\"Ouch!\"",
                };
                
                int jokeChoice = (int)(Math.random() * 8);
                message = new MessageBuilder()
                        .append(jokes[jokeChoice])
                        .build();
                break;
            default:
                message = new MessageBuilder()
                        .append("\"" + command + "\" is not a valid command.")
                        .build();
                break;
        }
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
