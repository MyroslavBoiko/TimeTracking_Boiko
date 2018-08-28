package utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCrypt {

    private static final Logger LOGGER = Logger.getLogger(PasswordCrypt.class);

    public static String encryptPassword(String password) {

        String returnValue;
        byte[] buf = password.getBytes();
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("NoSuchAlgorithmException in password encryption.");
        }
        algorithm.reset();
        algorithm.update(buf);
        byte[] digest = algorithm.digest();
        returnValue = "";
        for (int byteIdx = 0; byteIdx < digest.length; byteIdx++) {
            returnValue += Integer.toHexString(digest[byteIdx] + 256);
        }
        return returnValue;
    }
}
