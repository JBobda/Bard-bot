package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.TrackScheduler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

@CommandInfo(
        name = {"Skip", "VoteSkip"},
        description = "Skip the current music playing"
)
public class SkipMusicCommand extends Command {

    public final PlayMusicCommand playMusicCommand;
    public final TrackScheduler trackScheduler;
    public boolean hasExecuted;
    public MessageAction mAction;
    public Message myMessage;
    public int yesVoteCount;
    public int noVoteCount;

    public SkipMusicCommand(PlayMusicCommand playMusicCommand) {
        this.name = "skip";
        this.help = "Skip the current music playing";
        this.guildOnly = true;
        this.aliases = new String[]{"voteskip"};

        this.playMusicCommand = playMusicCommand;
        this.trackScheduler = playMusicCommand.getTrackScheduler();
        this.hasExecuted = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (trackScheduler.getPlayer().getPlayingTrack() == null) {
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "You have to be listening to something to start a vote!").build()).queue();
            return;
        }

        String embedMessage = "Quick! Vote wether or not you want to skip the current song!";
        mAction = event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", embedMessage).build());
        myMessage = mAction.complete();

        //Thumbs up Reaction
        myMessage.addReaction("\uD83D\uDC4D").complete();
        //Thumbs down reaction
        myMessage.addReaction("\uD83D\uDC4E").complete();  
        
        hasExecuted = true;
        
    }
    
    public void loadCommand(MessageReceivedEvent event){
        //Prevents the BotListener from calling this method in a nonstop cycle
        hasExecuted = false;
        int yesVoteCount = 0;
        int noVoteCount = 0;

        mAction = event.getMessage().editMessage(MessageHandler.embedBuilder("Vote Skip", "The time to vote is over, time has run out!").build());
        myMessage = mAction.complete();
        yesVoteCount = myMessage.getReactions().get(0).getCount();
        noVoteCount = myMessage.getReactions().get(1).getCount();

        if (yesVoteCount > noVoteCount) {
            System.out.println("THE VOTE WAS SUCCESFUL!");
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "The current track has been skipped").build()).queue();
            trackScheduler.nextTrack();
        } else {
            System.out.println("THE VOTE HAS FAILED!");
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "The Vote skip has failed.").build()).queue();
        }
    }

}
