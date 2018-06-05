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

    @Override
    public void trackLoaded(AudioTrack track) {
        trackScheduler.queue(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        for (AudioTrack track : playlist.getTracks()) {
            trackScheduler.queue(track);
            break;
        }
    }

    @Override
    public void noMatches() {

    }

    @Override
    public void loadFailed(FriendlyException fe) {

    }
}
