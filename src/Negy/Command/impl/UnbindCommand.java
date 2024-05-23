package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.ClientUtils;

public class UnbindCommand extends Command{

    @Override
    public void TextEvent(TextEvent e) {
        String text = e.getText().toString();
        if (text.startsWith(".unbind")) {
            String[] parts = text.split(" ");
            if (parts.length >= 2) {
                String moduleName = parts[1];
                Module module = ModuleManager.getModule(moduleName);
                if (module != null) {
                    module.KeyCode = 0;
                    ClientUtils.displayChatMessage("§aSuccessfully unbinded module: §f" + module.getName());
                    return;
                }
                ClientUtils.displayChatMessage("§cModule '" + moduleName + "' not found. Use '.help modules' to see available modules.");
                return;
            }
            ClientUtils.displayChatMessage("§cIncorrect format. §7Usage: §f.unbind <module>");
        }
    }

}
