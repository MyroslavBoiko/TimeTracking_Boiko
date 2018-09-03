/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * @author Mirosha
 */
public class PagesJsp {

    private static final Logger LOGGER = Logger.getLogger(PagesJsp.class);

    private static PagesJsp instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "pages.pagescfg";

    public static final String INDEX = "INDEX";
    public static final String ERROR = "ERROR";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";
    public static final String USERS_DATA = "USERS_DATA";
    public static final String ACTIVITIES_DATA = "ACTIVITIES_DATA";
    public static final String REQUESTS_TO_ADD = "REQUESTS_TO_ADD";
    public static final String USER_ASSIGNMENTS = "USER_ASSIGNMENTS";
    public static final String SET_TIME = "SET_TIME";
    public static final String ACTIVE_ASSIGNMENTS = "ACTIVE_ASSIGNMENTS";
    public static final String ASSIGNMENTS_TO_DELETE = "ASSIGNMENTS_TO_DELETE";
    public static final String REQUESTS_TO_DELETE = "REQUESTS_TO_DELETE";
    public static final String SHOW_ACTIVITIES_ADMIN = "SHOW_ACTIVITIES_ADMIN";


    public static PagesJsp getInstance() {
        if (instance == null) {
            instance = new PagesJsp();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        LOGGER.info("Return the page from bundle.");
        return (String) resource.getObject(key);
    }
}
