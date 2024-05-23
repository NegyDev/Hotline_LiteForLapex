package Negy.Module.impl.movement;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Utils.Timer;
import net.minecraft.b1;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public Timer timer = new Timer();
    public Fly() {
        super("Fly", 0, Category.MOVEMENT);
        this.description = "Uçmanızı Sağlar";
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
           ThePlayer.setOnGround(true);
           ThePlayer.setMotionY(0);
           ThePlayer.Strafe(1.0f);
        }
    }

    @Override
    public void onDisable() {

    }
}
