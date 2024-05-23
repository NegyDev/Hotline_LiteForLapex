package Negy.Module.impl.movement;

import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Category;
import Negy.Module.Module;
import org.lwjgl.input.Keyboard;

public class Clip extends Module {
    public Clip() {
        super("Clip", 0, Category.MOVEMENT);
        this.description = "5 block İleri Işınlanırsınız";
    }
    public static double getPosForSetPosX(double val, float RotationYaw) {
        float yaw = RotationYaw * ((float)Math.PI / 180);
        double x = -Math.sin(yaw) * val;
        return x;
    }

    public static double getPosForSetPosZ(double val, float RotationYaw) {
        float yaw = RotationYaw * ((float)Math.PI / 180);
        double z = Math.cos(yaw) * val;
        return z;
    }

    @Override
    public void onEnable() {
        double newposX = Minecraft.getMinecraft().Y.Fy + Clip.getPosForSetPosX(6, ThePlayer.getRotationYaw());
        double newposz = Minecraft.getMinecraft().Y.F4 + Clip.getPosForSetPosZ(6, ThePlayer.getRotationYaw());
        double newposY = Minecraft.getMinecraft().Y.L+ 1.0;
        Minecraft.getMinecraft().Y.J(newposX,newposY,newposz);
        this.setEnabled(false);
    }
}
