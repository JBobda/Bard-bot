package bot.janb.bardbot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;


@CommandInfo(
    name = "Coin",
    description = "Flips a virtual coin"
)
        
public class CoinFlipCommand extends Command{

    public MessageChannel channel;
    
    public CoinFlipCommand(){
        this.name = "coin";
        this.help = "Flips a virtual coin";
        this.guildOnly = false;
        this.aliases = new String[]{"flip"};
    }
    
    @Override
    protected void execute(CommandEvent ceevent) {
        //Finds channel to respond in
        channel = ceevent.getChannel();
        
        //Flips coin
        int choice = (int)(Math.random() * 2);
        String coin;
        if(choice == 1){
            coin = "Heads";
        }else{
            coin = "Tails";
        }
        
        Message message = new MessageBuilder()
                        .append(coin)
                        .build();
        channel.sendMessage(message).queue();
    }

}
