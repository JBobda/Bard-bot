package bot.janb.bardbot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class ResultHandler implements AudioLoadResultHandler {

    private final TrackScheduler trackScheduler;

    public ResultHandler(TrackScheduler trackScheduler) {
        this.trackScheduler = trackScheduler;
    }

    /**
     * Runs if there is just one track loaded
     * 
     * @param track the track that will be added to the queue 
     */
    @Override
    public void trackLoaded(AudioTrack track) {
        trackScheduler.queue(track);
    }

    /**
     * Runs if there is an entire playlist loaded, like from
     * youtube search or soundcloud search
     * 
     * @param playlist the playlist that will be used to load tracks
     */
    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        for (AudioTrack track : playlist.getTracks()) {
            trackScheduler.queue(track);
            break;
        }
    }

    @Override
    public void noMatches() {
        //NEEDS IMPLEMENTATION
    }

    @Override
    public void loadFailed(FriendlyException fe) {
        //NEEDS IMPLEMENTATION
    }
}
