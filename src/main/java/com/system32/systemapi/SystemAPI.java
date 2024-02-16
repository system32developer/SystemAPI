package com.system32.systemapi;

import com.system32.systemapi.api.command.CommandManager;
import com.system32.systemapi.api.config.CustomConfigFile;
import com.system32.systemapi.commands.ExampleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SystemAPI extends JavaPlugin {
    private static SystemAPI api;
    private static final String prefix = "#1a3399ѕ#2b44a3ʏ#3d56adѕ#4f68b7ᴛ#617ac1ᴇ#738cccᴍ#849ed6ᴀ#96b0e0ᴘ#a8c2eaɪ#bad4f4:&f ";
    private CommandManager commandManager;

    CustomConfigFile config;
    CustomConfigFile lang;

    @Override
    public void onEnable() {
        // Plugin startup logic
        api = this;
        config = new CustomConfigFile("config");
        lang = new CustomConfigFile("lang");
        commandManager = new CommandManager();
        commandManager.registerCommand(new ExampleCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //TODO
    // Database Manager
    // 




    public static SystemAPI get() {
        return api;
    }

    public static String getPrefix() {
        return prefix;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public CustomConfigFile getConfig() {
        return config;
    }
    public CustomConfigFile getLang() {
        return lang;
    }
}
