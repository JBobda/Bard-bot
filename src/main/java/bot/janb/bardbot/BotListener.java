package bot.janb.bardbot;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
    
    private Message message;
    private MessageChannel channel;
    private MessageReceivedEvent event;
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        //Stops messages from other bot's to trigger commands.
        if(event.getAuthor().isBot()) return;
    }
    
    @Override
    public void onGuildJoin(GuildJoinEvent e){
        channel = e.getGuild().getDefaultChannel();
        sendDocumentation();
    }
    
    public void sendDocumentation(){
        //NEEDS IMPLEMENTATION
    }
    
    public String getCommandPrefix(String rawMessage){
        //NEEDS IMPLEMEMTATION
        return "";
    }
    
    public String getCommand(String rawMessage){
        //NEEDS IMPLEMENTATION
        return "";
    }
}
