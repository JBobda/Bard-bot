package bot.janb.bardbot.Music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import java.util.HashSet;
import java.util.Set;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

public class AudioInfo {
    private final AudioTrack track;
    private final Set<String> skips;
    private final Member author;
    
    public AudioInfo(AudioTrack track, Member author){
        this.track = track;
        this.skips = new HashSet<>();
        this.author = author;
    }

    /**
     * @return the track
     */
    public AudioTrack getTrack() {
        return track;
    }

    /**
     * @return integer the skips size
     */
    public int getSkips() {
        return skips.size();
    }
    
    /**
     * Adds user to the skips HashSet
     * @param user 
     */
    public void addSkip(User user){
        skips.add(user.getId());
    }
    
    /**
     * Checks if the user has already voted to skip
     * @param user that makes the command
     * @return boolean of if the user has already voted
     */
    public boolean hasVoted(User user){
        return skips.contains(user.getId());
    }

    /**
     * @return the author
     */
    public Member getAuthor() {
        return author;
    }
}
