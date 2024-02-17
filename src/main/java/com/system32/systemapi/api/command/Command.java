package com.system32.systemapi.api.command;

import com.system32.systemapi.SystemAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.system32.systemapi.api.utils.MessageUtils.msg;

public abstract class Command implements TabCompleter, CommandExecutor {
    private final String name;

    private final SystemAPI plugin;
    private final List<SubCommand> subCommands;
    private final String permission;

    public Command(SystemAPI plugin, String name){
        this(plugin, name, new ArrayList<>(), null);
    }

    public Command(SystemAPI plugin, String name, String permission){
        this(plugin, name, new ArrayList<>(), permission);
    }

    public Command(SystemAPI plugin, String name, List<SubCommand> subCommands, String permission){
        this.name = name;
        this.plugin = plugin;
        this.subCommands = subCommands == null ? new ArrayList<>() : subCommands;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }


    public SystemAPI getPlugin() {
        return plugin;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (getPermission() != null && !sender.hasPermission(getPermission())) {
            msg(sender, "&fYou do not have permission to execute this command, you need the permission &c("+getPermission()+")", true);
            return true;
        }

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

    protected void addSubCommands(SubCommand ...subCommands){
        this.subCommands.addAll(List.of(subCommands));
    }

    public final List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
