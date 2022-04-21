package net.lanternmc.AllUtils.MineCraft;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class RailUtils {
    public static List<Chest> getNearbyChests(Block paramBlock) {
        int i = paramBlock.getX();
        int j = paramBlock.getY();
        int k = paramBlock.getZ();
        ArrayList<Chest> arrayList = new ArrayList();
        if (paramBlock.getWorld().getBlockAt(i, j, k).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i, j, k).getType() == Material.TRAPPED_CHEST)
            arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i, j, k).getState()); 
        if (paramBlock.getWorld().getBlockAt(i - 1, j, k).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i - 1, j, k).getType() == Material.TRAPPED_CHEST) {
            arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i - 1, j, k).getState());
            if (paramBlock.getWorld().getBlockAt(i - 2, j, k).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i - 2, j, k).getType() == Material.TRAPPED_CHEST)
                arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i - 2, j, k).getState()); 
        } 
        if (paramBlock.getWorld().getBlockAt(i + 1, j, k).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i + 1, j, k).getType() == Material.TRAPPED_CHEST) {
            arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i + 1, j, k).getState());
            if (paramBlock.getWorld().getBlockAt(i + 2, j, k).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i + 2, j, k).getType() == Material.TRAPPED_CHEST)
                arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i + 2, j, k).getState()); 
        } 
        if (paramBlock.getWorld().getBlockAt(i, j, k - 1).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i, j, k - 1).getType() == Material.TRAPPED_CHEST) {
            arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i, j, k - 1).getState());
            if (paramBlock.getWorld().getBlockAt(i, j, k - 2).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i, j, k - 2).getType() == Material.TRAPPED_CHEST)
                arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i, j, k - 2).getState()); 
        } 
        if (paramBlock.getWorld().getBlockAt(i, j, k + 1).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i, j, k + 1).getType() == Material.TRAPPED_CHEST) {
            arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i, j, k + 1).getState());
            if (paramBlock.getWorld().getBlockAt(i, j, k + 2).getType() == Material.CHEST || paramBlock.getWorld().getBlockAt(i, j, k + 2).getType() == Material.TRAPPED_CHEST)
                arrayList.add((Chest)paramBlock.getWorld().getBlockAt(i, j, k + 2).getState()); 
        } 
        return arrayList;
    }
}