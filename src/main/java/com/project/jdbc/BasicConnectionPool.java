package com.project.jdbc;

import com.project.properties.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicConnectionPool implements ConnectionPool {

    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_TIMEOUT = 5;
    private static BasicConnectionPool instance;
    private final List<Connection> usedConnections = new ArrayList<>();
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;

    private BasicConnectionPool() {
    }

    public static BasicConnectionPool getInstance() {
        if (instance == null) {
            ApplicationProperties appProperties = ApplicationProperties.getInstance();
            instance = new BasicConnectionPool();
            String url = String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", appProperties.getMySqlSchemaName());
            System.out.println(url);
            System.out.println(appProperties.getMySqlUserName());
            System.out.println(appProperties.getMySqlPassword());
            instance.createConnectionPool(url,
                    appProperties.getMySqlUserName(),
                    appProperties.getMySqlPassword());
            instance.setUrl(url);
            instance.setUser(appProperties.getMySqlUserName());
            instance.setPassword(appProperties.getMySqlPassword());
        }
        return instance;
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(BasicConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createConnectionPool(String url, String user, String password) {
        int INITIAL_POOL_SIZE = 10;
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));

        }
        this.connectionPool = pool;
    }

    @Override
    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(
                        createConnection(url, user, password));
            } else {
                Logger.getLogger(BasicConnectionPool.class.getName()).log(Level.SEVERE, null, "max limited exceeded");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        try {
            if (!connection.isValid(MAX_TIMEOUT)) {
                connection = createConnection(url, user, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BasicConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }

        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setUser(String user) {
        this.user = user;
    }

    private void setPassword(String password) {
        this.password = password;
    }


    @Override
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }
}