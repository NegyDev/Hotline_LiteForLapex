package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Utils.ClientUtils;

public class HelpCommand extends Command {

    @Override
    public void TextEvent(TextEvent e) {
        String helpCommand = ".help";
        if (e.getText().toString().equals(helpCommand)) {
            ClientUtils.displayChatMessage("§6§l====== §eHotline Lite Help §6§l======");
            ClientUtils.displayChatMessage("§e[§6Help§e] §f.help §6modules §7- §fGet Help About Modules");
            ClientUtils.displayChatMessage("§e[§6Help§e] §f.help §6config  §7- §fGet Help About Configs");
            ClientUtils.displayChatMessage("§e[§6Help§e] §f.help §6module bindings §7- §fGet Help About Module Bindings");
            ClientUtils.displayChatMessage("§e[§6Help§e] §f.help §6bindings §7- §fGet Help About Bindings");
            ClientUtils.displayChatMessage("§6§l================================");
        }
    }
}
