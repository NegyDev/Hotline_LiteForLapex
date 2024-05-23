package Negy.Module.impl.combat;

import Negy.Event.Event;
import Negy.Event.impl.MotionEvent;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Module.Settings.SliderSetting;
import Negy.Module.Settings.SquareSwitchSetting;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

public class KillAura extends Module {
    private BM target;
    private long lastAttackTime = 0L;
    private long attackCooldown = 100;
    private int delay = 8;
    public SliderSetting range = new Negy.Module.Settings.SliderSetting("Range",1,8,1,1);
    public SliderSetting CPS = new Negy.Module.Settings.SliderSetting("CPS",1,12,1,1);



    public KillAura() {
        super("KillAura", 0, Category.COMBAT);
        this.description = "Oyunculara Belirtilen mesafeden Otomatik Saldırır";
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof MotionEvent){
            for(B5 entity: Minecraft.getWorld().M){
                if(entity == Minecraft.getPlayer())continue;
                if(entity==null)continue;
                if(entity instanceof net.minecraft.OI){
                    float distance = Minecraft.getPlayer().k(entity);
                    if(distance <= 3.6f){
                        if(ThePlayer.onGround()){
                            Attack(entity);
                        }
                    }
                }
            }
        }
    }
    public static synchronized void faceEntity(B5 entity) {
        final float[] rotations = getRotationsNeeded(entity);
        ThePlayer.sendPacket(new bY(rotations[0],rotations[1],true));
    }
    public static float[] getRotationsNeeded(B5 entity) {
        double deltaX = entity.Fy -ThePlayer.getPosX();
        double deltaY = entity.L - ThePlayer.getPosY();
        double deltaZ = entity.F4 - ThePlayer.getPosZ();

        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        float yaw = (float) Math.toDegrees(-Math.atan2(deltaX, deltaZ));
        float pitch = (float) Math.toDegrees(-Math.atan2(deltaY, distance));

        return new float[]{yaw, pitch};
    }
    public void Attack(B5 Entity){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime >= attackCooldown) {
            float distance = Minecraft.getPlayer().k(Entity);
            if(distance <= 3.6f){
                faceEntity(Entity);
                Minecraft.getMinecraft().o.R(Minecraft.getPlayer(),Entity);
                Minecraft.getPlayer().Kd.O(new hv());
            }
            lastAttackTime = currentTime;
        }
    }
    public void Swing(){
    }
}
