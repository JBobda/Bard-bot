package bot.janb.generaldiscordbot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = "Choose",
    description = "Randomly chooses betweena list of options."
)
public class ChooseCommand extends Command{

    private MessageChannel channel;
    
    public ChooseCommand(){
        this.name = "choose";
        this.help = "chooses between different options";
        this.guildOnly = false;
        this.aliases = new String[]{"pick"};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        Message message;
        if(event.getArgs().isEmpty()){
            message = new MessageBuilder()
                    .append("You didn't offer me any choices!")
                    .build();
        }else{
            String[] choices = event.getArgs().split("\\s+");
            int choice = (int)(Math.random() * choices.length);
            message = new MessageBuilder()
                    .append(choices[choice] + " is most clearly the right choice!")
                    .build();
        }
        
        channel.sendMessage(message).queue();
    }
    
}
