/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package Negy.Module;

import Negy.Client;
import Negy.Event.Event;
import Negy.Mappings.Minecraft;
import Negy.Mappings.ThePlayer;
import Negy.Module.Settings.Setting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module {
    private static boolean keyPressed = false;
    public String name;
    public String Mode;
    public boolean toggled;
    public int KeyCode;
    public Category category;
    public String description;
    private long lastUpdateTime;
    private double delay;
    public boolean wasMouseClicked;
    public List<Setting> settings = new ArrayList();
    private boolean sizeChanged = false;

    public Module(String name, int key, Category c) {
        this.name = name;
        this.KeyCode = key;
        this.category = c;
    }
    public int getNewSize() {
        int totalSettings = 0;
        for(Setting setting :this.settings){
            totalSettings += this.getSettings().size() +10;
        }
        return totalSettings;
    }

    public void AddSettings(Setting... setting) {
        settings.addAll(Arrays.asList(setting));
    }
    public boolean GetSettingIsEmpty(){
        return this.settings.isEmpty();
    }
    public List<Setting> getSettings() {
        return settings;
    }
    public boolean isSizeChanged() {
        return sizeChanged;
    }

    public void setSizeChanged(boolean sizeChanged) {
        this.sizeChanged = sizeChanged;
    }

    public boolean isEnabled() {
        return this.toggled;
    }

    public String getName() {
        return this.name;
    }

    public void setMode(String NewMode) {
        this.Mode = NewMode;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public void setEnabled(boolean value) {
        this.toggled = value;
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public int getKey() {
        return this.KeyCode;
    }
    public void setKey(int Key) {
        this.KeyCode = Key;
    }
    public void setKey(String Key) {
        this.KeyCode = Keyboard.getKeyIndex(Key);
    }


    public static void onUpdateEvent() {
        for (Module m : ModuleManager.modules) {
            if (!m.toggled) continue;
            m.onUpdate();
        }
    }

    public static void onEventAdd(Event e) {
        for (Module m : ModuleManager.modules) {
            if (!m.toggled) continue;
            m.onEvent(e);
        }
    }

    public static void KeyPress(int key) {
        for (Module m : ModuleManager.modules) {
            System.out.println(String.valueOf(m.getKey()) + " : "+key);
            if (m.getKey() != key) continue;
            if(m.getKey() == 0)continue;
            m.toggle();
        }
    }

    public void onkeyPress(int key) {

    }

    public static void KeyCheckEvent() {
        if(Minecraft.getMinecraft().V instanceof net.minecraft.client.YP)return;
        if (Keyboard.getEventKeyState()) {
            int k = Keyboard.getEventKey();
            if (!keyPressed) {
                Module.KeyPress(k);
                keyPressed = true;
            }
        } else {
            keyPressed = false;
        }
    }

    public static void motionUpdater(Event e) {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onDelayedUpdate() {
    }

    public void onEvent(Event event) {
    }
}

