package bot.janb.bardbot;

import javax.security.auth.login.LoginException;

public class Main {
    
    public static void main(String[] args) {
        BotLoader loader = new BotLoader();
        try{
            loader.loadBot();
        }catch(LoginException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    

}
