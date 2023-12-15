package connection;

import customer.Customer;
import customer.CustomerDAO;
import customer.ICustomer;
import database.IReading;
import database.Reading;
import database.ReadingDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class DatabaseConnection implements IDatabaseConnection {
    public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/bssolutions";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";
    CustomerDAO customerDAO = new CustomerDAO(JDBC_URL, USERNAME, PASSWORD);
    ReadingDAO readingDAO = new ReadingDAO(JDBC_URL, USERNAME, PASSWORD);
    Customer customer = new Customer();
    Reading reading = new Reading();

    @Override
    public void createTables() {
        customerDAO.createTable();
        readingDAO.createTable();
    }


    @Override
    public void removeAllTables() {
        customerDAO.removeTable();
        readingDAO.removeTable();
    }


    @Override
    public void truncateTables() {
        customerDAO.truncate();
        readingDAO.truncate();
    }

    @Override
    public void closeConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        connection.close();
    }

}
