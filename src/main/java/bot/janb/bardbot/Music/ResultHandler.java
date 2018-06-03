package bot.janb.bardbot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class ResultHandler implements AudioLoadResultHandler{

    @Override
    public void trackLoaded(AudioTrack at) {
        
    }

    @Override
    public void playlistLoaded(AudioPlaylist ap) {
        
    }

    @Override
    public void noMatches() {
        
    }

    @Override
    public void loadFailed(FriendlyException fe) {
        
    }
}
