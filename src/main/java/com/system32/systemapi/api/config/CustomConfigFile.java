package com.system32.systemapi.api.config;

import com.system32.systemapi.SystemAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomConfigFile extends FileConfiguration {
    private File customConfigFile;
    private FileConfiguration customConfig;
    private final String fileName;

    public CustomConfigFile(String fileName) {
        this.fileName = fileName;
        createCustomConfig();
    }


    public void saveConfig() {
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void createCustomConfig() {
        customConfigFile = new File(SystemAPI.get().getDataFolder(), fileName + ".yml");
        if (!customConfigFile.exists()) {
            SystemAPI.get().saveResource(fileName + ".yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    // Delegate all other methods to customConfig
    public int getInt(String path) {
        return customConfig.getInt(path);
    }

    public boolean getBoolean(String path) {
        return customConfig.getBoolean(path);
    }

    public double getDouble(String path) {
        return customConfig.getDouble(path);
    }

    public long getLong(String path) {
        return customConfig.getLong(path);
    }

    public List<?> getList(String path) {
        return customConfig.getList(path);
    }

    public List<String> getStringList(String path) {
        return customConfig.getStringList(path);
    }
    public String getString(String path) {
        return customConfig.getString(path);
    }

    public void set(String path, Object value) {
        customConfig.set(path, value);
        saveConfig();
    }

    @Override
    public String saveToString() {
        return null;
    }

    @Override
    public void loadFromString(String s) throws InvalidConfigurationException {

    }
}
