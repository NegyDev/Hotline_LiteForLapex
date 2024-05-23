package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.ClientUtils;

public class BindCommand extends Command {

    @Override
    public void TextEvent(TextEvent e) {
        String text = e.getText().toString();
        if (text.startsWith(".bind")) {
            String[] parts = text.split(" ");
            if (parts.length >= 3) {
                String moduleName = parts[1];
                String key = parts[2].toUpperCase();
                Module module = ModuleManager.getModule(moduleName);
                if (module != null) {
                    module.setKey(key);
                    ClientUtils.displayChatMessage("§aKey §f'" + key + "' §asuccessfully assigned to module §f'" + module.getName() + "'§a.");
                    return;
                }
                ClientUtils.displayChatMessage("§cModule '" + moduleName + "' not found. Use '.help modules' to see available modules.");
                return;
            }
            ClientUtils.displayChatMessage("§cIncorrect format. §7Usage: §f.bind <module> <key>");
        }
    }
}
