package Negy;

import Negy.Event.impl.Render2DEvent;
import Negy.Gui.ClickGui.ClickGui;
import Negy.Mappings.Minecraft;
import Negy.Module.Module;
import Negy.Module.impl.Render.Hud;
import Negy.Utils.FontUtil;
import net.minecraft.client.Yn;
import org.lwjgl.input.Keyboard;

import java.awt.*;

import static Negy.Module.impl.Render.Arraylist.renderArrayList;

public class IngameGui extends net.minecraft.client.YI{
    public IngameGui(net.minecraft.client.YI originalGui) {
        super(Minecraft.getMinecraft());
    }

    @Override
    public void u(float v) {
        super.u(v);
        Module.onEventAdd(new Render2DEvent());
        Hud.HudDraw();
        renderArrayList();
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            Minecraft.DisplayGuiScreen(new ClickGui());
        }
    }
}