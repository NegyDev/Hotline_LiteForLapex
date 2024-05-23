package Negy.Fonts;

import Negy.Utils.FontUtil;
import Negy.Utils.MinecraftFontRenderer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    public String negy = "Gönül İsterdiki Farklı Bir Font Kullanalım Ama Arial'i Sevdim :( ";
    public static void InitFonts() {
        Map<String, Font> locationMap = new HashMap<>();
        FontUtil.font15 = new MinecraftFontRenderer(FontUtil.getFont(locationMap, "arial.ttf", 15), true, true);
        FontUtil.font20 = new MinecraftFontRenderer(FontUtil.getFont(locationMap, "arial.ttf", 20), true, true);
        FontUtil.font25 = new MinecraftFontRenderer(FontUtil.getFont(locationMap, "arial.ttf", 25), true, true);
        FontUtil.font30 = new MinecraftFontRenderer(FontUtil.getFont(locationMap, "arial.ttf", 30), true, true);
        FontUtil.font40 = new MinecraftFontRenderer(FontUtil.getFont(locationMap, "arial.ttf", 40), true, true);

    }
}
