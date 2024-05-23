package Negy.Module.impl.player;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Utils.ReflectUtils;
import net.minecraft.OI;
import net.minecraft.bc;

import java.lang.reflect.Method;

public class FastUse extends Module {
    public FastUse() {
        super("FastUse", 0, Category.PLAYER);
    }

    public boolean isUsingItem(Object Player){
        try {
            Method method = OI.class.getDeclaredMethod("v");
            method.setAccessible(true);
            return (boolean) method.invoke(Player,null);
        }catch (Exception e){
            e.printStackTrace();
            return (boolean) false;
        }
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            if(isUsingItem(Minecraft.getPlayer())){
                for (int i = 0;i<=10;i++){
                    ThePlayer.sendPacket(new bc(ThePlayer.onGround()));
                }
            }
        }
    }
}
