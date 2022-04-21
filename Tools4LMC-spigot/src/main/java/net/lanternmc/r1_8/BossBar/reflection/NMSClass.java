package net.lanternmc.r1_8.BossBar.reflection;

import java.lang.reflect.Field;

public abstract class NMSClass {
   private static boolean initialized;
   public static Class Entity;
   public static Class EntityLiving;
   public static Class EntityInsentient;
   public static Class EntityAgeable;
   public static Class EntityHorse;
   public static Class EntityArmorStand;
   public static Class EntityWither;
   public static Class EntityWitherSkull;
   public static Class EntitySlime;
   public static Class World;
   public static Class PacketPlayOutSpawnEntityLiving;
   public static Class PacketPlayOutSpawnEntity;
   public static Class PacketPlayOutEntityDestroy;
   public static Class PacketPlayOutAttachEntity;
   public static Class PacketPlayOutEntityTeleport;
   public static Class PacketPlayOutEntityMetadata;
   public static Class DataWatcher;
   public static Class WatchableObject;
   public static Class ItemStack;
   public static Class ChunkCoordinates;
   public static Class BlockPosition;
   public static Class Vector3f;
   public static Class EnumEntityUseAction;

   static {
      if (!initialized) {
         Field[] var3;
         int var2 = (var3 = NMSClass.class.getDeclaredFields()).length;

         for(int var1 = 0; var1 < var2; ++var1) {
            Field f = var3[var1];
            if (f.getType().equals(Class.class)) {
               try {
                  f.set((Object)null, Reflection.getNMSClassWithException(f.getName()));
               } catch (Exception var7) {
                  if (f.getName().equals("WatchableObject")) {
                     try {
                        f.set((Object)null, Reflection.getNMSClassWithException("DataWatcher$WatchableObject"));
                     } catch (Exception var6) {
                        var6.printStackTrace();
                     }
                  }
               }
            }
         }
      }

   }
}
