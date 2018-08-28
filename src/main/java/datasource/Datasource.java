package datasource;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Datasource {

    private static final Logger LOGGER = Logger.getLogger(Datasource.class);

    private static DataSource dataSource;

    static{
        try{
            /**
             * Get initial context that has references to all configurations and
             * resources defined for this web application.
             */
            Context initContext = new InitialContext();
            /**
             * Get Context object for all environment naming (JNDI), such as
             * Resources configured for this web application.
             */
            Context envContext = (Context) initContext.lookup("java:comp/env");
            /**
             * Get the data source for the MySQL to request a connection.
             */
            dataSource = (DataSource) envContext.lookup("jdbc/time_tracking");
        }catch (NamingException e){
            LOGGER.error("Naming exception in Datasource");
        }
    }

    private Datasource(){}

    public static DataSource getDataSource() {
        LOGGER.info("Return the object of DataSource class.");
        return dataSource;
    }
}
