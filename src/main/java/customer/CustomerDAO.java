package customer;

import database.Reading;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CustomerDAO implements ICustomerDAO {
    private final String JDBC_URL;
    private final String USERNAME;
    private final String PASSWORD;

    public CustomerDAO(String jdbcUrl, String username, String password) {
        this.JDBC_URL = jdbcUrl;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    @Override
    public UUID insertCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String insertCustomerQuery = "INSERT INTO customers (uuid, firstname, lastname) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertCustomerQuery);
            UUID generatedUUID = UUID.randomUUID();

            insertStatement.setString(1, generatedUUID.toString());
            insertStatement.setString(2, customer.getFirstname());
            insertStatement.setString(3, customer.getLastname());
            insertStatement.executeUpdate();

            return generatedUUID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ICustomer getCustomer(UUID uuid) {
        return null;
    }

    /*
    @Override
    public Customer findById(int id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectCustomerQuery = "SELECT id, uuid, firstname, lastname FROM customers WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectCustomerQuery);
            selectStatement.setInt(1, id);

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setUuid(UUID.fromString(resultSet.getString("uuid")));
                customer.setFirstname(resultSet.getString("firstname"));
                customer.setLastname(resultSet.getString("lastname"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     */

    @Override
    public Customer findByCustomerUuid(UUID uuid) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectCustomerQuery = "SELECT * FROM customers WHERE uuid = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectCustomerQuery);
            selectStatement.setObject(1, uuid.toString());


            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setUuid(UUID.fromString(resultSet.getString("uuid")));
                customer.setFirstname(resultSet.getString("firstname"));
                customer.setLastname(resultSet.getString("lastname"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





    @Override
    public void createTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                    "uuid VARCHAR(36)," +
                    "firstname VARCHAR(255)," +
                    "lastname VARCHAR(255)" +
                    ")";
            PreparedStatement createTableStatement = connection.prepareStatement(createCustomersTable);
            createTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String removeAllTables = "DROP TABLE IF EXISTS customers";
            PreparedStatement removeTableStatement = connection.prepareStatement(removeAllTables);
            removeTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public List<? extends Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectCustomersQuery = "SELECT uuid, firstname, lastname FROM customers";
            PreparedStatement selectStatement = connection.prepareStatement(selectCustomersQuery);
            ResultSet resultSet = selectStatement.executeQuery();


            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                UUID uuid1 = UUID.fromString(uuid);
                Customer customer = new Customer(uuid1, firstname, lastname);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }


    @Override
    public boolean updateCustomer(Customer ic) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String updateCustomerQuery = "UPDATE customers SET firstname = ?, lastname = ? WHERE uuid = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateCustomerQuery);
            updateStatement.setString(1, ic.getFirstname());
            updateStatement.setString(2, ic.getLastname());
            updateStatement.setObject(3, ic.getUuid());
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public String getFirstname(UUID uuid) {
        String firstname = null;
        String selectQuery = "SELECT firstname FROM customers WHERE uuid = ?";
        try (java.sql.Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setObject(1, uuid);

            // Rest des Codes bleibt unverändert
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return firstname;
    }

    @Override
    public void setFirstname(String firstName, UUID uuid) {
        String updateQuery = "UPDATE customers SET firstname = ? WHERE uuid = ?";

        try (java.sql.Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, firstName); // Setzen Sie den Vornamen
            preparedStatement.setObject(2, uuid);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLastname(UUID uuid) {
        String lastname = null;
        String selectQuery = "SELECT lastname FROM customers WHERE uuid = ?";

        try (java.sql.Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setObject(1, uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastname;
    }

    @Override
    public void setLastname(String lastName, UUID uuid) {
        String updateQuery = "UPDATE customers SET lastname = ? WHERE uuid = ?";

        try (java.sql.Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, lastName); // Setzen Sie den Nachnamen
            preparedStatement.setObject(2, uuid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteCustomer(UUID uuid) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteCustomerQuery = "DELETE FROM customers WHERE uuid = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteCustomerQuery);
            deleteStatement.setObject(1, uuid);
            deleteStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void truncate() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String truncateCustomersTable = "TRUNCATE TABLE customers";
            PreparedStatement truncateStatement = connection.prepareStatement(truncateCustomersTable);
            truncateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
