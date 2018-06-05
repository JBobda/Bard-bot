package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.Music.*;
import com.jagrosh.jdautilities.command.*;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
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
    private final AudioPlayerManager playerManager;
    private final AudioPlayer player;
    private final TrackScheduler trackScheduler;
    private AudioSendHandler sendHandler;
    
    public PlayMusicCommand(){
        //Sets up command
        this.name = "play";
        this.help = "Plays the music requested in the arguments";
        this.guildOnly = true;
        this.aliases = new String[]{"music"};
        
        //Music Setup
        playerManager = new DefaultAudioPlayerManager();
        player = playerManager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        AudioSourceManagers.registerRemoteSources(playerManager);   
    }

    @Override
    protected void execute(CommandEvent event) {
        trackScheduler.setEvent(event);
        if(!event.getArgs().isEmpty()){
            //Audio setup
            if (openAudioChannel(event)) {
                sendHandler = new SendHandler(player);
                audioManager.setSendingHandler(sendHandler);
                
                //loads Music choices into the playManager
                playerManager.loadItemOrdered(player, "ytsearch:" + event.getArgs(),  new ResultHandler(trackScheduler));
                
            }
        }else{
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "What do you want me to play?", event).build()).queue();
        } 
        
    }
    
    /**
     * Finds the voice channel of the user making the command
     * and has the bot join that channel
     * 
     * @param event that contains the voice channel the bot joins
     * @return boolean that confirms if the bot joined a voice channel
     */
    public boolean openAudioChannel(CommandEvent event){
        voiceChannel = event.getMember().getVoiceState().getChannel();
        audioManager = event.getGuild().getAudioManager();
        
        String text;
        if(voiceChannel != null){
            audioManager.openAudioConnection(voiceChannel);
            return true;
        }else{
            text = "Can't play music if you are not in a voice channel!";
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, text, event).build()).queue();
            return false;
        }
    }
}
    
