package net.lanternmc.Tools4LMC.GAuth;

import com.avaje.ebeaninternal.server.core.Message;
import net.lanternmc.Tools4LMC.GAuth.DrawMap.DownloadQRImage;
import net.lanternmc.Tools4LMC.GAuth.DrawMap.RenderImageMaps;
import net.lanternmc.Tools4LMCSpigot;
import net.lanternmc.r1_8.message.SendMessageUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

public class MapinHand {
    /**
     *
     * @param p
     */
//    @SuppressWarnings("deprecation")
    public static void Run(Player p) {
        String url = GoogleAuth.genPngurl(p);
        DownloadQRImage.download(url,p);
        MapView map = Bukkit.createMap(p.getWorld());
        map.setCenterX(0);
        map.setCenterZ(0);
        map.getRenderers().clear();
        map.addRenderer(new RenderImageMaps());
        ItemStack newMap = new ItemStack(Material.MAP);
        newMap.setDurability(map.getId());
        ItemMeta meta = newMap.getItemMeta();
        newMap.setItemMeta(meta);
        ItemStack oldinHand = p.getInventory().getItemInHand();
        p.setItemInHand(newMap);

//        TextComponent text = new TextComponent(url);
//        text.setText();
//        text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, url));
//
//        BaseComponent[] bc = new BaseComponent[0];
//        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, bc));
//        p.spigot().sendMessage(text);

        p.sendMessage("§6 点击开始扫码绑定吧(如果出现打不开可找腐竹或者Dev寻求帮助)" +
                "需要手机软件 <身份验证器APP> " +
                "进行 后期会开发扫码登陆等高级向...." +
                " \n§r" + url);
        SendMessageUtils.sendconsoleMessage(new String[]{url});

        new BukkitRunnable() {

            int i = 60;

            @Override
            public void run() {


                if (i == 0) {
                    DownloadQRImage.deleteFile(p);
                    p.setItemInHand(oldinHand);
                }
                i--;

            }
        }.runTaskTimer(Tools4LMCSpigot.getheart(), 20L, 20L);
    }




}
