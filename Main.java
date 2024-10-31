import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

public class Main{

    // Regex to verify if a String contains a path.
    public static final String REGEX_PATH = "(?:[a-zA-Z]:)?(?:\\\\+|\\/)?(?:[\\w\\s-]+(?:\\\\+|\\/))*[\\w\\s-]+\\.[\\w]+";
    
    // Regex to verify if a String contains a positive integer.
    public static final String REGEX_POSITIVE_INTEGER = "\\+?\\d+";

    // The path of the image.
    public static String path = "";

    // The width that you want the image to be.
    public static int width = 0;

    // The height that you want the image to be.
    public static int height = 0;

    public static void main(String[] args){

        if(args.length > 0){
            handleTerminalInput(args);
        }

        File file = new File(path);
        try {
            BufferedImage img = ImageIO.read(file);
            ImageToAscii.printImageToAscii(img, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handle the terminal inputs from the user.
     * <p>Acceptance examples:</p>
     * <p>(String path)</p>
     * <p>(String path) (int width)</p>
     * <p>(String path) (int width) (int height)</p>
     * <p>(int width)</p>
     * <p>(int width) (int height)</p>
     * @param args The terminal inputs.
     */
    private static void handleTerminalInput(String[] args){

        String string = "";

        // Convert the strings array to a single one
        for(int i = 0; i < args.length; i++){
            string += args[i];
            if(i != args.length - 1){
                string += " ";
            }
        }

        string = string.trim();

        // Compile the regex to verify if the String contains a valid path.
        Matcher matcher = Pattern.compile(REGEX_PATH).matcher(string);
        
        // Tries to find if the args contains a valid path for the image.
        if(matcher.find()){
            path = matcher.group();
            // Remove itself from the string
            string = matcher.replaceAll("").trim();
        }

        // Compile the regex to verify if the String contains a positive integer.
        matcher = Pattern.compile(REGEX_POSITIVE_INTEGER).matcher(string);

        // Tries to find if the new string contains a valid path for the image.
        if(matcher.find()){
            // The first ocurrence is the width.
            width = Integer.parseInt(matcher.group());
            // Remove itself from the string
            string = matcher.replaceFirst("").trim();
            if(matcher.find()){
                // The second ocurrence is the height.
                height = Integer.parseInt(matcher.group());
            }
        }
    }

}