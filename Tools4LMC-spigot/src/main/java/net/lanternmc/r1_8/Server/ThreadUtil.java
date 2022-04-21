package net.lanternmc.r1_8.Server;

import net.lanternmc.Tools4LMCSpigot;
import org.bukkit.Bukkit;

/**
 * Utility class for threading.
 *
 * @author rylinaux
 */
public class ThreadUtil {
    /**
     * Run a task in a separate thread.
     *
     * @param runnable the task.
     */
    public static void async(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Tools4LMCSpigot.getheart(), runnable);
    }

    /**
     * Run a task in the main thread.
     *
     * @param runnable the task.
     */
    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(Tools4LMCSpigot.getheart(), runnable);
    }

}
