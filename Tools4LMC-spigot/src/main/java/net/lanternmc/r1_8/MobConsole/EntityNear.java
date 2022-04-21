package net.lanternmc.r1_8.MobConsole;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityNear {
    public static List<Entity> getNearbyEntitys(Location location, double radius) {
        List<Entity> players = new ArrayList();
        Iterator var4 = location.getWorld().getNearbyEntities(location, radius, radius, radius).iterator();
        while(var4.hasNext()) {
            Entity e = (Entity)var4.next();
            if (e instanceof Entity && e.getLocation().distance(location) <= radius) {
                players.add(e);
            }
        }

        return players;
    }
}
