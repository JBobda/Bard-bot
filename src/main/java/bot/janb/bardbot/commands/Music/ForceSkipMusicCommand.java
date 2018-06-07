package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.TrackScheduler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.Permission;

@CommandInfo(
        name = "ForceSkip",
        description = "Forcably skips the current song"
)
public class ForceSkipMusicCommand extends Command{

    public final PlayMusicCommand playMusicCommand;
    public final TrackScheduler trackScheduler;
    
    public ForceSkipMusicCommand(PlayMusicCommand playMusicCommand){
        this.name = "forceskip";
        this.help = "Forceably skips the current song";
        this.guildOnly = true;
        
        this.playMusicCommand = playMusicCommand;
        this.trackScheduler = playMusicCommand.getTrackScheduler();
    }
    
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMember().getPermissions().contains(Permission.MANAGE_SERVER)){
            trackScheduler.nextTrack();
            //event.getChannel().sendMessage(MessageHandler.embedBuilder("Music", "Song has been Force Skipped", event).build()).queue();
        }else{
            event.getChannel().sendMessage(MessageHandler.embedBuilder("Music", "You do not have the permissions for this command", event).build()).queue();
        }
        
    }

}
