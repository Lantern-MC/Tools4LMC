package net.lanternmc.r1_8.Title;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleUtils {
   private static final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

   private static Class getNMSClass(String name) {
      try {
         return Class.forName("net.minecraft.server." + version + "." + name);
      } catch (ClassNotFoundException var2) {
         var2.printStackTrace();
         return null;
      }
   }

   private static void sendPacket(Player player, Object packet) throws Exception {
      Object handle = player.getClass().getMethod("getHandle").invoke(player);
      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
      playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
   }

   public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
      try {
         Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object)null);
         Object titleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + title + "\"}");
         Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get((Object)null);
         Object subtitleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + subtitle + "\"}");
         Constructor titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
         Object titlePacket = titleConstructor.newInstance(enumTitle, titleChat, fadeIn, stay, fadeOut);
         Object subtitlePacket = titleConstructor.newInstance(enumSubtitle, subtitleChat, fadeIn, stay, fadeOut);
         sendPacket(player, titlePacket);
         sendPacket(player, subtitlePacket);
      } catch (Exception var13) {
         var13.printStackTrace();
      }

   }

   public static void boardcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
      Iterator var6 = Bukkit.getOnlinePlayers().iterator();

      while(var6.hasNext()) {
         Player p = (Player)var6.next();

         try {
            sendTitle(p, title, subtitle, fadeIn, stay, fadeOut);
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }

   }
}