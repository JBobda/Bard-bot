package bot.janb.bardbot.commands.fun;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Choose", "Pick"},
    description = "Randomly chooses betweena list of options."
)
public class ChooseCommand extends Command{

    private MessageChannel channel;
    private MessageHandler mHandler;
    
    public ChooseCommand(){
        this.name = "choose";
        this.help = "chooses between different options";
        this.guildOnly = false;
        this.aliases = new String[]{"pick"};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        mHandler = new MessageHandler();
        String content;
        if(event.getArgs().isEmpty()){
            content = "You didn't offer me any choices!";
        }else{
            String[] choices = event.getArgs().split("\\s+");
            int choice = (int)(Math.random() * choices.length);
            content = choices[choice] + " is most clearly the right choice!";
        }
        
        channel.sendMessage(mHandler.embedBuilder(name, content).build()).queue();
    }
    
}
