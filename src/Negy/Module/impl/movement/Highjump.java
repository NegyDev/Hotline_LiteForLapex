package Negy.Module.impl.movement;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import org.lwjgl.input.Keyboard;

public class Highjump extends Module {
    public Highjump() {
        super("HighJump", 0, Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                ThePlayer.setMotionY(1.0);
            }
        }
    }
}
