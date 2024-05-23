package Negy;

import Negy.Command.CommandManager;
import Negy.Config.System.ConfigManager;
import Negy.Config.System.Configs.BindingsConfig;
import Negy.Fonts.FontManager;
import Negy.Mappings.Minecraft;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import com.google.gson.JsonObject;
import sonoyuncu.net.arikia.dev.drpc.DiscordRPC;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
    static String ClientName = "NegySpecial";
    static String HotlineMesaj = "Seviliyorsunuz Dostlar 410 Üye İçin Çok Ama Çok Teşekkür Ederim";
    static String Version = "2.3";
    static String Developer = "Negy";

    public static FontManager FontManager = new FontManager();
    public static void IngameGuiSetter(net.minecraft.client.YI gui) {
        Minecraft.getMinecraft().h0 = gui;
    }
    public static int i = 0;
    public static ConfigManager configManager = new ConfigManager();
    public static void Start() throws Exception {
        if(i<=0){
            i++;
            CommandManager.AddCommands();
            ModuleManager.AddAllModules();
            configManager.AddConfigs();
            configManager.createConfigsFolder();
            BindingsConfig Bindings = (BindingsConfig)configManager.getConfig("bindings");
            Bindings.LoadKeybinds(configManager.configsFolder);
            FontManager.InitFonts();
            IngameGuiSetter(new IngameGui(Minecraft.getMinecraft().h0));
        }else {
        }
    }


    public static void KeyPressThread() {
        while (true) {
            Module.KeyCheckEvent();
        }
    }

    public static void main(String[]args){

    }
}

