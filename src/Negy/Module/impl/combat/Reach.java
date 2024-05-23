package Negy.Module.impl.combat;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.InvocationTargetException;

public class Reach extends Module {
    public Reach() {
        super("Reach", 0, Category.COMBAT);
    }

    @Override
    public void onEvent(Event event) {

    }
}
