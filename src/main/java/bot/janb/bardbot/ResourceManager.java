package bot.janb.bardbot;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.imageio.ImageIO;

public class ResourceManager {
    private List<String> list;
    private BufferedImage image;
    
    /**
     * Loads any text file within the resources folder
     * 
     * @param path , the location of the file
     * @return List<String> a list object with each index being a line of the file.
     * @throws IOException 
     */
    public List<String> loadTextFile(String path) throws IOException{
        list = new ArrayList<String>();
        InputStream is = getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = reader.readLine()) != null){
            list.add(line);
        }
        return list;
    }
    
    /**
     * Loads any image file within the resources folder
     * 
     * @param path , the location of the file
     * @return BufferedImage a buffered image of the image located at the specified path
     * @throws IOException 
     */
    public BufferedImage loadImageFile(String path) throws IOException{
        InputStream is = getClass().getResourceAsStream(path);
        image = ImageIO.read(is);
        
        return image;
    }
}
