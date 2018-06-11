package bot.janb.bardbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import bot.janb.bardbot.commands.Music.*;
import bot.janb.bardbot.commands.PollCommand;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BotListener extends ListenerAdapter {

    public SkipMusicCommand skipCommand;
    public PlayMusicCommand playCommand;
    public PollCommand pollCommand;
    
    public ScheduledExecutorService scheduler;

    public BotListener(SkipMusicCommand skipCommand, PlayMusicCommand playCommand, PollCommand pollCommand) {
        this.skipCommand = skipCommand;
        this.playCommand = playCommand;
        this.pollCommand = pollCommand;
        
        scheduler  = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //For the Skip Command
        if (skipCommand.hasExecuted) {
        
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    skipCommand.loadCommand(event);
                }
            }, 15, TimeUnit.SECONDS);
            
            
        }
        
        if(pollCommand.hasExecuted){
            scheduler.schedule(new Runnable(){
                @Override
                public void run(){
                    pollCommand.countVotes(event);
                }
            }, 10, TimeUnit.MINUTES);
        }

    }

}
