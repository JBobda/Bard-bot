package bot.janb.bardbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import java.awt.Color;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Mimic","Repeat"},
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
        MessageEmbed messageEmb = new EmbedBuilder()
                .setColor(new Color(175, 143, 14))
                .addField("Test Mimic", content, false)
                .build();
        
         
        channel.sendMessage(messageEmb).queue();
    }
}
