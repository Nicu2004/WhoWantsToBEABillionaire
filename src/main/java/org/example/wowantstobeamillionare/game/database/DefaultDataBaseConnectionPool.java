package org.example.wowantstobeamillionare.game.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;

import static org.example.wowantstobeamillionare.game.addon.Log4j.logger;


public final class DefaultDataBaseConnectionPool implements ConnectionPool {

    private String url = "jdbc:postgresql://localhost/test";
    private String user = "postgres";
    private String password = "admin";
    private List<Connection> connectionPool;
    private List<Connection> usedConnections= new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 5;


    private DefaultDataBaseConnectionPool(List<Connection> pool) {
        this.connectionPool = pool;
    }
    public static DefaultDataBaseConnectionPool create() throws SQLException {

        List<Connection> pool = new ArrayList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++)
            {
                pool.add(createConnection());
            }
            return new DefaultDataBaseConnectionPool(pool);
        }

    @Override
    public Connection getConnection() throws SQLException {
        if(connectionPool.isEmpty()){
            logger.error("Connection pool is empty");
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
    private static Connection createConnection()throws SQLException
    {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        return DriverManager.getConnection(url , props);
    }
    public int getSize()
        {
        return connectionPool.size()+usedConnections.size();
        }
    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

