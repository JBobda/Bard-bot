package bot.janb.generaldiscordbot.commands;

import bot.janb.generaldiscordbot.BotLoader;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = "Mimic",
    description = "Repeats the user's input back"
)
public class MimicCommand extends Command{

    private MessageChannel channel;
    
    public MimicCommand(){
        this.name = "mimic";
        this.help = "Repeats the user's input";
        this.guildOnly = false;
        this.aliases = new String[]{"repeat"};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        String content = event.getMessage().getContentRaw();
        if(event.getArgs().isEmpty()){
            content = "Please enter arguments after the command.";
        }else{
            content = event.getArgs();
        }
        Message message = new MessageBuilder()
                .append(content)
                .build();
        
        channel.sendMessage(message).queue();
    }
}
