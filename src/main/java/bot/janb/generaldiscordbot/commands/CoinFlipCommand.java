package bot.janb.generaldiscordbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;


@CommandInfo(
    name = "CoinFlip",
    description = "Flips a virtual coin"
)
        
public class CoinFlipCommand extends Command{

    public CoinFlipCommand(){
        this.name = "CoinFlip";
        this.help = "Flips a virtual coin";
        this.guildOnly = false;
        this.aliases = new String[]{"CoinToss", "Heads or Tails"};
    }
    
    @Override
    protected void execute(CommandEvent ceevent) {
        
    }

}
