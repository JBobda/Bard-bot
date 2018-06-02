package bot.janb.bardbot;

import java.io.IOException;
import javax.security.auth.login.LoginException;

public class Main {
    
    /**
     * Main method of the program, calls the BotLoader and
     * catches any of the errors that it might throw.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        BotLoader loader = new BotLoader();
        try{
            loader.loadBot();
        }catch(LoginException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

}
