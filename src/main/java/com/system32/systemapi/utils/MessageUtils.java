package com.system32.systemapi.utils;

import com.system32.systemapi.SystemAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    public TextComponent msgCommandSuggest(String text, String value) {

        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, value));

        return textComponent;
    }

    public TextComponent msgHover(String text, String hover_msg, String value) {

        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, value));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover_msg).create()));
        return textComponent;
    }

    public TextComponent msgCmd(String text, String cmd) {

        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));

        return textComponent;
    }

    public static TextComponent msgItem(ItemStack item, String hoverText){
        String nbt = item.hasItemMeta() ? item.getItemMeta().getAsString() : "{}";
        Item contents = new Item(item.getType().getKey().toString(), item.getAmount(), ItemTag.ofNbt(nbt));
        net.md_5.bungee.api.chat.TextComponent component = new TextComponent(color(hoverText));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, contents));
        return component;
    }
    public static void log(String message){
        Bukkit.getConsoleSender().sendMessage(color(getPrefix()+message));
    }
}
