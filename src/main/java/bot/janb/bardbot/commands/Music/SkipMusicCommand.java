package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.TrackScheduler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

@CommandInfo(
        name = {"Skip", "VoteSkip"},
        description = "Skip the current music playing"
)
public class SkipMusicCommand extends Command {

    public final PlayMusicCommand playMusicCommand;
    public final TrackScheduler trackScheduler;
    public int yesVoteCount;
    public int noVoteCount;

    public SkipMusicCommand(PlayMusicCommand playMusicCommand) {
        this.name = "skip";
        this.help = "Skip the current music playing";
        this.guildOnly = true;
        this.aliases = new String[]{"voteskip"};

        this.playMusicCommand = playMusicCommand;
        this.trackScheduler = playMusicCommand.getTrackScheduler();
    }

    @Override
    protected void execute(CommandEvent event) {
        if (trackScheduler.getPlayer().getPlayingTrack() == null) {
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "You have to be listening to something to start a vote!").build()).queue();
            return;
        }

        yesVoteCount = 0;
        noVoteCount = 0;

        String embedMessage = "Quick! Vote wether or not you want to skip the current song!";
        MessageAction mAction = event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", embedMessage).build());
        Message myMessage = mAction.complete();

        //Thumbs up Reaction
        myMessage.addReaction("\uD83D\uDC4D").queue();
        //Thumbs down reaction
        myMessage.addReaction("\uD83D\uDC4E").queue();

        ScheduledExecutorService scheduledExecutorService
                = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.schedule(new Callable() {
                    public Object call() throws Exception {
                        yesVoteCount = myMessage.getReactions().get(0).getCount();
                        noVoteCount = myMessage.getReactions().get(1).getCount();
                        System.out.println("IT GOT HERE");
                        return "Called";
                    }
                }, 5, TimeUnit.SECONDS);

        /*
        try{
            Thread.sleep(5000);
            yesVoteCount = myMessage.getReactions().get(0).getCount();
            noVoteCount = myMessage.getReactions().get(1).getCount();
        } catch (InterruptedException ex) {
            Logger.getLogger(SkipMusicCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        System.out.println("IT ALSO GOT HERE");

        if (yesVoteCount > noVoteCount) {
            trackScheduler.nextTrack();
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "The current track has been skipped").build()).queue();
        }else{
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "The Vote skip has failed.").build()).queue();
        }
    }

}
