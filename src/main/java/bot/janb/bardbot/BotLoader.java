package bot.janb.bardbot;

import bot.janb.bardbot.commands.*;
import bot.janb.bardbot.commands.fun.*;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;

public class BotLoader {
    
    public static final String COMMAND_PREFIX = "+";
    
    private JDA discord;
    private List<String> botInfo = new ArrayList<String>();
    private CommandClientBuilder cBuilder;
    private EventWaiter waiter;
    private String token;
    private String ownerID;
    
    public void loadBot() throws LoginException, InterruptedException, IOException{
        //Gets the bot information from the config file
        loadConfiguration();    
        
        //Sets up the Event waiter
        waiter = new EventWaiter();
        
        //Config
        token = botInfo.get(0);
        ownerID = botInfo.get(1);
        
        //Command Client setup
        cBuilder = new CommandClientBuilder();
        cBuilder.useDefaultGame();
        cBuilder.setOwnerId(ownerID);
        cBuilder.setPrefix(COMMAND_PREFIX);
        
        //Add commands
        loadCommands();
        
        //Declares the JDA using the JDABuilder
        discord = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.playing("[GAME]"))
                .addEventListener(waiter)
                .addEventListener(cBuilder.build())
                .buildBlocking();

    }
    
    public void loadCommands(){
        //General Commands
        cBuilder.addCommand(new SpamCommand());
        cBuilder.addCommand(new MimicCommand());
        
        //Fun Commands
        cBuilder.addCommand(new CoinFlipCommand());
        cBuilder.addCommand(new ChooseCommand());
        cBuilder.addCommand(new JokeCommand());
        
    }
    
    public void loadConfiguration() throws IOException{
        InputStream is = getClass().getResourceAsStream("/config.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = reader.readLine()) != null){
            botInfo.add(line);
        }
    }
    
}
