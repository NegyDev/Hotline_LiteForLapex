package Negy.Command;

import Negy.Event.Event;
import Negy.Module.Module;
import Negy.Module.ModuleManager;
import Negy.Event.impl.TextEvent;

public class Command {
	
    public static String[] commands = {".help",".bind",".unbind",".teleport",".toggle",".config"};

    public static boolean stringCheckEvent(String text) {
        for (String command : commands) {
            if (text.toString().contains(command)) {
                return true;
            }
        }
        return false;
    }
	
	public static void ChatReceiveEventCall(TextEvent e) {
        for (Command m : CommandManager.commands) {
            m.TextEvent(e);
        }
    }
	
	public void TextEvent(TextEvent e) {
		
	}
}
