package Negy.Module.Settings;

import java.awt.Color;
import java.io.IOException;

public class SliderSetting extends Setting{
    private String name;
    private double minValue;
    private double maxValue;
    private double value;
    private double stepSize;
    public boolean isDragging;
    public int dragStartX;

    public SliderSetting(String name, double minValue, double maxValue, double defaultValue, double stepSize) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = defaultValue;
        this.stepSize = stepSize;
    }

    public String getName() {
        return name;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = Math.max(minValue, Math.min(maxValue, value));
    }

    public double getStepSize() {
        return stepSize;
    }
    
    
}
