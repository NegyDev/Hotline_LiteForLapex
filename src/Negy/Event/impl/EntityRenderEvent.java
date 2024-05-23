package Negy.Event.impl;

import Negy.Event.Event;
import net.minecraft.BM;
import net.minecraft.client.d;

public class EntityRenderEvent extends Event {
    public final d<BM> renderer;
    public final BM entity;
    public final double x, y, z;
    public final float partialTicks;

    public EntityRenderEvent(d<BM> renderer, BM entity, double x, double y, double z, float partialTicks) {
        this.renderer = renderer;
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.partialTicks = partialTicks;
    }
}
