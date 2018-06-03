package bot.janb.bardbot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import java.util.ArrayList;
import java.util.List;

public class TrackScheduler extends AudioEventAdapter{
    
    private AudioPlayer player;
    private List<AudioTrack> trackList;
    private int counter;

    public TrackScheduler(AudioPlayer player){
        this.player = player;
        this.trackList = new ArrayList<AudioTrack>();
        counter = 0;
    }
    
    /**
     * Adds AudioTracks to the list which acts as a
     * queue
     * 
     * @param track that needs to be added to the list(queue)
     */
    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            trackList.add(track);
        }
    }

}
