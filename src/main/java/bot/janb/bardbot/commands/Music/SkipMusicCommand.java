package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.TrackScheduler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

@CommandInfo(
    name = {"Skip", "VoteSkip"},
    description = "Skip the current music playing"
)
public class SkipMusicCommand extends Command{
    
    public final PlayMusicCommand playMusicCommand;
    public final TrackScheduler trackScheduler;
    public int yesVoteCount;
    public int noVoteCount;

    public SkipMusicCommand(PlayMusicCommand playMusicCommand){
        this.name = "skip";
        this.help = "Skip the current music playing";
        this.guildOnly = true;
        this.aliases = new String[]{"voteskip"};
        
        this.playMusicCommand = playMusicCommand;
        this.trackScheduler = playMusicCommand.getTrackScheduler();
    }
    
    @Override
    protected void execute(CommandEvent event) {
        yesVoteCount = 0;
        noVoteCount = 0;
        
        String embedMessage = "Quick! Vote wether or not you want to skip the current song!";
        //event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", embedMessage).build()).queue();
        RestAction<Message> mAction = event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", embedMessage).build());
        Message myMessage = mAction.complete();
        //Thumbs up Reaction
        myMessage.addReaction("\uD83D\uDC4D").queue();
        //Thumbs down reaction
        myMessage.addReaction("\uD83D\uDC4E").queue();

        if(yesVoteCount > noVoteCount){
            trackScheduler.nextTrack();
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Vote Skip", "The current track has been skipped").build()).queue();
        }
    }

}
