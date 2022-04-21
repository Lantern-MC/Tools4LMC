package net.lanternmc.r1_8.BossBar.reflection;

import java.lang.reflect.Field;

public abstract class NMUClass {
   private static boolean initialized;
   public static Class gnu_trove_map_TIntObjectMap;
   public static Class gnu_trove_map_hash_TIntObjectHashMap;
   public static Class gnu_trove_impl_hash_THash;
   public static Class io_netty_channel_Channel;

   static {
      if (!initialized) {
         Field[] var3;
         int var2 = (var3 = NMUClass.class.getDeclaredFields()).length;

         for(int var1 = 0; var1 < var2; ++var1) {
            Field f = var3[var1];
            if (f.getType().equals(Class.class)) {
               try {
                  String name = f.getName().replace("_", ".");
                  if (Reflection.getVersion().contains("1_8")) {
                     f.set((Object)null, Class.forName(name));
                  } else {
                     f.set((Object)null, Class.forName("net.minecraft.util." + name));
                  }
               } catch (Exception var5) {
                  var5.printStackTrace();
               }
            }
         }
      }

   }
}
