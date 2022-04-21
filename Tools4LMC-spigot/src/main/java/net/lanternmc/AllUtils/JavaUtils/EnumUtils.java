package net.lanternmc.AllUtils.JavaUtils;

import java.util.ArrayList;
import java.util.Locale;

public class EnumUtils {
    public static <T extends Enum<T>> T getEnumFromString(Class<T> paramClass, String paramString) {
        if (paramClass != null && paramString != null)
            try {
                return Enum.valueOf(paramClass, paramString.trim().toUpperCase(Locale.ENGLISH));
            } catch (IllegalArgumentException illegalArgumentException) {}
        return null;
    }

    public static <T extends Enum<T>> T getEnumFromStringCaseSensitive(Class<T> paramClass, String paramString) {
        if (paramClass != null && paramString != null)
            try {
                return Enum.valueOf(paramClass, paramString);
            } catch (IllegalArgumentException illegalArgumentException) {} 
        return null;
    }
    
    public static String[] getStringArrayFromEnum(Class<? extends Enum<?>> paramClass) {
        ArrayList<String> arrayList = new ArrayList();
        for (Enum enum_ : paramClass.getEnumConstants())
            arrayList.add(enum_.name()); 
        return arrayList.toArray(new String[arrayList.size()]);
    }
}