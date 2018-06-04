package bot.janb.bardbot;

import bot.janb.bardbot.commands.*;
import bot.janb.bardbot.commands.Music.PlayMusicCommand;
import bot.janb.bardbot.commands.fun.*;
import com.jagrosh.jdautilities.command.*;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.*;

public class BotLoader {
    
    public static final String COMMAND_PREFIX = "+";
    
    private JDA discord;
    private List<String> botInfo = new ArrayList<String>();
    private CommandClientBuilder cBuilder;
    private EventWaiter waiter;
    private ResourceManager resManager;
    private String token;
    private String ownerID;
    
    /**
     * Loads all of the proper information of the Bot
     * 
     * @throws LoginException
     * @throws InterruptedException
     * @throws IOException 
     */
    public void loadBot() throws LoginException, InterruptedException, IOException{
        //Gets the bot information from the config file
        resManager = new ResourceManager();
        botInfo = resManager.loadTextFile("/config.txt");
        
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
                .addEventListener(waiter)
                .addEventListener(cBuilder.build())
                .buildBlocking();

    }
    
    /**
     * Loads commands of the bot
     */
    public void loadCommands(){
        //General Commands
        cBuilder.addCommand(new SpamCommand());
        cBuilder.addCommand(new MimicCommand());
        
        //Fun Commands
        cBuilder.addCommand(new CoinFlipCommand());
        cBuilder.addCommand(new ChooseCommand());
        cBuilder.addCommand(new JokeCommand());
        
        //Music Commands
        cBuilder.addCommand(new PlayMusicCommand());
        
    }
    
    /**
     * Returns the CommandClient of this BotLoader
     * 
     * @return CommandClient 
     */
    public CommandClient getCommandClient(){
        return cBuilder.build();
    }
    
    
}
