package com.system32.systemapi.commands;

// ... existing code ...

import com.system32.systemapi.SystemAPI;
import com.system32.systemapi.api.command.Command;
import com.system32.systemapi.api.command.SubCommand;
import org.bukkit.command.CommandSender;

public class ExampleCommand extends Command {
    public ExampleCommand() {
        super(SystemAPI.get(), "example", "systemapi.example");

        // Add subcommands
        addSubCommand(new SubCommand("hola") {
            @Override
            public void execute(CommandSender sender, String[] args) {
                sender.sendMessage("¡Hola, " + sender.getName() + "!");
            }
        });

        addSubCommand(new SubCommand("adios") {
            @Override
            public void execute(CommandSender sender, String[] args) {
                sender.sendMessage("¡Adiós, " + sender.getName() + "!");
            }
        });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("¡Hola, " + sender.getName() + "! Este es el comando principal.");
    }
}