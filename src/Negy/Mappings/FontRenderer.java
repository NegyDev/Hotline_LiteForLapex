package Negy.Mappings;

import net.minecraft.client.M3;

public class FontRenderer {
    public int Font_height = 9;
    static M3 FontRenderer = Minecraft.getMinecraft().R;

    public void drawString(String text,int x,int y,int color){
        FontRenderer.M(text, x, y, color);
    }
    public void drawStringWithShadow(String text,float x,float y,int color){
        FontRenderer.r(text,x,y,color);
    }
    public void drawString(String text,float x,float y,int color,boolean shadow){
        FontRenderer.P(text, x, y, color, shadow);
    }
}
