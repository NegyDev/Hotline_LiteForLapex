package Negy.Command.impl;

import Negy.Command.Command;
import Negy.Event.impl.TextEvent;
import Negy.Utils.ClientUtils;

public class BindingsHelpCommand extends Command {

    @Override
    public void TextEvent(TextEvent e) {
        String helpCommand = ".help bindings";
        if (e.getText().toString().equals(helpCommand)) {
            ClientUtils.displayChatMessage("§6§l====== §eHotline Lite Bindings Help §6§l======");
            ClientUtils.displayChatMessage("§e.bind <module> <Key>§7 - Assign any module to any key");
            ClientUtils.displayChatMessage("§e.unbind <module>§7 - Removes Key Assignment in Module");
            ClientUtils.displayChatMessage("§6§l================================");
        }
    }
}
