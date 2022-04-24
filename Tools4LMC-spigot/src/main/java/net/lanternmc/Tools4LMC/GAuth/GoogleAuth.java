package net.lanternmc.Tools4LMC.GAuth;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.Player;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GoogleAuth {

    public static String getcodeURL(String user, String host, String secret) {
        String format = "https://api.qrserver.com/v1/create-qr-code/?data=otpauth://totp/" + user + "@"+ host+ "?secret=" + secret + "&size=128x128";
        return format;
    }
    /**
     * 生成网址图片 与存储数据库
     * @param p
     * @return
     */
    public static String genPngurl(Player p) {
        String miyao = generateSecretKey();
        String Pngurl = getcodeURL(p.getName(),
                "43.248.79.31", miyao);
        SQLSave.defaultAdminData(p.getName(), miyao);
        return Pngurl;
    }


    public static final int SECRET_SIZE = 10;
    public static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";
    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    // 生成密钥
    public static String generateSecretKey() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            String encodedKey = new String(bEncodedKey);
            return encodedKey;
        } catch (NoSuchAlgorithmException e) {
            // should never occur... configuration error
            System.out.print(SystemColor.red + "Error! 生成失败");
            return null;
        }
    }


    public boolean check_code(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -3; i <= 3; ++i) {
            long hash;
            try {
                hash = verify_code(decodedKey, t + i);
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            if (hash == code) {
                return true;
            }
        }
        return false;
    }


    public Boolean authcode(String codes, String savedSecret) {
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        return check_code(savedSecret, code, t);
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }


}
