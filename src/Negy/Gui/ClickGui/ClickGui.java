package Negy.Gui.ClickGui;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import Negy.Client;
import Negy.Mappings.Minecraft;
import Negy.Module.Settings.InputSetting;
import Negy.Module.Settings.Setting;
import Negy.Module.Settings.SliderSetting;
import Negy.Module.Settings.SquareSwitchSetting;
import Negy.Utils.FontUtil;
import Negy.Utils.MinecraftFontRenderer;
import net.minecraft.client.Ym;
import net.minecraft.client.Yx;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Negy.Fonts.FontManager;
import Negy.Module.Category;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import net.minecraft.client.aT;
import org.lwjgl.opengl.GL11;

public class ClickGui extends Ym {

    private static final int GUI_WIDTH = 530;
    private static final int GUI_HEIGHT = 400;
    private Category selectedCategory = Category.COMBAT;
    private HashMap<Module, Integer> moduleRectSize = new HashMap<>();
    private boolean isDraggingSlider = false;
    private SliderSetting draggedSlider = null;
    public int mouseXX,mouseYY;
    private int mouseXOffset = 0;
    private boolean isHovered = false;
    public ClickGui() {
        for(Module m : ModuleManager.modules) {
            moduleRectSize.put(m, 30);
        }
    }

    @Override
    public void e(int mouseX, int mouseY, float partialTicks) {
        int guiLeft = (i - GUI_WIDTH) / 2;
        int guiTop = (a - GUI_HEIGHT) / 2;
        mouseXX = mouseX;
        mouseYY = mouseY;
        F(guiLeft, guiTop, guiLeft + GUI_WIDTH, guiTop + GUI_HEIGHT, Color.WHITE.getRGB());
        drawVerticalLine(guiLeft + 140, guiTop, guiTop + GUI_HEIGHT, Color.BLACK.getRGB());
        drawWatermark(guiLeft +20,guiTop);
        drawUyelikBackground(guiLeft,guiTop);
        drawUyelikBilgileri(guiLeft, guiTop);
        drawCategorys(guiLeft -10,guiTop +20);
        drawModules(guiLeft,guiTop);
        drawModuleSettings(mouseX,mouseY);
        ResizeDisable();
        super.e(mouseX, mouseY, partialTicks);
    }
    public void drawModuleSettings(int mouseX, int mouseY) {
        for(Module module : ModuleManager.getModulesByCategory(selectedCategory)) {
            if(module.isSizeChanged()) {
                drawModuleSettings(module,(moduleRectSize.get(module)- module.getNewSize())+10,mouseX,mouseY);
            }
        }
    }
    public void drawModules(int guiLeft, int guiTop) {
        int rectTop = guiTop + 10;
        int rectWidth = 375;
        int rectHeight = 30;

        for (Module m : ModuleManager.getModulesByCategory(selectedCategory)) {
            int rectLeft = guiLeft + 150;
            int rectRight = rectLeft + rectWidth;
            int rectBottom = rectTop + moduleRectSize.get(m);
            Color rectColor = new Color(220, 220, 220);
            Yx.F(rectLeft, rectTop, rectRight, rectBottom, rectColor.getRGB());
            int textX = rectLeft + 5;
            int textY = rectTop + 10;
            FontUtil.font25.drawString(m.getName(), textX, textY, Color.BLACK.getRGB());
            int arrowSize = 10;
            int arrowX = rectRight - arrowSize - 5;
            int arrowY = rectTop + (30 - arrowSize) / 2;
            drawDownwardArrow(arrowX, arrowY, arrowSize,m);

            rectTop += moduleRectSize.get(m) + 10;
        }
    }


    private void drawDownwardArrow(int x, int y, int size, Module m) {
        int color = m.isEnabled() ? Color.GREEN.getRGB() : Color.WHITE.getRGB();
        Yx.F(x, y, x + size, y + size, color);
        Yx.F(x + size / 4, y + size / 4, x + size - size / 4, y + size - size / 4, Color.WHITE.getRGB());
        Yx.F(x + size / 2 - 1, y + size - size / 4, x + size / 2 + 1, y + size - 2, Color.WHITE.getRGB());
        int boxX = x + size / 4;
        int boxY = y + size / 4;
        int boxWidth = size / 2;
        int boxHeight = size / 2;
        boolean isMouseOverBox = mouseXX >= boxX && mouseXX <= boxX + boxWidth && mouseYY >= boxY && mouseYY <= boxY + boxHeight;

        if (isMouseOverBox && Mouse.isButtonDown(0)) {
            if (!m.wasMouseClicked) {
                m.toggle();
                m.wasMouseClicked = true;
            }
        } else {
            m.wasMouseClicked = false;
        }
    }




    public void drawCategorys(int guiLeft, int guiTop) {
        int buttonWidth = 140;
        int buttonHeight = 30;
        int spacing = 5;
        int startY = guiTop + 30;
        MinecraftFontRenderer fontUtil = FontUtil.font25;

        for (Category category : Category.values()) {
            int startX = guiLeft + 10;
            Color buttonColor = (category == selectedCategory) ? new Color(37, 150, 190) : Color.WHITE;

            Yx.F(startX, startY, startX + buttonWidth, startY + buttonHeight, Color.BLACK.getRGB());
            Yx.F(startX + 1, startY + 1, startX + buttonWidth - 1, startY + buttonHeight - 1, buttonColor.getRGB());
            int textWidth = (int) fontUtil.getStringWidth(category.name());
            int textHeight = fontUtil.getHeight();
            int textX = startX + (buttonWidth - textWidth) / 2;
            int textY = startY + (buttonHeight - textHeight) / 2;
            fontUtil.drawString(category.name(), textX, textY, Color.BLACK.getRGB());

            startY += buttonHeight + spacing;
        }
    }


    @Override
    protected void k(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.k(mouseX, mouseY, mouseButton);

        int guiLeft = (i - GUI_WIDTH) / 2;
        int guiTop = (a - GUI_HEIGHT) / 2;

        boolean clickedInsideCategory = false;
        for (Category category : Category.values()) {
            int startX = guiLeft + 10;
            int startY = guiTop + 30 + (category.ordinal() * 35);
            if (mouseX >= startX && mouseX <= startX + 140 && mouseY >= startY && mouseY <= startY + 50) {
                selectedCategory = category;
                clickedInsideCategory = true;
                break;
            }
        }

        int rectLeft = guiLeft + 150;
        int rectRight = guiLeft + GUI_WIDTH - 10;
        int rectTop = guiTop + 10;
        for (Module m : ModuleManager.getModulesByCategory(selectedCategory)) {
            if (mouseX >= rectLeft && mouseX <= rectRight && mouseY >= rectTop && mouseY <= rectTop + moduleRectSize.get(m) +10) {
                if(!m.GetSettingIsEmpty()){
                    if (mouseButton == 1) {
                        if (!m.isSizeChanged()) {
                            m.setSizeChanged(true);
                            updateRectSize(m, rectTop + moduleRectSize.get(m) + m.getNewSize() );
                        } else {
                            m.setSizeChanged(false);
                            updateRectSize(m, 30);
                        }
                    }
                }
            }
            rectTop += moduleRectSize.get(m)+m.getNewSize();
        }
    }

    public void drawModuleSettings(Module m,int Y,int mouseX, int mouseY) {
        int SettingsY = Y;
        for(Setting setting: m.settings) {
            if(setting instanceof SliderSetting) {
                SliderSetting settings = (SliderSetting)setting;
                Sliderdraw(settings,SettingsY);
            }
            if(setting instanceof SquareSwitchSetting) {
                SquareSwitchSetting settings = (SquareSwitchSetting)setting;
                Switchdraw(settings,SettingsY);
            }
            if(setting instanceof InputSetting) {
                InputSetting settings = (InputSetting)setting;
                Inputdraw(settings,SettingsY,mouseX,mouseY);
            }
            SettingsY+=25;
        }
    }
    public void Inputdraw(InputSetting inputsetting, int y, int mouseX, int mouseY) {
        int x = 375;
        int width = 40;
        int height = 20;
        String text = "Key:";
        String settingName = inputsetting.getName();
        int textWidth = (int) FontUtil.font20.getStringWidth(text);
        int nameWidth = (int) FontUtil.font20.getStringWidth(settingName);
        int totalWidth = width + textWidth + nameWidth + 30;
        int rectX = x + (totalWidth - width) / 2;
        int rectY = y;

        boolean isHovered = mouseX >= rectX && mouseX <= rectX + width && mouseY >= rectY && mouseY <= rectY + height;
        Yx.F(rectX, rectY, rectX + width, rectY + height, isHovered ? Color.LIGHT_GRAY.getRGB() : Color.WHITE.getRGB());
        boolean clickedInside = mouseX >= rectX && mouseX <= rectX + width && mouseY >= rectY && mouseY <= rectY + height;
        if (clickedInside) {
            FontUtil.font20.drawString("...", rectX + 10, rectY + 5, Color.GRAY.getRGB());
            FontUtil.font20.drawString(text, x + 5, y + 5, Color.BLACK.getRGB());
            if (Keyboard.getEventCharacter() != Keyboard.CHAR_NONE) {
                inputsetting.setName(String.valueOf(Keyboard.getEventCharacter()).toUpperCase());
                FontUtil.font20.drawString(text, x + 5, y + 5, Color.BLACK.getRGB());

            }
        } else {
            FontUtil.font20.drawString(settingName, x + textWidth + 24, y + 6, isHovered ? Color.BLUE.getRGB() : Color.CYAN.getRGB());
            FontUtil.font20.drawString(text, x + 5, y + 5, Color.BLACK.getRGB());
        }
    }

    public int countResizedModules() {
        int count = 0;
        for (Module m : ModuleManager.modules) {
            if (m.isSizeChanged()) {
                count++;
            }
        }
        return count;
    }
    public void ResizeDisable() {
        int count = countResizedModules();
        if(count >=2) {
            for (Module m : ModuleManager.modules) {
                m.setSizeChanged(false);
                updateRectSize(m, 30);
            }
        }
    }


    public void Switchdraw(SquareSwitchSetting switchSetting, int y) {
        boolean toggled = switchSetting.getValue();
        int switchWidth = 15;
        int switchHeight = 15;
        int x = 375;

        Yx.F(x, y, x + switchWidth, y + switchHeight, Color.GRAY.getRGB());
        Color switchColor = toggled ? Color.GREEN : Color.LIGHT_GRAY;
        Yx.F(x + 2, y + 2, x + switchWidth - 2, y + switchHeight - 2, switchColor.getRGB());
        FontUtil.font20.drawString(switchSetting.getName(), x, y - 10, Color.BLACK.getRGB());

        boolean isMouseOverSwitch = mouseXX >= x && mouseXX <= x + switchWidth && mouseYY >= y && mouseYY <= y + switchHeight;

        if (isMouseOverSwitch && Mouse.isButtonDown(0)) {
            if (!switchSetting.Clicked) {
                switchSetting.toggle();
                switchSetting.Clicked = true;
            }
        } else {
            switchSetting.Clicked = false;
        }
    }


    public void Sliderdraw(SliderSetting sliderSetting, int Y) {
        int sliderX = 375;
        int sliderY = Y;
        int sliderWidth = 250;
        int sliderHeight = 2;

        int minValue = (int) sliderSetting.getMinValue();
        int maxValue = (int) sliderSetting.getMaxValue();
        int defaultValue = (int) sliderSetting.getValue();
        int stepSize = (int) sliderSetting.getStepSize();

        FontUtil.font20.drawString(sliderSetting.getName(), sliderX, Y - 13, Color.WHITE.getRGB());

        float normalizedValue = (defaultValue - minValue) / (float) (maxValue - minValue);
        int filledWidth = (int) (sliderWidth * normalizedValue);

        Yx.F(sliderX, sliderY, sliderX + sliderWidth, sliderY + sliderHeight, Color.GRAY.getRGB());
        Yx.F(sliderX, sliderY, sliderX + filledWidth, sliderY + sliderHeight, Color.GREEN.getRGB());

        FontUtil.font15.drawString(String.valueOf(defaultValue), sliderX + sliderWidth + 15, Y - 1, Color.BLACK.getRGB());

        boolean isMouseOver = mouseXX >= sliderX && mouseXX <= sliderX + sliderWidth &&
                mouseYY >= sliderY && mouseYY <= sliderY + sliderHeight;

        if (isMouseOver && Mouse.isButtonDown(0)) {
            float newValue = minValue + (mouseXX - sliderX) * (maxValue - minValue) / sliderWidth;
            sliderSetting.setValue(newValue);
        }
    }





    private void updateRectSize(Module module, int newRectBottom) {
        if (moduleRectSize.containsKey(module)) {
            moduleRectSize.replace(module, newRectBottom);
        } else {
            moduleRectSize.put(module, newRectBottom);
        }
    }





    public void drawUyelikBackground(int guiLeft,int guiTop) {
        int rectLeft = guiLeft;
        int rectTop = guiTop + GUI_HEIGHT;
        int rectRight = guiLeft +140;
        int rectBottom = guiTop + GUI_HEIGHT - 40;
        Color bgcolor = new Color(240, 236, 236);
        Yx.F(rectLeft, rectTop, rectRight, rectBottom, bgcolor.getRGB());
    }
    public void drawWatermark(int guiLeft, int guiTop) {
        String watermarkText = "Hotline Lite";
        int textWidth = (int) FontUtil.font40.getStringWidth(watermarkText);
        int textHeight = FontUtil.font40.getHeight();
        int rectLeft = guiLeft -20;
        int rectTop = guiTop;
        int rectRight = rectLeft + textWidth + 38;
        int rectBottom = rectTop + textHeight + 15;

        Color bgcolor = new Color(240, 236, 236);
        Yx.F(rectLeft, rectTop, rectRight, rectBottom, bgcolor.getRGB());

        FontUtil.font40.drawString("Hotline Lite", guiLeft, guiTop + 10, Color.BLACK.getRGB());
    }


    public void drawUyelikBilgileri(int guiLeft, int guiTop) {
        int circleCenterX = guiLeft + 25;
        int circleCenterY = guiTop + GUI_HEIGHT - 25;
        int circleRadius = 15;
        FontUtil.font15.drawString("Username: .gg/hotlinelite", guiLeft + 40, guiTop + GUI_HEIGHT - 25 - FontUtil.font15.getHeight(), Color.BLACK.getRGB());
        drawMinecraftSkin(circleCenterX - circleRadius +5, circleCenterY - circleRadius +5, circleRadius + 5);
        FontUtil.font15.drawString("Version: Free", guiLeft + 40, guiTop + GUI_HEIGHT - 25 - FontUtil.font15.getHeight()+8, Color.BLACK.getRGB());
    }

    private void drawMinecraftSkin(int x, int y, int size) {
        aT player = Minecraft.getPlayer();
        if (player != null) {
            Minecraft.getMinecraft().L().O(player.E());
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Yx.l(x, y, 8.0F, 8.0F, 8, 8, size, size, 64.0F, 64.0F);
        }
    }
    public void drawVerticalLine(int x, int startY, int endY, int color) {
        Yx.F(x, startY, x + 1, endY, color);
    }


}
