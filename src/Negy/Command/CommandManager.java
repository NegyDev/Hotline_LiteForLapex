package Negy.Command;

import java.util.concurrent.CopyOnWriteArrayList;

import Negy.Command.impl.*;
import Negy.Module.Module;

public class CommandManager {
	public static CopyOnWriteArrayList<Command> commands = new CopyOnWriteArrayList();

	public static void AddCommands() {
		commands.add(new HelpCommand());
		commands.add(new ModuleBindingsHelpCommand());
		commands.add(new BindingsHelpCommand());
		commands.add(new ConfigSave());
		commands.add(new BindCommand());
		commands.add(new ModulesHelpCommand());
		commands.add(new UnbindCommand());
		commands.add(new ToggleCommand());
	}

}
