package Negy.Module.impl.movement;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;

public class LongJump extends Module {
    public LongJump() {
        super("LongJump", 0, Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            if(ThePlayer.onGround() == false){
                ThePlayer.Strafe(1.2f);
            }
        }
    }
}
