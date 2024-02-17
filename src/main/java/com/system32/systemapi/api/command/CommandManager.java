package com.system32.systemapi.api.command;

import com.system32.systemapi.api.exceptions.CommandNotRegistered;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.system32.systemapi.api.utils.MessageUtils.log;

public class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public void registerCommand(Command cmd) {

        PluginCommand command = cmd.getPlugin().getServer().getPluginCommand(cmd.getName());
        if(command == null) {
            throw new CommandNotRegistered("Command " + cmd.getName() + " is not registered in the plugin.yml");
        }

        commands.add(cmd);
        command.setExecutor(cmd);

        log("Registered command &c" + cmd.getName());

    }

    public Optional<Command> getCommand(String name) {
        return commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}