package net.lanternmc.r1_8.components;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerConn {

    public static void connect(Player p, Plugin pl, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        p.sendPluginMessage(pl, "BungeeCord", b.toByteArray());
        Bukkit.getMessenger().registerOutgoingPluginChannel(pl, "BungeeCord");
    }

}
