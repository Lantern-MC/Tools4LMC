package net.lanternmc.Tools4LMC;

import ch.ethz.ssh2.Connection;
import net.lanternmc.ExceptMC.SSH.SshLinux;
import net.lanternmc.Tools4LMC.GAuth.*;
import net.lanternmc.Tools4LMCSpigot;
import net.lanternmc.r1_8.PlayerManager.BoomClient;
import net.lanternmc.r1_8.PlayerManager.Chuli;
import net.lanternmc.r1_8.Title.TitleUtils;
import net.lanternmc.r1_8.message.SendMessageUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.scheduler.BukkitRunnable;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * /lmc
 */
public class CommandManager implements CommandExecutor {

    public static List<java.util.UUID> players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("Tools.lanternmc.staff")) {
                SendMessageUtils.sendMessage(sender
                        , "§m---------------------"
                        , "§7 我的世界§cLanternMC§7服务器 §6核心插件T4LMC 帮助信息"
                        , "§eV" + Tools4LMCSpigot.getheart().getDescription().getVersion()
                        , ""
                        , "§f/lmc ssh ip userName userPwd cmd  --  §6远程ssh链接"
                        , "§f/lmc createcode  --  §6创建"
                        , "§f/lmc code <6个数字>  --  §630秒验证自己验证器中的Code"
                        , "§f/lmc list  --  §6插件列表"
                        , "§f/lmc freeze <player>  --  §6冻结外挂"
                        , "§f/lmc boom <player>  --  §6爆炸客户端"
                        , "§f/lmc plugin <load/unload/reload> <插件名字>  --  §6插件管理"
                        , "§f/lmc fly [player]  --  §6飞行"
                        , "§m---------------------"
                );
                return true;
            } else {
                SendMessageUtils.sendMessage(sender
                        , "§m---------------------"
                        , "§9 我的世界§cLanternMC§9服务器 §6核心插件T4LMC 玩家帮助信息"
                        , "§eV" + Tools4LMCSpigot.getheart().getDescription().getVersion()
                        , ""
                        , "§f/lmc fly [player]  --  §6飞行"
                        , "§m---------------------"
                );
                return true;
            }
        }
        //爆炸客户端
        if(args[0].equals("boom")){
            if (!sender.hasPermission("Tools.lanternmc.boom")) {
                sender.sendMessage("??????");
            } else {
                Player boomp = Bukkit.getPlayer(args[1]);
                if (boomp == null) {
                    sender.sendMessage("qwq");
                } else {
                    if (boomp.isOnline()){
                        (new BukkitRunnable() {
                            int i = 10;
                            public void run() {
                                // 10秒时发
                                if(i == 10) {
                                    TitleUtils.sendTitle(boomp,"§e[§4!§e][§4!§e][§4!§e]","§a请§7" + boomp.getName() + "§a截全屏发给管理",1, 20, 0);
                                }
                                // 滞留最后的在boom时仍显示
                                if(i == 1) {
                                    TitleUtils.sendTitle(boomp, "§a请截全屏发给管理","§a加QQ频道中转群891093994 点群公告频道入口 进申诉频道",1, 600, 0);
                                }
                                //最后的爆炸操作
                                if (i == 0) {
                                    if (BoomClient.crashInternal(boomp)) {
                                        Bukkit.broadcastMessage("§c§l由于一位玩家客户端异常，服务器已强制（强迫）踢出游戏");
                                        sender.sendMessage("§c已成功将玩家§7" + boomp.getName() + "§c客户端锁定。");
                                    } else {
                                        sender.sendMessage("§c你好像炸偏了，快去后台看看炸哪里了，别炸到别人家里了！");
                                    }
                                    cancel();
                                }
                                --this.i;
                            }
                        }).runTaskTimerAsynchronously(Tools4LMCSpigot.getheart(), 20, 20);
                    } else {
                        sender.sendMessage("不在线炸个屁");return true;
                    }
                }
            }
            return true;
        }
        //Plugin
        if(args[0].equals("plugin")){
            if (args.length <= 2) {
                sender.sendMessage("§4/lmc plugin <load/unload/reload> <插件名字>"); return true;
            }
            try {
                PluginControl.pl(args[1], args[2], (Player)sender);
            } catch (InvalidPluginException e) {
                e.printStackTrace();
            }
        }
        //玩家限制
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //staff大区
            if (p.hasPermission("Tools.lanternmc.staff")) {
                //远程ssh
                if(args.length == 5) {
                    if(args[0].equals("ssh")) {
                        Connection lianjie = SshLinux.login(args[1], args[2], args[3]);
                        sender.sendMessage("§a" + SshLinux.execute(lianjie, args[4]));
                    }
                }

                if(args[0].equals("testEvent")) {
                    p.sendMessage(ChatColor.DARK_RED + "暂无测试内容");
                }

                //验证args[1]输的对吗
                if(args[0].equals("code")) {
                    codeCMD.Run(args, p);
                }

                //创建Code 管理需要手动 创建不然 下次登陆时判定你是对服务器的安全不负责任
                if(args[0].equals("createcode")) {
                    MapinHand.Run(p);
                }

                //Pliugins
                if(args[0].equals("list")) {
                    sender.sendMessage("§4没写到时候有时间一定写");
                }
                //freeze冻结
                if (args.length == 2) {
                    if (args[0].equals("freeze")) {
                        Player freezeyou = Bukkit.getPlayer(args[1]);
                        if (!players.contains(freezeyou.getUniqueId())) {
                            sender.sendMessage("§a目标 §e" + freezeyou.getName() + " §a已被成功冻结！");
                            players.add(freezeyou.getUniqueId());
                        } else {
                            sender.sendMessage("§a目标 §e" + freezeyou.getName() + " §a已被成功解冻！");
                            players.remove(freezeyou.getUniqueId());
                        }
                    }
                }
            } else {
                return true;
            }

            //权限指令
            //fly飞行 只要你有权限谁都可以福来
            if (p.hasPermission("Tools.lanternmc.fly")) {
                if(args.length == 2 && args[0].equals("fly")) {
                    p.setAllowFlight(true);
                } else if (args.length == 3 && args[0].equals("fly")) {
                    Player needfly = Bukkit.getPlayer(args[1]);
                    needfly.setAllowFlight(true);
                }
            }
        } else {
            SendMessageUtils.sendMessage(sender, "§b憨憨你没权限No Perms或者你TM就不是个人");return true;
        }
        return true;
    }

}
