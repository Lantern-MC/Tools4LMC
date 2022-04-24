package net.lanternmc.Tools4LMC.GAuth.DrawMap;

import net.lanternmc.Tools4LMCSpigot;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RenderImageMaps extends MapRenderer {

    private static String writeImage;
    public static ConsoleCommandSender Console = Tools4LMCSpigot.getheart().getServer().getConsoleSender();

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        try {
            File BukkitPath = new File("");
            String fileName = BukkitPath.getAbsolutePath()
                    + File.separator + "plugins"
                    + File.separator + "update"
                    + File.separator + player.getName() +  ".png";
            mapCanvas.drawImage(0, 0, ImageIO.read(new File(fileName)));
        } catch (IOException | IllegalArgumentException e) {
            System.out.print(SystemColor.BLUE + e.getMessage());
        }

    }
}