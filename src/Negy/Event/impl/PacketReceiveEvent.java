package Negy.Event.impl;
import Negy.Event.Event;
import net.minecraft.wj;
public class PacketReceiveEvent extends Event {
    private wj<?> packet;

    public PacketReceiveEvent(wj<?> packet)
    {
        this.packet = packet;
    }

    public wj<?> getPacket()
    {
        return packet;
    }

}
