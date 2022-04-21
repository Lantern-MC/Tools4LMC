package net.lanternmc.Tools4LMC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TLMCListern implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (CommandManager.players.contains(p.getUniqueId())) {
            if (!p.isFlying()) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
            p.sendMessage("");
            p.sendMessage("§c你目前进入了冻结状态！请不要尝试退出当前服务器， 否则你将会被永久移除服务器！");
            p.sendMessage("");
            e.setTo(e.getFrom());
        }
    }

}
