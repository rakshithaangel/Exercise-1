import java.util.ArrayList;
import java.util.List;

class DatabaseConnectionPool {
    private static DatabaseConnectionPool instance;
    private List<Connection> availableConnections;
    private List<Connection> usedConnections;

    private DatabaseConnectionPool() {
        availableConnections = new ArrayList<>();
        usedConnections = new ArrayList<>();
       
        for (int i = 0; i < 5; i++) {
            availableConnections.add(new Connection());
        }
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        if (availableConnections.isEmpty()) {
            throw new RuntimeException("No available connections in the pool.");
        }
        Connection connection = availableConnections.remove(0);
        usedConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        availableConnections.add(connection);
    }
}

class Connection {
    
}

public class SingletonDemo {
    public static void main(String[] args) {
        DatabaseConnectionPool connectionPool1 = DatabaseConnectionPool.getInstance();
        DatabaseConnectionPool connectionPool2 = DatabaseConnectionPool.getInstance();

        
        Connection connection1 = connectionPool1.getConnection();
        Connection connection2 = connectionPool2.getConnection();

        connectionPool1.releaseConnection(connection1);
        connectionPool2.releaseConnection(connection2);
    }
}