package Negy.Module.impl.player;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import net.minecraft.bc;

public class Nofall extends Module {
    public Nofall() {
        super("NoFall", 0, Category.PLAYER);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            if(Minecraft.getPlayer().t >1){
                ThePlayer.sendPacket(new bc(true));
            }
        }
    }
}
