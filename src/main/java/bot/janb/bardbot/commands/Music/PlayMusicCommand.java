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
        trackScheduler = new TrackScheduler(getPlayer());
        player.addListener(trackScheduler);
        AudioSourceManagers.registerRemoteSources(playerManager);   
    }

    @Override
    protected void execute(CommandEvent event) {
        getTrackScheduler().setEvent(event);
        if(!event.getArgs().isEmpty()){
            //Audio setup
            if (openAudioChannel(event)) {
                setSendHandler(new SendHandler(getPlayer()));
                getAudioManager().setSendingHandler(getSendHandler());
                
                //loads Music choices into the playManager
                getPlayerManager().loadItemOrdered(getPlayer(), "ytsearch:" + event.getArgs(),  new ResultHandler(getTrackScheduler()));
                
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
        setVoiceChannel(event.getMember().getVoiceState().getChannel());
        setAudioManager(event.getGuild().getAudioManager());
        
        String text;
        if(getVoiceChannel() != null){
            getAudioManager().openAudioConnection(getVoiceChannel());
            return true;
        }else{
            text = "Can't play music if you are not in a voice channel!";
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, text, event).build()).queue();
            return false;
        }
    }

    /**
     * @return the voiceChannel
     */
    public VoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    /**
     * @param voiceChannel the voiceChannel to set
     */
    public void setVoiceChannel(VoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    /**
     * @return the audioManager
     */
    public AudioManager getAudioManager() {
        return audioManager;
    }

    /**
     * @param audioManager the audioManager to set
     */
    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    /**
     * @return the playerManager
     */
    public AudioPlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * @return the player
     */
    public AudioPlayer getPlayer() {
        return player;
    }

    /**
     * @return the trackScheduler
     */
    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    /**
     * @return the sendHandler
     */
    public AudioSendHandler getSendHandler() {
        return sendHandler;
    }

    /**
     * @param sendHandler the sendHandler to set
     */
    public void setSendHandler(AudioSendHandler sendHandler) {
        this.sendHandler = sendHandler;
    }
}
    
