package net.lanternmc.AllUtils.JavaUtils;

public class CryptUtils {
    public static String bytesToHexString(byte[] paramArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (paramArrayOfbyte == null || paramArrayOfbyte.length <= 0)
            return null; 
        for (byte b = 0; b < paramArrayOfbyte.length; b++) {
            int i = paramArrayOfbyte[b] & 0xFF;
            String str = Integer.toHexString(i);
            if (str.length() < 2)
                stringBuilder.append(0); 
            stringBuilder.append(str);
        } 
        return stringBuilder.toString();
    }
    
    public static byte[] hexStringToBytes(String paramString) {
        if (paramString == null || paramString.equals(""))
            return null; 
        paramString = paramString.toUpperCase();
        int i = paramString.length() / 2;
        char[] arrayOfChar = paramString.toCharArray();
        byte[] arrayOfByte = new byte[i];
        for (byte b = 0; b < i; b++) {
            int j = b * 2;
            arrayOfByte[b] = (byte)(charToByte(arrayOfChar[j]) << 4 | charToByte(arrayOfChar[j + 1]));
        } 
        return arrayOfByte;
    }
    
    private static byte charToByte(char paramChar) {
        return (byte)"0123456789ABCDEF".indexOf(paramChar);
    }
}
