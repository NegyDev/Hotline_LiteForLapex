package Negy.Command.impl;

import org.lwjgl.input.Keyboard;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.ClientUtils;

public class ModuleBindingsHelpCommand extends Command {

    @Override
    public void TextEvent(TextEvent e) {
        String helpCommand = ".help module bindings";
        if (e.getText().toString().equals(helpCommand)) {
            ClientUtils.displayChatMessage("§6§l====== §eHotline Lite Module Bindings §6§l======");
            for (Module module : ModuleManager.modules) {
                String bindingInfo = "§e" + module.getName() + "§7: §f" + GetKey(module.getKey());
                ClientUtils.displayChatMessage(bindingInfo);
            }
            ClientUtils.displayChatMessage("§6§l====================================");
        }
    }

    public String GetKey(int keyCode) {
        return Keyboard.getKeyName(keyCode);
    }
}
