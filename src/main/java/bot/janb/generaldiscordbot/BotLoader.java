package bot.janb.generaldiscordbot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
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
    
    private static final String COMMAND_PREFIX = "!apx ";
    
    private JDA discord;
    private List<String> botInfo;
    private CommandClientBuilder cBuilder;
    private String token;
    private String ownerID;
    
    public void loadBot() throws LoginException, InterruptedException{
        //Gets the bot information from the config file
        try{
            botInfo = Files.readAllLines(Paths.get("config.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        token = botInfo.get(0);
        ownerID = botInfo.get(1);
        
        cBuilder = new CommandClientBuilder();
        cBuilder.useDefaultGame();
        cBuilder.setOwnerId(ownerID);
        cBuilder.setPrefix(COMMAND_PREFIX);
        
        
        //Declares the JDA using the JDABuilder
        discord = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.playing("[GAME]"))
                .addEventListener(new BotListener())
                .addEventListener(cBuilder.build())
                .buildBlocking();

    }
}
