package net.lanternmc.r1_8.BossBar.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class AccessUtil {
   public static Field setAccessible(Field f) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
      f.setAccessible(true);
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(f, f.getModifiers() & -17);
      return f;
   }

   public static Method setAccessible(Method m) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
      m.setAccessible(true);
      return m;
   }
}
