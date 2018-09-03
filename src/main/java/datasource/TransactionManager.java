package datasource;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class TransactionManager {

    private final Datasource datasource = Datasource.getInstance();
    private static TransactionManager instance;
    private final ThreadLocal<ConnectionHolder> currentConnection = new ThreadLocal<>();

    private final static Logger LOGGER = Logger.getLogger(TransactionManager.class);

    private TransactionManager() {

    }

    public static TransactionManager getInstance() {
        if(instance == null){
            instance = new TransactionManager();
        }
        return instance;
    }

    public ConnectionHolder getConnection() {
        if (currentConnection.get() == null) {
            return new ConnectionHolder(datasource.getConnection());
        } else {
            return provideConnection();
        }
    }

    public ConnectionHolder startTransaction() {
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
        currentConnection.set(new ConnectionHolder(datasource.getConnection()));
        currentConnection.get().setTransactionActive(true);
        try {
            currentConnection.get().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to DB", e);
        }
        return currentConnection.get();
    }
}
