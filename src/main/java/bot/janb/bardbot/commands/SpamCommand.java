package bot.janb.bardbot.commands;

import bot.janb.bardbot.Messages.MessageHandler;
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
    private MessageHandler mHandler;

    public SpamCommand(){
        this.name = "spam";
        this.help = "Spams a message in chat";
        this.guildOnly = true;
        //this.aliases = new String[]{""};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        mHandler = new MessageHandler();
        boolean shouldSpam;
        String content;
        if (event.getArgs().isEmpty()) {
            content = "Please enter arguments after the command.";
            shouldSpam = false;
        }else{
            content = event.getArgs();
            shouldSpam = true;
        }
        
        if (!shouldSpam){
            channel.sendMessage(mHandler.embedBuilder(name, content).build()).queue();
        }else{
            Thread thread = new Thread(this);
            for(int i = 0; i < 10; i++){
                channel.sendMessage(mHandler.embedBuilder(name, content).build()).queue();
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
