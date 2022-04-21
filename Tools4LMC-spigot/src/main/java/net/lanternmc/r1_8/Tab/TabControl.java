package net.lanternmc.r1_8.Tab;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TabControl {
    public static void sendTab(Plugin plugin, String 上层, String 下层) {
        Bukkit.getOnlinePlayers().forEach(online -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    TabUtils.sendTablist(online, 上层, 下层);
                }
            }.runTaskTimer(plugin, 20L, 3L);
        });
    }
}
