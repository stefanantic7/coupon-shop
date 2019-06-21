package rs.raf.boot;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnectionPool {

    //TODO: Read configuration from config file.

    // JDBC Driver Name & Database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_DB_URL = "jdbc:mysql://192.168.10.10:3306/couponshop";

    // JDBC Database Credentials
    private static final String JDBC_USER = "homestead";
    private static final String JDBC_PASS = "secret";

    private static GenericObjectPool gPool = null;
    private static DataSource dataSource = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            // Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
            gPool = new GenericObjectPool();
            gPool.setMaxActive(5);

            // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
            ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

            // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
            PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
            dataSource = new PoolingDataSource(gPool);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static GenericObjectPool getConnectionPool() {
        return gPool;
    }

    // This Method Is Used To Print The Connection Pool Status
    private static void printDbStatus() {
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        printDbStatus();
        return connection;
    }
}
