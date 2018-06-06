package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.TrackScheduler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;

@CommandInfo(
    name = {"Skip", "VoteSkip"},
    description = "Skip the current music playing"
)
public class SkipMusicCommand extends Command{
    
    public final PlayMusicCommand playMusicCommand;
    public final TrackScheduler trackScheduler;

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
        trackScheduler.nextTrack();
        event.getChannel().sendMessage(MessageHandler.embedBuilder("Music", "The current track has been skipped", event).build()).queue();
    }

}
