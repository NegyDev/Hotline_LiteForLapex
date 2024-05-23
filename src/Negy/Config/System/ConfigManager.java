package Negy.Config.System;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import Negy.Config.System.Configs.BindingsConfig;

public class ConfigManager {
	 public static CopyOnWriteArrayList<Config> configs = new CopyOnWriteArrayList<>();
	    public String configsFolder = System.getProperty("user.dir") + File.separator + "Hotline_Lite" + File.separator + "Configs";
	    
	    public void AddConfigs() {
	    	configs.add(new BindingsConfig());
		}
		

	    public void createConfigsFolder() {
	        File folder = new File(configsFolder);
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }

	        for (Config config : configs) {
	            File configFile = new File(folder, config.getConfigName() + ".json");
	            if (!configFile.exists()) {
	                try {
	                    configFile.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	public void SaveConfigs() {
		for(Config config : configs){
			if(config instanceof BindingsConfig) {
				BindingsConfig configg = (BindingsConfig)config;
				configg.Save(configsFolder);
			}
		}
	}
	    
	    public Config getConfig(String configName) {
	        for (Config config : configs) {
	            if (config.getConfigName().toLowerCase().equals(configName.toLowerCase())) {
	                return config;
	            }
	        }
	        return null; 
	    }

}
