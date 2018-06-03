package bot.janb.bardbot.commands.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.*;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;


@CommandInfo(
    name = {"Play","Music"},
    description = "Plays the music requested in the arguments"
)
public class PlayCommand extends Command{
    
    private VoiceChannel voiceChannel;
    private AudioManager audioManager;
    
    public PlayCommand(){
        this.name = "play";
        this.help = "Plays the music requested in the arguments";
        this.guildOnly = true;
        this.aliases = new String[]{"music"};
    }

    @Override
    protected void execute(CommandEvent event) {
        openAudioChannel(event);
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
    
