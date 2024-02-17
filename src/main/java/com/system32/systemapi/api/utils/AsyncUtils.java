package com.system32.systemapi.api.utils;

import com.system32.systemapi.SystemAPI;
import org.bukkit.Bukkit;

public class AsyncUtils {
    public static void makeAsync(Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(SystemAPI.get(), task);
    }
    public static void makeAsync(Runnable task, int delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SystemAPI.get(), task, delay * 20L);
    }



}
