package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.ClientUtils;

public class ToggleCommand extends Command {

    @Override
    public void TextEvent(TextEvent e) {
        String text = e.getText().toString();
        if (text.startsWith(".toggle")) {
            String[] parts = text.split(" ");
            if (parts.length >= 2) {
                String moduleName = parts[1];
                Module module = ModuleManager.getModule(moduleName);
                if (module != null) {
                    module.toggle();
                    ClientUtils.displayChatMessage("§aToggled module: §e" + moduleName);
                } else {
                    ClientUtils.displayChatMessage("§cModule not found: §e" + moduleName);
                }
            } else {
                ClientUtils.displayChatMessage("§cInvalid command usage. Usage: .toggle <moduleName>");
            }
        }
    }
}
