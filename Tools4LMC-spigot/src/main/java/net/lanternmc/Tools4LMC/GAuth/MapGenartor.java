package net.lanternmc.Tools4LMC.GAuth;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.*;
import org.bukkit.map.MapView.Scale;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// 等待与开始
public class MapGenartor {
    private static Image image;
    public void render(MapView arg0, MapCanvas arg1, Player arg2) {
        System.out.println("RENDERING!");
        arg1.drawImage(0, 0, image);
    }

    public static void init(){
        try {
            image = ImageIO.read(new File("image.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void S2(Player player) {
        ItemStack map = new ItemStack(Material.MAP, 1);


        MapView view = Bukkit.createMap(player.getWorld());

        //边界
        view.setCenterX((int) player.getLocation().getX());
        view.setCenterZ((int) player.getLocation().getZ());
        MapRenderer renderer = new MapRenderer() {
            boolean rendered = false;

            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                if (rendered) {
                    return;
                }

                MapCursorCollection cursors = new MapCursorCollection();
                MapCursor cursor = new MapCursor((byte) 0, (byte) 0, (byte) 12, (byte) 4, true);

                cursors.addCursor(cursor);
                mapCanvas.setCursors(cursors);

                cursors.addCursor(player.getLocation().getBlockX(), (int) player.getLocation().getZ(), (byte) 12);
                mapCanvas.setCursors(cursors);

                rendered = true;
            }
        };

        view.setScale(Scale.CLOSEST);
        map.setDurability(view.getId());

        player.getInventory().setItem(0, map);
    }


    public static void S2A(Player player) {
        MapView map = Bukkit.createMap(player.getWorld());

        map.setCenterX((int) player.getLocation().getX());
        map.setCenterZ((int) player.getLocation().getZ());

        map.setScale(Scale.CLOSEST);
        map.getRenderers().clear();
        map.addRenderer(new MapRenderer() {

            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {

                MapCursorCollection cursors = new MapCursorCollection();

                cursors.addCursor((byte) 60, (byte) 70, (byte) 12, (byte) 4);
                mapCanvas.setCursors(cursors);

            }
        });


        ItemStack m = new ItemStack(Material.MAP);

        player.getInventory().addItem(m);
    }


    public static void createMAP(Player p) {
        ItemStack map = new ItemStack(Material.MAP);
        MapView mapView = Bukkit.createMap(Bukkit.getWorlds().get(0));
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        MapRenderer renderer = new MapRenderer() {
            boolean rendered = false;
            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                if (rendered) {
                    return;
                }
                MapCursorCollection cursorCollection = new MapCursorCollection();
                cursorCollection.addCursor(10, 5, (byte) 1);
                final MapCursorCollection cursors = mapCanvas.getCursors();
                for (int i = 0; i < cursors.size(); i++) {
                    player.sendMessage(cursors.getCursor(i).toString());
                }

                mapView.setScale(Scale.CLOSEST);
                mapCanvas.getMapView().setCenterX(10);
                mapCanvas.getMapView().setCenterZ(50);
                mapCanvas.setCursors(cursorCollection);
                mapCanvas.drawImage(500, 500, image);
                mapCanvas.setPixel(100, 100, (byte) 5);
                try {
                    image = ImageIO.read(new File("image.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rendered = true;
            }
        };
        mapView.addRenderer(renderer);
        mapMeta.setScaling(true);
        map.setItemMeta(mapMeta);
        p.getInventory().addItem(map);

    }
}