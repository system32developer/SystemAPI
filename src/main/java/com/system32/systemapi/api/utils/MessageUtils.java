package com.system32.systemapi.api.utils;

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

    /**
     * Translates color codes in a string.
     *
     * @param message The string to translate color codes in.
     * @return The string with color codes translated.
     */
    public static String color(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher match = pattern.matcher(message);
        while (match.find()) {
            String color = message.substring(match.start(), match.end());
            message = message.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', "&f" + message);
    }

    /**
     * Sends a message to a CommandSender.
     *
     * @param sender The CommandSender to send the message to.
     * @param message The message to send.
     * @param prefix Whether to prefix the message.
     */
    public static void msg(CommandSender sender, String message, Boolean prefix) {
        if (prefix) sender.sendMessage(color(getPrefix() + message));
        else sender.sendMessage(color(message));
    }

    /**
     * Sends a message to a Player.
     *
     * @param sender The Player to send the message to.
     * @param message The message to send.
     * @param prefix Whether to prefix the message.
     */
    public static void msg(Player sender, String message, Boolean prefix) {
        if (prefix) sender.sendMessage(color(getPrefix() + message));
        else sender.sendMessage(color(message));
    }

    /**
     * Sends a message to a ConsoleCommandSender.
     *
     * @param sender The ConsoleCommandSender to send the message to.
     * @param message The message to send.
     * @param prefix Whether to prefix the message.
     */
    public static void msg(ConsoleCommandSender sender, String message, Boolean prefix) {
        if (prefix) sender.sendMessage(color(getPrefix() + message));
        else sender.sendMessage(color(message));
    }

    /**
     * Creates a TextComponent with a command suggestion.
     *
     * @param text The text of the TextComponent.
     * @param value The command to suggest.
     * @return The created TextComponent.
     */
    public TextComponent msgCommandSuggest(String text, String value) {

        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, value));

        return textComponent;
    }

    /**
     * Creates a TextComponent with a hover text and a command suggestion.
     *
     * @param text The text of the TextComponent.
     * @param hover_msg The hover text.
     * @param value The command to suggest.
     * @return The created TextComponent.
     */
    public TextComponent msgHover(String text, String hover_msg, String value) {

        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, value));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover_msg).create()));
        return textComponent;
    }

    /**
     * Creates a TextComponent with a command to run.
     *
     * @param text The text of the TextComponent.
     * @param cmd The command to run.
     * @return The created TextComponent.
     */
    public TextComponent msgCmd(String text, String cmd) {
        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
        return textComponent;
    }

    /**
     * Creates a TextComponent with a URL to open.
     *
     * @param text The text of the TextComponent.
     * @param url The URL to open.
     * @return The created TextComponent.
     */
    public TextComponent msgURL(String text, String url) {
        TextComponent textComponent = new TextComponent(color(text));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return textComponent;
    }

    /**
     * Creates a TextComponent with an item hover text.
     *
     * @param item The item to display.
     * @param hoverText The hover text.
     * @return The created TextComponent.
     */
    public static TextComponent msgItem(ItemStack item, String hoverText) {
        String nbt = item.hasItemMeta() ? item.getItemMeta().getAsString() : "{}";
        Item contents = new Item(item.getType().getKey().toString(), item.getAmount(), ItemTag.ofNbt(nbt));
        net.md_5.bungee.api.chat.TextComponent component = new TextComponent(color(hoverText));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, contents));
        return component;
    }

    /**
     * Logs a message to the console.
     *
     * @param message The message to log.
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(color(getPrefix() + message));
    }
}
