package Negy.Module.impl.movement;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import org.lwjgl.input.Keyboard;

import static Negy.Mappings.ThePlayer.Strafe;

public class Speed extends Module {
    private boolean zipladikxd = false;
    public Speed() {
        super("Speed", 0, Category.MOVEMENT);
        this.description = "Oyun İçinde Hızlı Koşarsınız";
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            if(ThePlayer.onGround()){
                Strafe(0.9f);
            }

        }
    }
}
