package bot.janb.bardbot.commands;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class PollCommand extends Command{

    public PollCommand(){
        this.name = "poll";
        this.help = "Create a poll for your server.";
        this.guildOnly = true;
        this.aliases = new String[]{"vote"};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "You didn't give any options!", event).build()).queue();
            return;
        }
        String[] choices = event.getArgs().split("\\s+");
        System.out.println("CHOICES LENGTH IS " + choices[0]);
        String[] symbols = {"\u0031\u20E3", "\u0032\u20E3", "\u0033\u20E3", "\u0034\u20E3", "\u0035\u20E3", "\u0036\u20E3", "\u0037\u20E3" + "\u0038\u20E3" + "\u0039\u20E3"};
        if (choices.length > 9) {
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "You have too many options!", event).build()).queue();
            return;
        }else if(choices.length <= 1){
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "You didn't give any options!", event).build()).queue();
        }
        
        EmbedBuilder builder = MessageHandler.embedBuilder(name, "Vote on what choice best suits you!");
        
        for (int i = 0; i < choices.length; i++) {
            builder.addField("", symbols[i] + " " + choices[i], false);
        }
        
        
        MessageAction mAction = event.getChannel().sendMessage(builder.build());
        Message myMessage = mAction.complete();
        
        for (int i = 0; i < choices.length; i++) {
            myMessage.addReaction(symbols[i]);
        }
        
    }

}
