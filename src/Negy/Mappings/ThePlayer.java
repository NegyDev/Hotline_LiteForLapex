package Negy.Mappings;

import net.minecraft.Zc;
import net.minecraft.client.Ex;
import net.minecraft.wj;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import net.minecraft.rs;
import net.minecraft.yz;

import javax.swing.*;

public class ThePlayer {
    public static void addChatMessage(String Message){
        String newMessage = "§7[§d§lHotline§r§7] " + Message;
        Minecraft.getPlayer().K(new Zc(newMessage));
    }
    public static double getMotionX(){
        return Minecraft.getPlayer().FF;
    }
    public static double getMotionY(){
        return Minecraft.getPlayer().E();
    }
    public static double getMotionZ(){
        return Minecraft.getPlayer().J;
    }
    public static double getPosX(){return Minecraft.getPlayer().Fy;}
    public static double getPosY(){return Minecraft.getPlayer().L;}
    public static double getPosZ(){return Minecraft.getPlayer().F4;}
    public static void setMotionY(double motionY){
        Minecraft.getPlayer().J(motionY);
    }
    public static boolean onGround(){
        return Minecraft.getPlayer().g();
    }
    public static void setOnGround(boolean value){
         Minecraft.getPlayer().Fj = value;
    }

    public static void sendPacket(wj packet){
        Minecraft.getPlayer().Kd.O(packet);
    }
    public static double Direction(){
        float rotationYaw = getRotationYaw();
        if (Minecraft.getPlayer().K1.k < 0f) rotationYaw += 180f;
        float forward = 1f;
        if (Minecraft.getPlayer().K1.k < 0f) forward = -0.5f; else if (Minecraft.getPlayer().K1.k > 0f) forward = 0.5f;
        if (Minecraft.getPlayer().K1.Y > 0f) rotationYaw -= 90f * forward;
        if (Minecraft.getPlayer().K1.Y < 0f) rotationYaw += 90f * forward;
        return Math.toRadians(rotationYaw);
    }
    public static boolean isMoving(){
        return Minecraft.getPlayer() != null && (Minecraft.getPlayer().K1.k != 0f || Minecraft.getPlayer().K1.Y != 0f);
    }
    public static void jump(){
        Minecraft.getPlayer().i();
    }
    public static float getRotationYaw(){
        return Minecraft.getPlayer().O;
    }
    public static void Strafe(float speed){
        if (!isMoving()) return;
        setMotionX(-sin(Direction()) * speed);;
        setMotionZ(cos(Direction()) * speed);
    }
    public static void setReach(){
        long var2_2 = 36173608526566L;
        try {
           Method v8 = yz.d(2505465765626672921L, var2_2);
            JOptionPane.showMessageDialog(null,v8.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setMotionX(double motionX){
        Minecraft.getPlayer().FF = motionX;
    }
    public static void setMotionZ(double motionZ){
        Minecraft.getPlayer().J = motionZ;
    }
}
