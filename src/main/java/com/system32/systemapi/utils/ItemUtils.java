package com.system32.systemapi.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.system32.systemapi.utils.MessageUtils.color;

public class ItemUtils {
    public static ItemStack item(Material material, String name, String... lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color(name));
        List<String> coloredLore = new ArrayList<>();
        coloredLore.add(color(" "));
        for (String s : lore) {
            coloredLore.add(color("&f"+s));
        }
        meta.setLore(coloredLore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack item(Material material, String name, List<String> lore){
        return item(material, name, lore.toArray(new String[0]));
    }

    public static ItemStack skull(String name, String texture, String... lore){
        ItemStack item = item(Material.PLAYER_HEAD, name, lore);
        NBT.modify(item, nbt -> {
            final ReadWriteNBT skullOwnerCompound = nbt.getOrCreateCompound("SkullOwner");
            skullOwnerCompound.setUUID("Id", UUID.randomUUID());
            skullOwnerCompound.getOrCreateCompound("Properties")
                    .getCompoundList("textures")
                    .addCompound()
                    .setString("Value", texture);
        });

        return item;
    }
    public static ItemStack skull(String name, Player owner, String... lore){
        ItemStack skull = item(Material.PLAYER_HEAD, name, lore);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwnerProfile(owner.getPlayerProfile());
        skull.setItemMeta(meta);
        return skull;
    }
    public static ItemStack skull(String name, String texture, List<String> lore){
        return skull(name, texture, lore.toArray(new String[0]));
    }
}
