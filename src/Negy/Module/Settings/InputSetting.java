package Negy.Module.Settings;

import org.lwjgl.input.Keyboard;

public class InputSetting extends Setting{
    private String name;
    private int keyCode;

    public InputSetting(int KeyCode) {
    	this.name = Keyboard.getKeyName(KeyCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
