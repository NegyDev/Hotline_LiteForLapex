package Negy.Utils;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontUtil
{
    public static volatile int completed;
    public final static String
            BUG = "a",
            LIST = "b",
            BOMB = "c",
            EYE = "d",
            PERSON = "e",
            WHEELCHAIR = "f",
            SCRIPT = "g",
            SKIP_LEFT = "h",
            PAUSE = "i",
            PLAY = "j",
            SKIP_RIGHT = "k",
            SHUFFLE = "l",
            INFO = "m",
            SETTINGS = "n",
            CHECKMARK = "o",
            XMARK = "p",
            TRASH = "q",
            WARNING = "r",
            FOLDER = "s",
            LOAD = "t",
            SAVE = "u";

    public static MinecraftFontRenderer font15,font20,font25,font30,font40,icons;
    public static Font tenacityBoldFont40_;

    public FontUtil()
    {
    }
    public static final String FONT_DIRECTORY = "C://Windows//Fonts//";

    public static Font getFont(Map<String, Font> locationMap, String location, int size)
    {
        Font font;

        try
        {
            if (locationMap.containsKey(location))
            {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            }
            else
            {
                String fontPath = FONT_DIRECTORY + location;
                File fontFile = new File(fontPath);
                InputStream is = new FileInputStream(fontFile);
                locationMap.put(location, font = Font.createFont(0, is));
                font = font.deriveFont(Font.PLAIN, size);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, +10);
        }

        return font;
    }
    public static Font getAWTFont(String directory, int size) {
        try {
            File fontFile = new File(directory);
            Font loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) size);
            return loadedFont;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasLoaded()
    {
        return completed >= 3;
    }
}
