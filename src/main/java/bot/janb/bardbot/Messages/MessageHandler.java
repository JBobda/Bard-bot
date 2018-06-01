package bot.janb.bardbot.Messages;

import bot.janb.bardbot.ResourceManager;
import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;

public class MessageHandler {
    
    private EmbedBuilder builder = new EmbedBuilder();
    private ResourceManager resManager = new ResourceManager();
    
    public EmbedBuilder embedBuilder(){
        builder.setTitle("Title of Field");
        builder.setColor(new Color(175, 143, 14));
        builder.setDescription("This is the description");
        builder.addField("Title of Field", "Sample field text.", false);
        //Make sure it is the raw Image
        builder.setImage("https://github.com/JBobda/Bard-bot/raw/master/src/main/resources/smollBard.jpg");
        //builder.setThumbnail("https://github.com/JBobda/Bard-bot/blob/master/src/main/resources/smollBard.jpg");
        
        return builder;
    }
    
    
}
