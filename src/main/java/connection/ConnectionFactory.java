package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/magazin";
    private static final String USER = "root";
    private static final String PASS = "95Nicoleta";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     *
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * metoda care realizeaza connectarea la baza de date
     * @return
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
            System.out.println("Database successfully connected!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Nu se poate conecta la baza de date!");
            e.printStackTrace();
        }
        return connection;
    }

    /***
     *
     * @return
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * metoda care inchide conexiunea la baza de date
     * @param connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare la conexiune!");
            }
        }
    }

    /**
     * metoda care inchide conexiunea la baza de date
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare la conexiune!");
            }
        }
    }

    /**
     * metoda care inchide conexiunea la baza de date
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare la conexiune!");
            }
        }
    }
}


