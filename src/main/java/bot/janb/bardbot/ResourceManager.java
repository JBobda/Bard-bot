package bot.janb.bardbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ResourceManager {
    private List<String> list = new ArrayList<String>();
    
    public List<String> loadFile(String path) throws IOException{
        InputStream is = getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = reader.readLine()) != null){
            list.add(line);
        }
        return list;
    }
}
