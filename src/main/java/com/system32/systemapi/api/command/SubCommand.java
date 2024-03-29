package com.system32.systemapi.api.command;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    private final String name;

    public SubCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(CommandSender sender, String[] args);
}
