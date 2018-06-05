package bot.janb.bardbot.Music;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private CommandEvent event;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Adds AudioTracks to the list which acts as a queue
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
            event.getChannel()
                 .sendMessage(MessageHandler.embedBuilder("Music", "Now playing", event).build())
                 .queue();

        }
    }

    public void nextTrack(AudioTrack track) {
        player.startTrack(queue.poll(), false);
        event.getChannel()
                 .sendMessage(MessageHandler.embedBuilder("Music",track.getIdentifier() + "Now playing", event).build())
                 .queue();
        
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        event.getChannel().sendMessage(MessageHandler.embedBuilder("Music", track.getIdentifier() + " has ended").build()).queue();
        // Only start the next track if the end reason is suitable for it (FINISHED or LOAD_FAILED)
        if (endReason.mayStartNext) {
            if (queue.isEmpty()) {
                return;
            }
            nextTrack(track);
        }
    }

    public void setEvent(CommandEvent event) {
        this.event = event;
    }

}
