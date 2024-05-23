package Negy.Module.impl.movement;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Module.Category;
import Negy.Module.Module;

public class Step extends Module {
    public Step() {
        super("Step", 0, Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            Minecraft.getPlayer().FZ = 13.0f;
        }
    }

    @Override
    public void onDisable() {
        Minecraft.getPlayer().FZ = 0.6f;
    }
}
