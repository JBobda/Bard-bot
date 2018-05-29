package bot.janb.generaldiscordbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;


@CommandInfo(
    name = "CoinFlip",
    description = "Flips a virtual coin"
)
        
public class CoinFlipCommand extends Command{

    public MessageChannel channel;
    
    public CoinFlipCommand(){
        this.name = "coin";
        this.help = "Flips a virtual coin";
        this.guildOnly = false;
        this.aliases = new String[]{"CoinToss", "Heads or Tails"};
    }
    
    @Override
    protected void execute(CommandEvent ceevent) {
        channel = ceevent.getChannel();
        Message message = new MessageBuilder()
                        .append("This command works")
                        .build();
        channel.sendMessage(message).queue();
    }

}
