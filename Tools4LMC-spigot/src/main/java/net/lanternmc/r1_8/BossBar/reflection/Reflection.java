package net.lanternmc.r1_8.BossBar.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;

public abstract class Reflection {
   public static String getVersion() {
      String name = Bukkit.getServer().getClass().getPackage().getName();
      String version = name.substring(name.lastIndexOf(46) + 1) + ".";
      return version;
   }

   public static Class getNMSClass(String className) {
      String fullName = "net.minecraft.server." + getVersion() + className;
      Class clazz = null;

      try {
         clazz = Class.forName(fullName);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return clazz;
   }

   public static Class getNMSClassWithException(String className) throws Exception {
      String fullName = "net.minecraft.server." + getVersion() + className;
      Class clazz = Class.forName(fullName);
      return clazz;
   }

   public static Class getOBCClass(String className) {
      String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
      Class clazz = null;

      try {
         clazz = Class.forName(fullName);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return clazz;
   }

   public static Object getHandle(Object obj) {
      try {
         return getMethod(obj.getClass(), "getHandle").invoke(obj);
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public static Field getField(Class clazz, String name) {
      try {
         Field field = clazz.getDeclaredField(name);
         field.setAccessible(true);
         return field;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static Method getMethod(Class clazz, String name, Class... args) {
      Method[] var6;
      int var5 = (var6 = clazz.getMethods()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         Method m = var6[var4];
         if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
            m.setAccessible(true);
            return m;
         }
      }

      return null;
   }

   public static boolean ClassListEqual(Class[] l1, Class[] l2) {
      boolean equal = true;
      if (l1.length != l2.length) {
         return false;
      } else {
         for(int i = 0; i < l1.length; ++i) {
            if (l1[i] != l2[i]) {
               equal = false;
               break;
            }
         }

         return equal;
      }
   }
}
