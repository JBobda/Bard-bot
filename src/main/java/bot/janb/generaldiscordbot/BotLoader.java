package bot.janb.generaldiscordbot;

import bot.janb.generaldiscordbot.commands.MimicCommand;
import bot.janb.generaldiscordbot.commands.SpamCommand;
import bot.janb.generaldiscordbot.commands.fun.ChooseCommand;
import bot.janb.generaldiscordbot.commands.fun.CoinFlipCommand;
import bot.janb.generaldiscordbot.commands.fun.JokeCommand;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class BotLoader {
    
    public static final String COMMAND_PREFIX = "!apx ";
    
    private JDA discord;
    private List<String> botInfo;
    private CommandClientBuilder cBuilder;
    private String token;
    private String ownerID;
    
    public void loadBot() throws LoginException, InterruptedException{
        //Gets the bot information from the config file
        try{
            botInfo = Files.readAllLines(Paths.get("res/config.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        EventWaiter waiter = new EventWaiter();
        
        token = botInfo.get(0);
        ownerID = botInfo.get(1);
        
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
        //Commands
        cBuilder.addCommand(new CoinFlipCommand());
        cBuilder.addCommand(new MimicCommand());
        cBuilder.addCommand(new ChooseCommand());
        cBuilder.addCommand(new JokeCommand());
        cBuilder.addCommand(new SpamCommand());
    }
    
}
