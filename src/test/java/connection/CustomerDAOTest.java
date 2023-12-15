package connection;

import customer.Customer;
import customer.CustomerDAO;
import customer.ICustomer;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;
import static connection.DatabaseConnection.*;

class CustomerDAOTest {


    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/bssolutions";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";


    Customer customerWithId = new Customer(UUID.fromString("7cf48e30-ea4b-48fd-8ae0-6e44f7c61fe2"), "Johnny", "Test");
    Customer customerWithIdNew = new Customer(UUID.fromString("7cf48e30-ea4b-48fd-8ae0-6e44f7c61fe2"), "John", "Tester");
    Customer customerWithoutId = new Customer("Johnny", "Test");
    Customer customerWithoutIdNew = new Customer("John", "Tester");

    CustomerDAO customerDAO = new CustomerDAO(JDBC_URL, USERNAME, PASSWORD);



    @BeforeEach
    public void setUp() throws SQLException {
        // Verbindung zur eingebetteten Datenbank herstellen und Tabelle erstellen
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                    "uuid VARCHAR(36)," +
                    "firstname VARCHAR(255)," +
                    "lastname VARCHAR(255)" +
                    ")";
            connection.createStatement().executeUpdate(createCustomersTable);
        }
    }

    /*
    @AfterEach
    public void tearDown() throws SQLException {
        // Verbindung zur eingebetteten Datenbank schließen
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            connection.createStatement().executeUpdate("DROP TABLE customers");
        }
    }

     */



    @Test
    public void testInsertCustomer() {
        UUID uuid = customerDAO.insertCustomer(customerWithoutId);

        assertEquals(customerWithoutId.getFirstname(),customerDAO.findByCustomerUuid(uuid).getFirstname());
        assertEquals(customerWithoutId.getLastname(),customerDAO.findByCustomerUuid(uuid).getLastname());

    }

    @Test
    public void testCreateTable() {
        CustomerDAO customerDAO = new CustomerDAO(JDBC_URL, USERNAME, PASSWORD);
        customerDAO.createTable();

        // Überprüfen, ob die Tabelle erfolgreich erstellt wurde
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            assertTrue(connection.createStatement().execute("SELECT * FROM customers"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void testRemoveTable() {
        CustomerDAO customerDAO = new CustomerDAO(JDBC_URL, USERNAME, PASSWORD);
        customerDAO.removeTable();

        // Überprüfen, ob die Tabelle erfolgreich gelöscht wurde
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            assertFalse(connection.createStatement().execute("SELECT * FROM customers"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */

    @Test
    void updateCustomer() {

        UUID uuid = customerDAO.insertCustomer(customerWithoutId);
        Customer customer = new Customer(uuid,customerWithoutIdNew.getFirstname(),customerWithoutIdNew.getLastname());
        customerDAO.updateCustomer(customer);


        assertEquals(customerDAO.findByCustomerUuid(uuid).getFirstname(), customer.getFirstname());
        assertEquals(customerDAO.findByCustomerUuid(uuid).getLastname(), customer.getLastname());
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void findById() {

        UUID uuid = customerDAO.insertCustomer(customerWithId);
        Customer customer = new Customer(uuid, customerWithId.getFirstname(), customerWithId.getLastname());

        customerDAO.findByCustomerUuid(uuid);

        System.out.println(customerWithId.getUuid());
        System.out.println(customerDAO.findByCustomerUuid(uuid).getUuid());

        assertEquals(customer.getUuid(), customerDAO.findByCustomerUuid(uuid).getUuid());
        assertEquals(customer.getFirstname(), customerDAO.findByCustomerUuid(uuid).getFirstname());
        assertEquals(customer.getLastname(), customerDAO.findByCustomerUuid(uuid).getLastname());


        /*
        UUID uuid = customerDAO.insertCustomer(customerWithId);

        customerDAO.findByCustomerUuid(uuid);

        System.out.println(customerWithId.getUuid());
        System.out.println(customerDAO.findByCustomerUuid(uuid).getUuid());

        assertEquals(customerWithId.getUuid(), customerDAO.findByCustomerUuid(uuid).getUuid());
        assertEquals(customerWithId.getFirstname(), customerDAO.findByCustomerUuid(uuid).getFirstname());
        assertEquals(customerWithId.getLastname(), customerDAO.findByCustomerUuid(uuid).getLastname());

         */

    }
}