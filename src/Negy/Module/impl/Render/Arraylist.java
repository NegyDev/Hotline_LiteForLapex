package Negy.Module.impl.Render;

import Negy.Client;
import Negy.Event.Event;
import Negy.Event.impl.Render2DEvent;
import Negy.Mappings.FontRenderer;
import Negy.Mappings.Minecraft;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.FontUtil;
import Negy.Utils.MinecraftFontRenderer;
import net.minecraft.client.Ex;
import net.minecraft.client.Yy;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;

public class Arraylist {
    public Arraylist() {

    }

    public static void renderArrayList() {
        Yy sr = new Yy(Minecraft.getMinecraft());
        MinecraftFontRenderer fontRenderer = FontUtil.font25;

        int yOffset = 4;
        int moduleCount = 0;

        for (Negy.Module.Module module : ModuleManager.modules) {
            if (module.toggled) {
                int color = rainbow(600, 20, moduleCount);
                fontRenderer.drawString(module.name, getScaledWidth(sr) - fontRenderer.getStringWidth(module.name) - 4, yOffset, color);
                yOffset += fontRenderer.getHeight() +3;
                moduleCount++;
            }
        }
    }

    public static int rainbow(int delay, int offset, int index) {
        double rainbowDelay = Math.ceil(System.currentTimeMillis() + (long)((long) delay * index)) / offset;
        return Color.getHSBColor((float)(rainbowDelay % 360.0 / 360.0), 0.5F, 1.0F).getRGB();
    }

    public static int getScaledWidth(Object resulation){
        try{
            Field field = Yy.class.getDeclaredField("v");
            field.setAccessible(true);
            return (int) field.get(resulation);
        }catch (Exception e){
            e.printStackTrace();
            return (int)0;
        }
    }
}
