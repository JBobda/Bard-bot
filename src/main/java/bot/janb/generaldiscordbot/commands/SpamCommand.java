package bot.janb.generaldiscordbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Spam"},
    description = "Spams a message in chat."
)
public class SpamCommand extends Command{
    
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
        String content;
        if (event.getArgs().isEmpty()) {
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
