package com.system32.systemapi.utils;

import com.system32.systemapi.SystemAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.system32.systemapi.SystemAPI.getPrefix;

public class MessageUtils {
    public static String color(String message){
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher match = pattern.matcher(message);
        while(match.find()){
            String color = message.substring(match.start(), match.end());
            message = message.replace(color, ChatColor.of(color)+"");
            match = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', "&f"+message);
    }

    public static void msg(CommandSender sender, String message, Boolean prefix){
        if(prefix) sender.sendMessage(color(getPrefix()+message));
        else sender.sendMessage(color(message));
    }
    public static void msg(Player sender, String message, Boolean prefix){
        if(prefix) sender.sendMessage(color(getPrefix()+message));
        else sender.sendMessage(color(message));
    }
    public static void msg(ConsoleCommandSender sender, String message, Boolean prefix){
        if(prefix) sender.sendMessage(color(getPrefix()+message));
        else sender.sendMessage(color(message));
    }
    public static void log(String message){
        Bukkit.getConsoleSender().sendMessage(color(getPrefix()+message));
    }
}
