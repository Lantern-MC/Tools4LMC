package net.lanternmc.r1_8.Event;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LongpressEvent extends PlayerInteractEvent implements Cancellable {
    private Result useClickedBlock;

    public LongpressEvent(Player who, Action action, ItemStack item, Block clickedBlock, BlockFace clickedFace) {
        super(who, action, item, clickedBlock, clickedFace);
    }


    @Override
    public boolean isCancelled() {
        return useInteractedBlock() == Result.DENY;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public Result useInteractedBlock() {
        return useClickedBlock;
    }
}
