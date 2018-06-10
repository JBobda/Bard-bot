package bot.janb.bardbot.Messages;

import com.jagrosh.jdautilities.command.CommandEvent;
import java.awt.Color;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class MessageHandler {
    
    private static final Color EMBED_DEFAULT_COLOR = new Color(175, 143, 14);
    private static EmbedBuilder builder;
    
    /**
     * Creates an EmbedBuilder containing one field that has the name of the
     * command calling it and the description of what that command wants to embed
     * 
     * @param commandTitle the title of the command that calls this method
     * @param description the message that the command wants to send in an embed
     * @return EmbedBuilder with field that has commandTitle and description
     */
    public static EmbedBuilder embedBuilder(String commandTitle, String description){
        //Upper Case for the first letter
        commandTitle = commandTitle.substring(0,1).toUpperCase() + commandTitle.substring(1);
        
        builder = new EmbedBuilder();
        builder.addField(commandTitle, description, true);
        builder.setColor(EMBED_DEFAULT_COLOR);
        
        return builder;
    }
    
    /**
     * Creates an EmbedBuilder containing one field that has the name of the
     * command calling it, the author's username and picture and the description 
     * of what that command wants to embed
     * 
     * @param commandTitle the title of the command that calls this method
     * @param description the message that the command wants to send in an embed
     * @return EmbedBuilder with field that has commandTitle and description
     */
    public static EmbedBuilder embedBuilder(String commandTitle, String description, CommandEvent event){
        //Upper Case for the first letter
        commandTitle = commandTitle.substring(0,1).toUpperCase() + commandTitle.substring(1);
        User author = event.getAuthor();
        
        builder = new EmbedBuilder();
        builder.setAuthor(author.getName(), null, author.getAvatarUrl());
        builder.addField(commandTitle, description, true);
        builder.setColor(EMBED_DEFAULT_COLOR);
        
        return builder;
    }
    
    /**
     * Creates an EmbedBuilder containing one image from the URL that 
     * that is given
     * 
     * @param url the raw URL location of the image
     * @return EmbedBuilder with the image specified at the given URL
     */
    public static EmbedBuilder embedBuilder(String url){
        builder = new EmbedBuilder();
        builder.setImage(url);
        builder.setColor(EMBED_DEFAULT_COLOR);
        
        return builder;
    }
    
    /**
     * Creates an EmbedBuilder with generic information tied to it
     * 
     * @return EmbedBuilder with generic information
     */
    public static EmbedBuilder embedBuilder(){
        
        builder = new EmbedBuilder();
        builder.setTitle("Title");
        builder.setColor(EMBED_DEFAULT_COLOR);
        builder.setDescription("This is the description");
        builder.addField("Title of Field", "Sample field text.", false);
        //Make sure it is the raw Image
        builder.setImage("https://github.com/JBobda/Bard-bot/raw/master/src/main/resources/smollBard.jpg");
        //builder.setThumbnail("https://github.com/JBobda/Bard-bot/blob/master/src/main/resources/smollBard.jpg");
        
        return builder;
    }
    
    /**
     * Automatically deletes a message sent by the bot after 7 seconds
     * using the ScheduledExecutor 
     * 
     * @param message requires a message to be deleted
     */
    public static void autoDelete(Message message){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        scheduler.schedule(new Runnable(){
            @Override
            public void run(){
                message.delete().complete();
            }
        }, 7, TimeUnit.SECONDS);
        
    }
    
    
}
