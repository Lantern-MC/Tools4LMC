package net.lanternmc.AllUtils.MineCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.SignChangeEvent;

public class SignUtils {
    public static boolean isSign(Block paramBlock) {
        return (paramBlock.getType() == Material.SIGN_POST || paramBlock.getType() == Material.WALL_SIGN);
    }
    
    public static BlockFace getFacing(Block paramBlock) {
        return getBack(paramBlock);
    }
    
    public static BlockFace getFront(Block paramBlock) {
        if (paramBlock.getType() == Material.SIGN_POST) {
            switch (paramBlock.getData()) {
                case 0:
                    return BlockFace.SOUTH;
                case 1:
                case 2:
                case 3:
                    return BlockFace.SOUTH_WEST;
                case 4:
                    return BlockFace.WEST;
                case 5:
                case 6:
                case 7:
                    return BlockFace.NORTH_WEST;
                case 8:
                    return BlockFace.NORTH;
                case 9:
                case 10:
                case 11:
                    return BlockFace.NORTH_EAST;
                case 12:
                    return BlockFace.EAST;
                case 13:
                case 14:
                case 15:
                    return BlockFace.SOUTH_EAST;
            } 
            return BlockFace.SELF;
        } 
        switch (paramBlock.getData()) {
            case 2:
                return BlockFace.NORTH;
            case 3:
                return BlockFace.SOUTH;
            case 4:
                return BlockFace.WEST;
            case 5:
                return BlockFace.EAST;
        } 
        return BlockFace.SELF;
    }
    
    public static Block getFrontBlock(Block paramBlock) {
        return paramBlock.getRelative(getFront(paramBlock));
    }
    
    public static BlockFace getBack(Block paramBlock) {
        if (paramBlock.getType() == Material.SIGN_POST) {
            switch (paramBlock.getData()) {
                case 0:
                    return BlockFace.NORTH;
                case 1:
                case 2:
                case 3:
                    return BlockFace.NORTH_EAST;
                case 4:
                    return BlockFace.EAST;
                case 5:
                case 6:
                case 7:
                    return BlockFace.SOUTH_EAST;
                case 8:
                    return BlockFace.SOUTH;
                case 9:
                case 10:
                case 11:
                    return BlockFace.SOUTH_WEST;
                case 12:
                    return BlockFace.WEST;
                case 13:
                case 14:
                case 15:
                    return BlockFace.NORTH_WEST;
            } 
            return BlockFace.SELF;
        } 
        switch (paramBlock.getData()) {
            case 2:
                return BlockFace.SOUTH;
            case 3:
                return BlockFace.NORTH;
            case 4:
                return BlockFace.EAST;
            case 5:
                return BlockFace.WEST;
        } 
        return BlockFace.SELF;
    }
    
    public static Block getBackBlock(Block paramBlock) {
        return paramBlock.getRelative(getBack(paramBlock));
    }
    
    public static BlockFace getRight(Block paramBlock) {
        if (paramBlock.getType() == Material.SIGN_POST) {
            switch (paramBlock.getData()) {
                case 0:
                    return BlockFace.EAST;
                case 1:
                case 2:
                case 3:
                    return BlockFace.SOUTH_EAST;
                case 4:
                    return BlockFace.SOUTH;
                case 5:
                case 6:
                case 7:
                    return BlockFace.SOUTH_WEST;
                case 8:
                    return BlockFace.WEST;
                case 9:
                case 10:
                case 11:
                    return BlockFace.NORTH_WEST;
                case 12:
                    return BlockFace.NORTH;
                case 13:
                case 14:
                case 15:
                    return BlockFace.NORTH_EAST;
            } 
            return BlockFace.SELF;
        } 
        switch (paramBlock.getData()) {
            case 2:
                return BlockFace.WEST;
            case 3:
                return BlockFace.EAST;
            case 4:
                return BlockFace.SOUTH;
            case 5:
                return BlockFace.NORTH;
        } 
        return BlockFace.SELF;
    }
    
    public static Block getLeftBlock(Block paramBlock) {
        return paramBlock.getRelative(getLeft(paramBlock));
    }
    
    public static BlockFace getLeft(Block paramBlock) {
        if (paramBlock.getType() == Material.SIGN_POST) {
            switch (paramBlock.getData()) {
                case 0:
                    return BlockFace.WEST;
                case 1:
                case 2:
                case 3:
                    return BlockFace.NORTH_WEST;
                case 4:
                    return BlockFace.NORTH;
                case 5:
                case 6:
                case 7:
                    return BlockFace.NORTH_EAST;
                case 8:
                    return BlockFace.EAST;
                case 9:
                case 10:
                case 11:
                    return BlockFace.SOUTH_EAST;
                case 12:
                    return BlockFace.SOUTH;
                case 13:
                case 14:
                case 15:
                    return BlockFace.SOUTH_WEST;
            } 
            return BlockFace.SELF;
        } 
        switch (paramBlock.getData()) {
            case 2:
                return BlockFace.EAST;
            case 3:
                return BlockFace.WEST;
            case 4:
                return BlockFace.NORTH;
            case 5:
                return BlockFace.SOUTH;
        } 
        return BlockFace.SELF;
    }
    
    public static Block getRightBlock(Block paramBlock) {
        return paramBlock.getRelative(getRight(paramBlock));
    }
    
    public static boolean isCardinal(Block paramBlock) {
        if (paramBlock.getType() != Material.SIGN_POST)
            return true; 
        switch (paramBlock.getData()) {
            case 0:
            case 4:
            case 8:
            case 12:
                return true;
        } 
        return false;
    }
    
    public static BlockFace getClockWise(BlockFace paramBlockFace) {
        switch (paramBlockFace) {
            case NORTH:
                return BlockFace.EAST;
            case EAST:
                return BlockFace.SOUTH;
            case SOUTH:
                return BlockFace.WEST;
            case WEST:
                return BlockFace.NORTH;
        } 
        return BlockFace.SELF;
    }
    
    public static BlockFace getCounterClockWise(BlockFace paramBlockFace) {
        switch (paramBlockFace) {
            case NORTH:
                return BlockFace.WEST;
            case EAST:
                return BlockFace.NORTH;
            case SOUTH:
                return BlockFace.EAST;
            case WEST:
                return BlockFace.SOUTH;
        } 
        return BlockFace.SELF;
    }
    
    public static void cancelSign(SignChangeEvent paramSignChangeEvent) {
        paramSignChangeEvent.setCancelled(true);
        paramSignChangeEvent.getBlock().breakNaturally();
    }
}
