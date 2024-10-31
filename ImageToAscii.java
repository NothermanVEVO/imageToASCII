import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class ImageToAscii {

    /*
     * All flags for the brightness.
     */
    public static final char ZERO = '@';
    public static final char UM = '#';
    public static final char DOIS = 'S';
    public static final char TRES = '%';
    public static final char QUATRO = '?';
    public static final char CINCO = '*';
    public static final char SEIS = '+';
    public static final char SETE = ';';
    public static final char OITO = ':';
    public static final char NOVE = ',';
    public static final char DEZ = '.';

    /**
     * Gets each pixel of the image and transform into a correspondent character based in each pixel of the image.
     * @param img The buffered image.
     * @param width The scaled width.
     * @param height The scaled height.
     * @return All the pixels of the image converted to ascii characters.
     */
    public static char[][] getImageToAscii(BufferedImage img, int width, int height){
        char[][] chars = new char[height][width];

        try {
            if(img == null){
                throw new Exception("The image can't be null!");
            } else if(width <= 0){
                throw new Exception("Width needs to be higher than 0!");
            } else if(height <= 0){
                throw new Exception("Height needs to be higher than 0!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return chars;
        }

        img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
        Color[][] colors = getImgColors(img);
        for(int i = 0; i < colors.length; i++){
            for(int j = 0; j < colors[i].length; j++){
                chars[i][j] = colorToChar(colors[i][j]);
            }
        }
        return chars;
    }

    /**
     * Gets each pixel of the image and transform into a correspondent character based in each pixel of the image.
     * @param img The image.
     * @param width The scaled width.
     * @param height The scaled height.
     * @return All the pixels of the image converted to ascii characters.
     */
    public static char[][] getImageToAscii(Image img, int width, int height){
        char[][] chars = new char[height][width];

        try {
            if(img == null){
                throw new Exception("The image can't be null!");
            } else if(width <= 0){
                throw new Exception("Width needs to be higher than 0!");
            } else if(height <= 0){
                throw new Exception("Height needs to be higher than 0!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return chars;
        }

        img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
        Color[][] colors = getImgColors(img);
        for(int i = 0; i < colors.length; i++){
            for(int j = 0; j < colors[i].length; j++){
                chars[i][j] = colorToChar(colors[i][j]);
            }
        }
        return chars;
    }

    /**
     * Print the image to it's correspondent ascii characters.
     * @param img The buffered image.
     * @param width The scaled width.
     * @param height The scaled height.
     */
    public static void printImageToAscii(BufferedImage img, int width, int height){
        char[][] characters = getImageToAscii(img, width, height);
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                System.out.print(characters[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Print the image to it's correspondent ascii characters.
     * @param img The image.
     * @param width The scaled width.
     * @param height The scaled height.
     */
    public static void printImageToAscii(Image img, int width, int height){
        char[][] characters = getImageToAscii(img, width, height);
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                System.out.print(characters[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Get all the colors of a BufferedImage, it gets the Color of each pixel.
     * @param img The BufferedImage.
     * @return An 2 dimensional array of Color of each pixel of the BufferedImage.
     */
    public static Color[][] getImgColors(BufferedImage img){
        int m = img.getHeight();
        int n = img.getWidth();
        Color[][] colors = new Color[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                colors[i][j] = new Color(img.getRGB(j, i));
            }
        }
        return colors;
    }

    /**
     * Get all the colors of a BufferedImage, it gets the Color of each pixel.
     * @param img The BufferedImage.
     * @return An 2 dimensional array of Color of each pixel of the BufferedImage.
     */
    public static Color[][] getImgColors(Image img){
        BufferedImage buffImg = toBufferedImage(img);
        int m = buffImg.getHeight();
        int n = buffImg.getWidth();
        Color[][] colors = new Color[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                colors[i][j] = new Color(buffImg.getRGB(j, i));
            }
        }
        return colors;
    }

    /**
     * Transform a Image to a BufferedImage.
     * @param img The Image.
     * @return A BufferedImage based in the Image.
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage){
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    /**
     * Transforms a Color to a char, it's based in the brightness of the color.
     * @param color The color.
     * @return The char based in the brightness of the color.
     */
    public static char colorToChar(Color color){
        float[] hsbValue = Color.RGBtoHSB(color.getRed(), 
            color.getGreen(), color.getBlue(), null);
        float b = hsbValue[2];
        if(b == 0.0){
            return ZERO;
        } else if(b > 0.0 && b <= 0.1){
            return UM;
        } else if(b > 0.1 && b <= 0.2){
            return DOIS;
        } else if(b > 0.2 && b <= 0.3){
            return TRES;
        } else if(b > 0.3 && b <= 0.4){
            return QUATRO;
        } else if(b > 0.4 && b <= 0.5){
            return CINCO;
        } else if(b > 0.5 && b <= 0.6){
            return SEIS;
        } else if(b > 0.6 && b <= 0.7){
            return SETE;
        } else if(b > 0.7 && b <= 0.8){
            return OITO;
        } else if(b > 0.8 && b <= 0.9){
            return NOVE;
        } else if(b > 0.9 && b <= 1.0){
            return DEZ;
        }
        try {
            throw new Exception("BRIGHTNESS NOT IDENTIFIED");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(-1);
        return 0;
    }

}
