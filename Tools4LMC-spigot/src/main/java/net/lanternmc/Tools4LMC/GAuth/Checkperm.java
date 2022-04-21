package net.lanternmc.Tools4LMC.GAuth;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class Checkperm implements Listener {
    long timestemp = 30 * 1000 ;
    public HashMap<Player, Boolean> stats = new HashMap<>();

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    @EventHandler
    public void onjoin(PlayerMoveEvent e) {
        LuckPerms api = LuckPermsProvider.get();
        if (e.getPlayer().hasPermission("*") || isPlayerInGroup(e.getPlayer(), "owner")) {
            creatchannl(e.getPlayer());
        }
    }

    private void creatchannl(Player p) {
        if (stats.get(p) == null) {
            // 创建通道
        }
    }

}
