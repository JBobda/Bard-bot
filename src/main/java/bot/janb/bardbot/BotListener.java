package bot.janb.bardbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import bot.janb.bardbot.commands.Music.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BotListener extends ListenerAdapter {

    public SkipMusicCommand skipCommand;
    public PlayMusicCommand playCommand;

    public BotListener(SkipMusicCommand skipCommand, PlayMusicCommand playCommand) {
        this.skipCommand = skipCommand;
        this.playCommand = playCommand;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //For the Skip Command
        if (skipCommand.hasExecuted) {
            
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    skipCommand.loadCommand(event);
                }
            }, 15, TimeUnit.SECONDS);
            
            
        }

    }

}
