package Negy.Module.impl.player;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import net.minecraft.B5;
import net.minecraft.BM;
import net.minecraft.OI;
import net.minecraft.bc;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Regen extends Module {
    private long lastAttackTime = 0L;
    private long attackCooldown = 360;
    public Regen() {
        super("Regen", Keyboard.KEY_M, Category.PLAYER);
    }
    @Override
    public void onEvent(Event event) {
        if (event instanceof MotionEvent) {
                Heal();
        }
    }
    public net.minecraft._K getFoodStats(B5 entity) {
        try {
            Field field = OI.class.getDeclaredField("sy");
            field.setAccessible(true);
            return (net.minecraft._K)field.get(entity);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public float getHealt(B5 entity) {
        try {
            Method method = BM.class.getDeclaredMethod("O",null);
            method.setAccessible(true);
            return (float)method.invoke(entity);
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void Heal(){
        if(ThePlayer.onGround()&&getFoodStats(Minecraft.getPlayer()).V() >18){
            for(int i = 0; i < 9; i++)
            {
               ThePlayer.sendPacket(new bc(ThePlayer.onGround()));
            }
        }
    }
}
