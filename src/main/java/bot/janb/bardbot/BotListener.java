package bot.janb.bardbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        //Stops messages from other bot's to trigger commands.
        if(event.getAuthor().isBot()) return;
        //NEEDS IMPLEMENTATION
    }
    
}
