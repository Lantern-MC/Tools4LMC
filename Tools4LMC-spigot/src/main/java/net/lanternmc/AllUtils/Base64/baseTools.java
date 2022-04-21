package net.lanternmc.AllUtils.Base64;

import java.io.IOException;

public class baseTools {

    /**
     * 编码
     *
     * @param content
     * @return
     */
    public static String encode(byte[] content) {
        return new sun.misc.BASE64Encoder().encode(content);
    }

    /**
     * 解码
     *
     * @param source
     * @return
     */
    public static byte[] decode(String source) {
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            return decoder.decodeBuffer(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
