package bot.janb.generaldiscordbot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
    
    private static final String COMMAND_PREFIX = "!apx";
    private Message message;
    private MessageChannel channel;
    private MessageReceivedEvent event;
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        //Stops messages from other bot's to trigger commands.
        if(event.getAuthor().isBot()) return;
        
        //Isolates comand prefix from the rest of the raw message.
        String rawMessage = event.getMessage().getContentRaw();
        String commandPrefix = getCommandPrefix(rawMessage);
        //Checks if the command prefix has been included.
        if(!commandPrefix.equals(COMMAND_PREFIX)) return;
        
        //Isolates command from the rest of the raw message.
        rawMessage = rawMessage.substring(rawMessage.indexOf(" ") + 1);
        String command = getCommand(rawMessage);
        
        //Calls the command menu.
        this.event = event;
        channel = event.getChannel();
        
        commandMenu(command, rawMessage);
        
        channel.sendMessage(message).queue();
    }
    
    @Override
    public void onGuildJoin(GuildJoinEvent e){
        channel = e.getGuild().getDefaultChannel();
        sendDocumentation();
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
                    for (int i = 0; i < 14; i++) {
                        channel.sendMessage(message).queue();
                        
                    }
                }catch(StringIndexOutOfBoundsException e){
                    message = new MessageBuilder()
                            .append("Please enter arguments after the spam command.")
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
                
                int jokeChoice = (int)(Math.random() * 7);
                message = new MessageBuilder()
                        .append(jokes[jokeChoice])
                        .build();
                break;
            case "help":
                sendDocumentation();
            default:
                message = new MessageBuilder()
                        .append("\"" + command + "\" is not a valid command.")
                        .build();
                break;
        }
    }
    
    public void sendDocumentation(){
        message = new MessageBuilder()
                .append("**Gnar Documentation**\n" +
                "The prefix of the bot on this server is !apx.\n\n" +
                "Settings — 0\n" +
                "`coming` \n\n" +
                "**Music — 0**\n" +
                "`coming` \n\n" +
                "**Fun — 1**\n" +
                "`joke` \n\n" +
                "**Media — 0**\n" +
                "`coming` \n\n" +
                "**General — 2**\n" +
                "`mimic` `spam` ")
                .build();
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
