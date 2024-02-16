package com.system32.systemapi.api.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command implements TabCompleter, CommandExecutor {
    private final String name;

    private final String description;
    private final Plugin plugin;
    private List<SubCommand> subCommands;
    private final String permission;

    public Command(Plugin plugin, String name, String description){
        this(plugin, name, description, new ArrayList<>(), null);
    }

    public Command(Plugin plugin, String name, String description, String permission){
        this(plugin, name, description, new ArrayList<>(), permission);
    }

    public Command(Plugin plugin, String name, String description, List<SubCommand> subCommands, String permission){
        this.name = name;
        this.description = description;
        this.plugin = plugin;
        this.subCommands = subCommands == null ? new ArrayList<>() : subCommands;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Plugin getPlugin() {
        return plugin;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                    subCommand.execute(sender, subArgs);
                    return true;
                }
            }
        }
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public final List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args){
        return onTab(sender, args);
    }

    public List<String> onTab(CommandSender sender, String[] args) {
        if (args.length == 0) {
            List<String> subCommandNames = new ArrayList<>();
            for (SubCommand subCommand : subCommands) {
                subCommandNames.add(subCommand.getName());
            }
            return subCommandNames;
        }
        if (args.length == 1) {
            List<String> subCommandNames = new ArrayList<>();
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().startsWith(args[0])) {
                    subCommandNames.add(subCommand.getName());
                }
            }
            return subCommandNames;
        }

        return List.of("");
    }

    protected void addSubCommand(SubCommand subCommand){
        subCommands.add(subCommand);
    }

    public final List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
