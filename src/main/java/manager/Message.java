/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class Message {

    private static final Logger LOGGER = Logger.getLogger(Message.class);

    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String LOGIN_OR_PASS_ERROR = "loginOrPassError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String FIRST_NAME_ERROR = "firstNameError";
    public static final String LAST_NAME_ERROR = "lastNameError";
    public static final String PASSWORD_ERROR = "passwordError";
    public static final String USED_EMAIL_ERROR = "usedEmailError";
    public static final String USED_ACTIVITY_ERROR = "usedActivityError";
    public static final String INPUT_NUMBER_ERROR = "inputNumberError";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
