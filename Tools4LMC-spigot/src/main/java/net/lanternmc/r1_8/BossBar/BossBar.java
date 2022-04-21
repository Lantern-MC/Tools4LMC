package net.lanternmc.r1_8.BossBar;

import net.lanternmc.r1_8.BossBar.reflection.ClassBuilder;
import net.lanternmc.r1_8.BossBar.reflection.NMSClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class BossBar extends BukkitRunnable {
   protected static int ENTITY_DISTANCE = 32;
   protected final int ID = (new Random()).nextInt();
   protected final Player receiver;
   protected String message;
   protected float health;
   protected float healthMinus;
   protected float minHealth = 1.0F;
   protected Location location;
   protected World world;
   protected boolean visible = false;
   protected Object dataWatcher;

   protected BossBar(Plugin instance, Player player, String message, float percentage, int timeout, float minHealth) {
      this.receiver = player;
      this.message = message;
      this.health = percentage / 100.0F * this.getMaxHealth();
      this.minHealth = minHealth;
      this.world = player.getWorld();
      this.location = this.makeLocation(player.getLocation());
      if (percentage <= minHealth) {
         BossBarAPI.removeBar(player);
      }

      if (timeout > 0) {
         this.healthMinus = this.getMaxHealth() / (float)timeout;
         this.runTaskTimer(instance, 10, 10);
      }

   }

   protected Location makeLocation(Location base) {
      return base.getDirection().multiply(ENTITY_DISTANCE).add(base.toVector()).toLocation(this.world);
   }

   public float getMaxHealth() {
      return 300.0F;
   }

   public void setHealth(float percentage) {
      this.health = percentage / 100.0F * this.getMaxHealth();
      if (this.health <= this.minHealth) {
         BossBarAPI.removeBar(this.receiver);
      } else {
         this.sendMetadata();
      }

   }

   public float getHealth() {
      return this.health;
   }

   public void setMessage(String message) {
      this.message = message;
      if (this.isVisible()) {
         this.sendMetadata();
      }

   }

   public String getMessage() {
      return this.message;
   }

   public Location getLocation() {
      return this.location;
   }

   public void run() {
      this.health -= this.healthMinus;
      if (this.health <= this.minHealth) {
         BossBarAPI.removeBar(this.receiver);
      } else {
         this.sendMetadata();
      }

   }

   public void setVisible(boolean flag) {
      if (flag != this.visible) {
         if (flag) {
            this.spawn();
         } else {
            this.destroy();
         }
      }

   }

   public boolean isVisible() {
      return this.visible;
   }

   public void updateMovement() {
      if (this.visible) {
         this.location = this.makeLocation(this.receiver.getLocation());

         try {
            Object packet = ClassBuilder.buildTeleportPacket(this.ID, this.getLocation(), false, false);
            BossBarAPI.sendPacket(this.receiver, packet);
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

   }

   protected void updateDataWatcher() {
      if (this.dataWatcher == null) {
         try {
            this.dataWatcher = ClassBuilder.buildDataWatcher((Object)null);
            ClassBuilder.setDataWatcherValue(this.dataWatcher, 17, 0);
            ClassBuilder.setDataWatcherValue(this.dataWatcher, 18, 0);
            ClassBuilder.setDataWatcherValue(this.dataWatcher, 19, 0);
            ClassBuilder.setDataWatcherValue(this.dataWatcher, 20, 1000);
            ClassBuilder.setDataWatcherValue(this.dataWatcher, 0, (byte)32);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      try {
         ClassBuilder.setDataWatcherValue(this.dataWatcher, 6, this.health);
         ClassBuilder.setDataWatcherValue(this.dataWatcher, 11, (byte)1);
         ClassBuilder.setDataWatcherValue(this.dataWatcher, 3, (byte)1);
         ClassBuilder.setDataWatcherValue(this.dataWatcher, 10, this.message);
         ClassBuilder.setDataWatcherValue(this.dataWatcher, 2, this.message);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   protected void sendMetadata() {
      this.updateDataWatcher();

      try {
         Object metaPacket = ClassBuilder.buildNameMetadataPacket(this.ID, this.dataWatcher, 2, 3, this.message);
         BossBarAPI.sendPacket(this.receiver, metaPacket);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   protected void spawn() {
      try {
         this.updateMovement();
         this.updateDataWatcher();
         Object packet = ClassBuilder.buildWitherSpawnPacket(this.ID, this.getLocation(), this.dataWatcher);
         BossBarAPI.sendPacket(this.receiver, packet);
         this.visible = true;
         this.sendMetadata();
         this.updateMovement();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   protected void destroy() {
      try {
         this.cancel();
      } catch (IllegalStateException var3) {
      }

      try {
         Object packet = NMSClass.PacketPlayOutEntityDestroy.getConstructor(int[].class).newInstance(new int[]{this.ID});
         BossBarAPI.sendPacket(this.receiver, packet);
         this.visible = false;
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }
}
