package Negy.Config.System.Configs;

import Negy.Config.System.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import Negy.Module.Module;
import Negy.Module.ModuleManager;

public class BindingsConfig extends Config {
    private Map<String, Integer> keybinds = new HashMap<>();

    public BindingsConfig() {
        this.ConfigName = "bindings";
    }

    public void Save(String configFolder) {
        for (Module m : ModuleManager.modules) {
            keybinds.put(m.getName(), m.getKey());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(keybinds);

        try (FileWriter writer = new FileWriter(configFolder + File.separator + "bindings.json")) {
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void LoadKeybinds(String configFolder) {
        File configFile = new File(configFolder + File.separator + "bindings.json");
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<Map<String, Integer>>() {}.getType();
                Map<String, Integer> loadedKeybinds = gson.fromJson(reader, type);
                if (loadedKeybinds != null) {
                    keybinds.clear();
                    keybinds.putAll(loadedKeybinds);
                    applyKeybinds();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void applyKeybinds() {
        for (Module m : ModuleManager.modules) {
            Integer keyCode = keybinds.get(m.getName());
            if (keyCode != null) {
                m.setKey(keyCode);
            }
        }
    }
}
