package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Connection holder used for providing
 * the connection for users.
 *
 * @author Mirosha
 */
public class ConnectionHolder implements AutoCloseable {

    private final Connection connection;
    private boolean isTransactionActive;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        if (!isTransactionActive) {
            connection.close();
        }
    }

    public void setTransactionActive(boolean transactionActive) {
        isTransactionActive = transactionActive;
    }
}
