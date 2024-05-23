package Negy.Module.impl.misc;

import Negy.Event.Event;
import Negy.Event.impl.PacketSendEvent;
import Negy.Module.Category;
import Negy.Module.Module;
import org.lwjgl.input.Keyboard;
import net.minecraft.bc;
import javax.swing.*;
import java.io.File;
import java.lang.reflect.Field;

public class Disabler extends Module {
    public Disabler() {
        super("Disabler", 0, Category.MISC);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof PacketSendEvent){
            PacketSendEvent e = (PacketSendEvent) event;
            if(e.getPacket() instanceof bc){
                bc packet = (bc)e.getPacket();
                setPacketOnGround(packet);
            }
        }
    }
    public void setPacketOnGround(bc Packet){
        try{
            Field field = bc.class.getDeclaredField("n");
            field.setAccessible(true);
            field.set(Packet,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
