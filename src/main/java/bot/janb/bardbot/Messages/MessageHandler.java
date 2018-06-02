package bot.janb.bardbot.Messages;

import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;

public class MessageHandler {
    
    private EmbedBuilder builder = new EmbedBuilder();
    private Color embedColor = new Color(175, 143, 14);
    
    /**
     * Creates an EmbedBuilder containing one field that has the name of the
     * command calling it and the description of what that command wants to embed
     * 
     * @param commandTitle the title of the command that calls this method
     * @param description the message that the command wants to send in an embed
     * @return EmbedBuilder with field that has commandTitle and description
     */
    public EmbedBuilder embedBuilder(String commandTitle, String description){
        //Upper Case for the first letter
        commandTitle = commandTitle.substring(0,1).toUpperCase() + commandTitle.substring(1);
        
        builder.addField(commandTitle, description, true);
        builder.setColor(embedColor);
        
        return builder;
    }
    
    /**
     * Creates an EmbedBuilder containing one image from the URL that 
     * that is given
     * 
     * @param url the raw URL location of the image
     * @return EmbedBuilder with the image specified at the given URL
     */
    public EmbedBuilder embedBuilder(String url){
        builder.setImage(url);
        
        return builder;
    }
    
    /**
     * Creates an EmbedBuilder with generic information tied to it
     * 
     * @return EmbedBuilder with generic information
     */
    public EmbedBuilder embedBuilder(){
        builder.setTitle("Title");
        builder.setColor(embedColor);
        builder.setDescription("This is the description");
        builder.addField("Title of Field", "Sample field text.", false);
        //Make sure it is the raw Image
        builder.setImage("https://github.com/JBobda/Bard-bot/raw/master/src/main/resources/smollBard.jpg");
        //builder.setThumbnail("https://github.com/JBobda/Bard-bot/blob/master/src/main/resources/smollBard.jpg");
        
        return builder;
    }
    
    
}
