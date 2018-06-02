package bot.janb.bardbot.Messages;

import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;

public class MessageHandler {
    
    private EmbedBuilder builder = new EmbedBuilder();
    private Color embedColor = new Color(175, 143, 14);
    
    //Returns EmbedBuilder with one field that has a title and Description from @param
    public EmbedBuilder embedBuilder(String commandTitle, String description){
        //Upper Case for the first letter
        commandTitle = commandTitle.substring(0,1).toUpperCase() + commandTitle.substring(1);
        
        builder.addField(commandTitle, description, true);
        builder.setColor(embedColor);
        
        return builder;
    }
    
    //Returns EmbedBuilder with an image set to it from the @param
    public EmbedBuilder embedBuilder(String url){
        builder.setImage(url);
        
        return builder;
    }
    
    //Returns EmbedBuilder with generic information tied to it
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
