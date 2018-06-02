package bot.janb.bardbot.commands;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;


@CommandInfo(
    name = {"Mimic","Repeat"},
    description = "Repeats the user's input back"
)
public class MimicCommand extends Command{

    private MessageChannel channel;
    private MessageHandler mHandler;
    
    public MimicCommand(){
        this.name = "mimic";
        this.help = "Repeats the user's input";
        this.guildOnly = false;
        this.aliases = new String[]{"repeat"};
    }
    
    /**
     * Simply repeats what the user said in the 
     * channel of the event
     * 
     * @param event 
     */
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        mHandler = new MessageHandler();
        String content = event.getMessage().getContentRaw();
        if(event.getArgs().isEmpty()){
            content = "Please enter arguments after the command.";
        }else{
            content = event.getArgs();
        }
        
        channel.sendMessage(mHandler.embedBuilder(name, content, event).build()).queue();
    }
}
