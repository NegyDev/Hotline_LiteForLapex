package Negy.Module.impl.Render;

import Negy.Event.Event;
import Negy.Event.impl.Render2DEvent;
import Negy.Mappings.Minecraft;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Utils.FontUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Hud {

    public static void HudDraw() {
        Hud.drawRainbowString("Hotline", 5, 5, 2L);
        FontUtil.font20.drawString("Lite", FontUtil.font30.getStringWidth("Hotline") + 5, 2, -1);
    }

    public static void drawRainbowString(String text, int x, int y, long time) {
        float hue = (float)(System.nanoTime() - time) / 1.0E10f % 1.0f;
        Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
        FontUtil.font30.drawString(text, x, y, color.getRGB());
    }
}
