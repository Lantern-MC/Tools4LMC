package net.lanternmc.r1_8.PlayerManager;

import com.comphenix.protocol.events.PacketContainer;
import net.lanternmc.Tools4LMCSpigot;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.InvocationTargetException;

/**
 * 测试
 */
public class Chuli {

    /**
     * 弃用
     * @param p
     */
    public static void pstart(Player p) {
        EntityHorse horse1 = new EntityHorse(((CraftWorld) p.getLocation().getWorld()).getHandle());
        PacketPlayOutAttachEntity sit = new PacketPlayOutAttachEntity(0, ((CraftPlayer) p).getHandle(), horse1);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(sit);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(horse1);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
//        PacketContainer packet = Tools4LMCSpigot.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        try {
            Tools4LMCSpigot.protocolManager.sendServerPacket(p, PacketContainer.fromPacket(packet));
            System.out.println("emmmmm");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void xpcd(Player p, int time, Plugin instance) {
        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1,1);
        p.giveExpLevels(-time);
        new BukkitRunnable() {
            double i = time;
            @Override
            public void run() {
                if (i > 0){
                    p.setExp((float) ((0.99 / time) * i));
                    p.setLevel((int) i);
                }
                if (i == 0) {
                    p.setExp(0);
                    p.setLevel((int) i);
                    cancel();
                }
                i--;
            }
        }.runTaskTimer(instance,20L, 20L);
    }


    public static BukkitTask task;
    /**
     * 示例放入适合的插件请勿调用 这是自动每秒张志 10秒打满
     * @param instance
     * @return
     */
    public static boolean Powner(Player p, Plugin instance) {
        p.setExp(0);
        task = new BukkitRunnable() {

            int i = 6;

            @Override
            public void run() {
                if (p.getExp() >= 0.99) {
                    cancel();
                    p.setExp((float) 0.99);
                }

                if (i <= 10 ) {
                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 2, 2);
                    p.setExp((float) (p.getExp() + (0.99 / i)));
                }

                --i;
            }
        }.runTaskTimer(instance, 10L, 10L);

        return true;
    }

}
