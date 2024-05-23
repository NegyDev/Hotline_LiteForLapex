package Negy.Module.impl.combat;

import Negy.Event.Event;
import Negy.Event.impl.PacketReceiveEvent;
import Negy.Module.Category;
import Negy.Module.Module;
import net.minecraft._a;
import org.lwjgl.input.Keyboard;
import net.minecraft.yz;

import java.lang.reflect.InvocationTargetException;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", 0, Category.COMBAT);
        this.description = "Hasar Alındığında Asla Sekmezsiniz";
    }
    @Override
    public void onEvent(Event event) {
        if (event instanceof PacketReceiveEvent) {
            PacketReceiveEvent e = (PacketReceiveEvent)event;
            if(e.getPacket() instanceof _a){
                e.setCancelled(true);
            }
        }
    }
}
