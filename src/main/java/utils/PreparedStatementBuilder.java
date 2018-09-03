package utils;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mirosha
 */
public class PreparedStatementBuilder {

    private static Logger LOGGER = Logger.getLogger(PreparedStatementBuilder.class);

    public static PreparedStatement setValues(PreparedStatement preparedStatement, Object... values) {
        for (int i = 0; i < values.length; i++) {
            try {
                preparedStatement.setObject(i + 1, values[i]);
            } catch (SQLException e) {
                LOGGER.error("Exception in PreparedStatementBuilder");
            }
        }
        return preparedStatement;
    }
}
