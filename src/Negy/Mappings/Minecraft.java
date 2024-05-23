package Negy.Mappings;

import net.minecraft.Po;
import net.minecraft.client.Ex;
import net.minecraft.client.Ym;
import net.minecraft.client.Yn;
import net.minecraft.client.aJ;
import net.minecraft.rs;
import net.minecraft.yz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Minecraft {
    public static FontRenderer fontRendererobj = new FontRenderer();
    public static GlStateManager glStateManager = new GlStateManager();
    public static Yn getMinecraft(){
        try{
            Field TheMinecraft = Yn.class.getDeclaredField("L");
            TheMinecraft.setAccessible(true);
            return (Yn) TheMinecraft.get(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void DisplayGuiScreen(Ym GuiScreen) {
        try {
            Method displayGuiScreenMethod = yz.d(1523337144448772159L, 55732237178208L);
            displayGuiScreenMethod.invoke(Minecraft.getMinecraft(), yz.a(GuiScreen));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static aJ getPlayer(){
        return getMinecraft().Y;
    }
    public static rs getWorld(){
        return getMinecraft().P;
    }
}
