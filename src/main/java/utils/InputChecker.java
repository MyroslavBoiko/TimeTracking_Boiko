package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mirosha
 */
public class InputChecker {

    public static boolean checkNumber(String value) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^[\\w._-]+@[\\w.-]+\\.[a-z]{2,4}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean checkName(String name) {
        Pattern p = Pattern.compile("^\\p{L}+$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean checkPassword(String password) {
        Pattern p = Pattern.compile("^[\\p{L}0-9]{6,}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
