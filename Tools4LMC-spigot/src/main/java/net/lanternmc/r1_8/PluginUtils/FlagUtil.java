package net.lanternmc.r1_8.PluginUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Utilities for dealing with flags passed to commands.
 *
 * @author rylinaux
 */
public class FlagUtil {

    /**
     * 检查命令参数中是否存在标志，并将其从原始数组中删除。
     *
     * @param args the array of arguments.
     * @param flag the flag to check for.
     * @return true if the flag exists.
     */
    public static boolean hasFlag(String[] args, char flag) {

        List<String> list = new ArrayList(Arrays.asList(args));
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            if (it.next().equalsIgnoreCase("-" + flag)) {
                it.remove();
                args = list.toArray(args);
                return true;
            }
        }

        return false;

    }

}
