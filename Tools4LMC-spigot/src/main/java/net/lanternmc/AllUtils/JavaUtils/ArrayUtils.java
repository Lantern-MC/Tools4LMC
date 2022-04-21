package net.lanternmc.AllUtils.JavaUtils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public static String[] removeAt(String[] paramArrayOfString, int paramInt) {
        List<String> list = Arrays.asList(paramArrayOfString);
        list.remove(paramInt);
        return list.<String>toArray(new String[0]);
    }
}