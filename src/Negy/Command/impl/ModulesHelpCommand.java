package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Utils.ClientUtils;

public class ModulesHelpCommand extends Command{

    @Override
    public void TextEvent(TextEvent e) {
        String helpCommand = ".help modules";
        if (e.getText().toString().equals(helpCommand)) {
            ClientUtils.displayChatMessage("§6§l====== §eHotline Lite Modules §6§l======");
            ClientUtils.displayChatMessage("§6Module Name=============Description");
            for (Module module : ModuleManager.modules) {
                String moduleName = String.format("%-20s", module.getName());
                String moduleDesc = module.description;
                String moduleAndDesc = "§e" + moduleName + "§7-" + moduleDesc;
                ClientUtils.displayChatMessage(moduleAndDesc);
            }
            ClientUtils.displayChatMessage("§6§l====================================");
        }
    }

}
