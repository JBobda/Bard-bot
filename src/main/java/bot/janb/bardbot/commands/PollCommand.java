package bot.janb.bardbot.commands;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class PollCommand extends Command{
    
    public boolean hasExecuted = false;
    public int[] optionResults;
    public Message myMessage;
    public MessageAction mAction;

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
        optionResults = new int[choices.length];
        String[] symbols = {"\u0031\u20E3", "\u0032\u20E3", "\u0033\u20E3", "\u0034\u20E3", "\u0035\u20E3", "\u0036\u20E3", "\u0037\u20E3" + "\u0038\u20E3" + "\u0039\u20E3"};
        if (choices.length > 9) {
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "You have too many options!", event).build()).queue();
            return;
        }else if(choices.length <= 1){
            event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "There needs to be more than 1 option!", event).build()).queue();
            return;
        }
        
        EmbedBuilder builder = MessageHandler.embedBuilder(name, "The poll will last for 10 minutes, vote what you think is best!");
        
        for (int i = 0; i < choices.length; i++) {
            builder.addField("Option " + (i +1),symbols[i] + " " + choices[i], false);
        }
        
        
        mAction = event.getChannel().sendMessage(builder.build());
        myMessage = mAction.complete();
        
        
        for (int i = 0; i < choices.length; i++) {
            myMessage.addReaction(symbols[i]).complete();
        }
        
        hasExecuted = true;
        
    }

    public void countVotes(MessageReceivedEvent event) {
        hasExecuted = false;
        
        mAction = event.getMessage().editMessage(MessageHandler.embedBuilder(name, "The vote is over!").build());
        myMessage = mAction.complete();
        
        int[] results = new int[myMessage.getReactions().size()];
        int max = 0;
        for (int i = 0; i < results.length; i++) {
            results[i] = myMessage.getReactions().get(i).getCount();
            if (results[i] > results[max]) {
                max = i;
            }
        }
        
        event.getChannel().sendMessage(MessageHandler.embedBuilder(name, "Option " + (max + 1) + " has won the poll!").build()).queue();
        
        
    }

}
