package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.*;
import com.jagrosh.jdautilities.command.*;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.audio.AudioSendHandler;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;


@CommandInfo(
    name = {"Play","Music"},
    description = "Plays the music requested in the arguments"
)
public class PlayMusicCommand extends Command{
    
    private VoiceChannel voiceChannel;
    private AudioManager audioManager;
    private AudioPlayerManager playerManager;
    private AudioPlayer player;
    private TrackScheduler trackScheduler;
    private AudioSendHandler sendHandler;
    
    public PlayMusicCommand(){
        //Sets up command
        this.name = "play";
        this.help = "Plays the music requested in the arguments";
        this.guildOnly = true;
        this.aliases = new String[]{"music"};
        
    }

    @Override
    protected void execute(CommandEvent event) {
        //Audio setup
        openAudioChannel(event);
        
        //Music Setup
        playerManager = new DefaultAudioPlayerManager();
        player = playerManager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        AudioSourceManagers.registerRemoteSources(playerManager); 
        playerManager.loadItemOrdered(event.getGuild(), "https://www.youtube.com/watch?v=xpVfcZ0ZcFM",  new ResultHandler(trackScheduler));
        
        sendHandler = new SendHandler(player);
        audioManager.setSendingHandler(sendHandler);
        
    }
    
    /**
     * Finds the voice channel of the user making the command
     * and has the bot join that channel
     * 
     * @param event that contains the voice channel the bot joins
     */
    public void openAudioChannel(CommandEvent event){
        voiceChannel = event.getMember().getVoiceState().getChannel();
        audioManager = event.getGuild().getAudioManager();
        
        String text;
        if(voiceChannel != null){
            audioManager.openAudioConnection(voiceChannel);
        }else{
            text = "Can't play music if you are not in a voice channel!";
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, text, event).build()).queue();
        }
    }
}
    
