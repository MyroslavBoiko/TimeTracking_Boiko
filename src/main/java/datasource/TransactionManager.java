package datasource;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class TransactionManager {

    private final Datasource DATA_SOURCE = Datasource.getInstance();
    private static TransactionManager ourInstance = new TransactionManager();
    private final ThreadLocal<ConnectionHolder> currentConnection = new ThreadLocal<>();

    private static Logger LOGGER = Logger.getLogger(TransactionManager.class);

    public static TransactionManager getInstance() {
        return ourInstance;
    }

    private TransactionManager() {
    }

    public ConnectionHolder getConnection() {

        if (currentConnection.get() == null) {
            return new ConnectionHolder(DATA_SOURCE.getConnection());
        } else {
            return provideConnection();
        }

    }

    public ConnectionHolder beginTransaction() {

        if (currentConnection.get() != null) {
            throw new IllegalStateException();
        }
        return provideConnection();
    }

    public void commit(boolean success) {

        if (currentConnection.get() == null) {
            throw new IllegalStateException();
        }

        currentConnection.get().setTransactionActive(false);

        try {
            if(success){
                currentConnection.get().commit();
            }else {
                LOGGER.error("Transaction is being rolled back");
                currentConnection.get().rollback();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to DB", e);
        } finally {
            try {
                currentConnection.get().setAutoCommit(true);
                currentConnection.get().close();
            } catch (SQLException e) {
                LOGGER.error("Can`t connect to DB", e);
            }
        }

        currentConnection.set(null);
    }

    private ConnectionHolder provideConnection() {

        if (currentConnection.get() != null) {
            return currentConnection.get();
        }

        currentConnection.set(new ConnectionHolder(DATA_SOURCE.getConnection()));
        currentConnection.get().setTransactionActive(true);

        try {
            currentConnection.get().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to DB", e);
        }

        return currentConnection.get();
    }
}
