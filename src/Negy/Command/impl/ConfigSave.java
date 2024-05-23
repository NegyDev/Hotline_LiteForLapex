package Negy.Command.impl;

import Negy.Client;
import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Utils.ClientUtils;
import org.lwjgl.opengl.Display;

public class ConfigSave extends Command {

    public void TextEvent(TextEvent e) {
        String helpCommand = ".config save";
        if (e.getText().toString().equals(helpCommand)) {
            Client.configManager.SaveConfigs();
            ClientUtils.displayChatMessage("§aConfigurations saved successfully!");
        }
    }
}
