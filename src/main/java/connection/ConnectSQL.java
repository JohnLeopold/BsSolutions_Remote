package connection;

import customer.Customer;
import customer.CustomerDAO;
import customer.ICustomer;
import database.Reading;
import database.ReadingDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

import static connection.DatabaseConnection.*;


public class ConnectSQL {
    public static void main(String[] args) throws SQLException {
        /*
        DatabaseConnection dbCon = new DatabaseConnection();

        ReadingDAO readingDAO = new ReadingDAO(JDBC_URL, USERNAME, PASSWORD);

        int date = 2023-12-12;
        Date date1 = Date.valueOf(String.valueOf(date));
        Reading r1 = new Reading(date1, "TestKindOfMeter", 12.12, "1", true);

        dbCon.removeAllTables();
        dbCon.createTables();

        readingDAO.insertReading(r1);
         */

        DatabaseConnection dbCon = new DatabaseConnection();
        CustomerDAO customerDAO = new CustomerDAO(JDBC_URL, USERNAME, PASSWORD);

        System.out.println(dbCon);

        Customer c1 = new Customer("John", "Leo");

        dbCon.removeAllTables();
        dbCon.createTables();

        customerDAO.insertCustomer(c1);

    }
}