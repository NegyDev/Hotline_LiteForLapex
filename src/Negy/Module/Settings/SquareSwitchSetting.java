package Negy.Module.Settings;

public class SquareSwitchSetting extends Setting {
    private boolean toggled;
    public boolean Clicked;
    public String SettingName;

    public SquareSwitchSetting(String name, boolean toggled) {
    	this.SettingName = name;
        this.toggled = toggled;
    }

    public boolean getValue() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
    public String getName() {
    	return SettingName;
    }

    public void toggle() {
        toggled = !toggled;
    }
}

