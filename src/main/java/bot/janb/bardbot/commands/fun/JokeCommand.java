package bot.janb.bardbot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Joke","Pun"},
    description = "Randomly chooses a joke from a list of jokes."
)
public class JokeCommand extends Command{

    private MessageChannel channel;
    private List<String> jokes;
    
    public JokeCommand(){
        this.name = "joke";
        this.help = "Tells you a random joke";
        this.guildOnly = false;
        this.aliases = new String[]{"pun"};       
    }

    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        try {
            jokes = Files.readAllLines(Paths.get("res/jokes.txt"));
            jokes.add(event.getAuthor().getAsMention() + "! Get it? " + event.getAuthor().getAsMention() + " is the joke!");
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(JokeCommand.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        int choice = (int)(Math.random() * jokes.size());
        Message message = new MessageBuilder()
                .append(jokes.get(choice))
                .build();
        
        channel.sendMessage(message).queue();
    }
    
}
