package bot.janb.generaldiscordbot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;

public class Main {
    
    private static Main main;
    private JDA discord;
    
    public static void main(String[] args) {
        main = new Main();
        main.start();
    }
    
    public void start(){
        try{
            discord = new JDABuilder(AccountType.BOT)
                    .setToken("[TOKEN]")
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setGame(Game.playing("[GAME]"))
                    .buildBlocking();
        }catch(LoginException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        discord.addEventListener(new MessageListener());
        
    }

}
