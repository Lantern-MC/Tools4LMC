package net.lanternmc.r1_8.Tab;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabUtils {
    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }
    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static void sendTablist(Player p, String msg, String msg2) {

        try {
            switch (getServerVersion()) {
                case "v1_8_R3":
                    Object header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, "{'text': '" + msg + "'}");
                    Object footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, "{'text': '" + msg2 + "'}");
                    Object popup = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[] { getNmsClass("IChatBaseComponent") }).newInstance(header);

                    Field f = popup.getClass().getDeclaredField("b");
                    f.setAccessible(true);
                    f.set(popup, footer);
                    Object nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p);
                    Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
                    pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, popup);

                case "v1_8_R2":
                    header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[]{String.class}).invoke(null, "{'text': '" + msg + "'}");
                    footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[]{String.class}).invoke(null, "{'text': '" + msg2 + "'}");
                    popup = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[]{getNmsClass("IChatBaseComponent")}).newInstance(header);
                    f = popup.getClass().getDeclaredField("b");
                    f.setAccessible(true);
                    f.set(popup, footer);
                    nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p);
                    pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
                    pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, popup);
            }
        } catch (IllegalAccessException
                |IllegalArgumentException
                |java.lang.reflect.InvocationTargetException
                |NoSuchMethodException
                |SecurityException
                |ClassNotFoundException
                |InstantiationException
                |NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}