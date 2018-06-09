package bot.janb.bardbot.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import net.dv8tion.jda.core.entities.Guild;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private CommandEvent event;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Adds AudioTracks to the queue, if there is no track
     * currently playing, it will play the first requested track
     *
     * @param track that needs to be added to the list(queue)
     */
    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            queue.offer(track);
            event.getChannel()
                 .sendMessage(MessageHandler.embedBuilder("Music", track.getIdentifier()+ "has been added to the queue", event).build())
                 .queue();
        } else {
            if(!queue.isEmpty()){
                event.getChannel()
                     .sendMessage(MessageHandler.embedBuilder("Music", "Now playing", event).build())
                     .queue();
            }

        }
    }

    /**
     * Plays the next track in the queue and sends a message in
     * the channel saying that the track is now playing
     * 
     * @param track that should be played or added to queue
     */
    public void nextTrack() {
        Guild guild = event.getGuild();
        if (queue.isEmpty()) {
            new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        guild.getAudioManager().closeAudioConnection();
                    }
                }, 500);
        }
        player.startTrack(queue.poll(), false);
        event.getChannel()
                 .sendMessage(MessageHandler.embedBuilder("Music","Now playing", event).build())
                 .queue();
        
    }

    /**
     * When the track currently playing ends it will look to see if the queue is empty
     * if it still has items, it will play the next item in the queue
     * 
     * @param player the player object 
     * @param track the track that has ended
     * @param endReason the reason why the track ended
     */
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        event.getChannel().sendMessage(MessageHandler.embedBuilder("Music", track.getIdentifier() + " has ended").build()).queue();
        // Only start the next track if the end reason is suitable for it (FINISHED or LOAD_FAILED)
        if(endReason.mayStartNext) {
            nextTrack();
        }
        
    }

    /**
     * Allows for outside classes to set the event for this 
     * TrackScheduler
     * 
     * @param event the event that is used for channel info 
     */
    public void setEvent(CommandEvent event) {
        this.event = event;
    }
    
    /**
     * Allows access to the audio player to outside classes
     * @return AudioPlayer the player of this trackScheduler
     */
    public AudioPlayer getPlayer(){
        return player;
    }
    
}
