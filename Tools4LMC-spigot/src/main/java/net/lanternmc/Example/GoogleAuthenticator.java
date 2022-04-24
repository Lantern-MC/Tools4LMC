package net.lanternmc.Example;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GoogleAuthenticator {

    // taken from Google pam docs - we probably don't need to mess with these


//    public static Boolean authcode(String codes, String savedSecret) {
//        long code = Long.parseLong(codes);
//        long t = System.currentTimeMillis();
//        GoogleAuthenticator ga = new GoogleAuthenticator();
//        boolean r = ga.check_code(savedSecret, code, t);
//        return r;
//    }








//    public boolean check_code(String secret, long code, long timeMsec) {
//        Base32 codec = new Base32();
//        byte[] decodedKey = codec.decode(secret);
//        // convert unix msec time into a 30 second "window"
//        // this is per the TOTP spec (see the RFC for details)
//        long t = (timeMsec / 1000L) / 30L;
//        // Window is used to check codes generated in the near past.
//        // You can use this value to tune how far you're willing to go.
//        for (int i = -window_size; i <= window_size; ++i) {
//            long hash;
//            try {
//                hash = verify_code(decodedKey, t + i);
//            }catch (Exception e) {
//                // Yes, this is bad form - but
//                // the exceptions thrown would be rare and a static configuration problem
//                e.printStackTrace();
//                throw new RuntimeException(e.getMessage());
//                //return false;
//            }
//            if (hash == code) {
//                return true;
//            }
//        }
//        // The validation code is invalid.
//        return false;
//    }

    private static int verify_code (byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
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
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }




}