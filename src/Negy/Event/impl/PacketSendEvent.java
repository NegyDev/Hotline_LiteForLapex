package Negy.Event.impl;

import Negy.Event.Event;
import net.minecraft.wj;

public class PacketSendEvent extends Event {
    private wj<?> packet;

    public PacketSendEvent(wj<?> packet)
    {
        this.packet = packet;
    }

    public wj<?> getPacket()
    {
        return packet;
    }
}
