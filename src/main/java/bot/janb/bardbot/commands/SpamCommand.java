package bot.janb.bardbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Spam"},
    description = "Spams a message in chat."
)
public class SpamCommand extends Command implements Runnable{
    
    private MessageChannel channel;

    public SpamCommand(){
        this.name = "spam";
        this.help = "Spams a message in chat";
        this.guildOnly = true;
        //this.aliases = new String[]{""};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        boolean shouldSpam;
        String content;
        if (event.getArgs().isEmpty()) {
            content = "Please enter arguments after the command.";
            shouldSpam = false;
        }else{
            content = event.getArgs();
            shouldSpam = true;
        }
        
        
        Message message = new MessageBuilder()
                .append(content)
                .build();
        if (!shouldSpam){
            channel.sendMessage(message).queue();
        }else{
            Thread thread = new Thread(this);
            for(int i = 0; i < 10; i++){
                channel.sendMessage(message).queue();
                try {
                    thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpamCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }

    @Override
    public void run() {
        //TODO: Run later
    }

}
