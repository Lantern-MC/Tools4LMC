package net.lanternmc.r1_8.BossBar;


import net.lanternmc.r1_8.BossBar.reflection.Reflection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BossBarAPI {
   private static final Map barMap = new ConcurrentHashMap();

   public static void setMessage(Plugin instance,Player player, String message) {
      setMessage(instance, player, message, 100.0F);
   }

   public static void setMessage(Plugin instance,Player player, String message, float percentage) {
      setMessage(instance, player, message, percentage, 0);
   }

   public static void setMessage(Plugin instance, Player player, String message, float percentage, int timeout) {
      setMessage(instance, player, message, percentage, timeout, 100.0F);
   }

   public static void setMessage(Plugin instaner, Player player, String message, float percentage, int timeout, float minHealth) {
      if (!barMap.containsKey(player.getUniqueId())) {
         barMap.put(player.getUniqueId(), new BossBar(instaner,player, message, percentage, timeout, minHealth));
      }

      BossBar bar = (BossBar)barMap.get(player.getUniqueId());
      if (!bar.message.equals(message)) {
         bar.setMessage(message);
      }

      float newHealth = percentage / 100.0F * bar.getMaxHealth();
      if (bar.health != newHealth) {
         bar.setHealth(percentage);
      }

      if (!bar.isVisible()) {
         bar.setVisible(true);
      }

   }

   public static String getMessage(Player player) {
      BossBar bar = getBossBar(player);
      return bar == null ? null : bar.getMessage();
   }

   public static boolean hasBar(Player player) {
      return barMap.containsKey(player.getUniqueId());
   }

   public static void removeBar(Player player) {
      BossBar bar = getBossBar(player);
      if (bar != null) {
         bar.setVisible(false);
         barMap.remove(player.getUniqueId());
      }

   }

   public static void setHealth(Player player, float percentage) {
      BossBar bar = getBossBar(player);
      if (bar != null) {
         bar.setHealth(percentage);
      }

   }

   public static float getHealth(Player player) {
      BossBar bar = getBossBar(player);
      return bar == null ? -1.0F : bar.getHealth();
   }

   public static BossBar getBossBar(Player player) {
      return player == null ? null : (BossBar)barMap.get(player.getUniqueId());
   }

   public static Collection getBossBars() {
      List list = new ArrayList(barMap.values());
      return list;
   }

   protected static void sendPacket(Player p, Object packet) {
      if (p != null && packet != null) {
         try {
            Object handle = Reflection.getHandle(p);
            Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
            Reflection.getMethod(connection.getClass(), "sendPacket", Reflection.getNMSClass("Packet")).invoke(connection, packet);
         } catch (Exception var4) {
         }
      } else {
         throw new IllegalArgumentException("player and packet cannot be null");
      }
   }
}
