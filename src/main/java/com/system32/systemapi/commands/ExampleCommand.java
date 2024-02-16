package com.system32.systemapi.commands;


import com.system32.systemapi.SystemAPI;
import com.system32.systemapi.api.command.Command;
import com.system32.systemapi.api.command.SubCommand;
import org.bukkit.command.CommandSender;

public class ExampleCommand extends Command {
    public ExampleCommand() {
        super(SystemAPI.get(), "example", "systemapi.example");

        // Add subcommands
        addSubCommand(new SubCommand("hi") {
            @Override
            public void execute(CommandSender sender, String[] args) {
                sender.sendMessage("¡Hi, " + sender.getName() + "!");
            }
        });

        addSubCommand(new SubCommand("bye") {
            @Override
            public void execute(CommandSender sender, String[] args) {
                sender.sendMessage("¡Bye, " + sender.getName() + "!");
            }
        });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("¡Hi, " + sender.getName() + "! this is the main command.");
    }
}