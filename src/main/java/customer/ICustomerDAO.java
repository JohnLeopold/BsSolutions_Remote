package customer;

import database.IDAO;

import java.util.List;
import java.util.UUID;


public interface ICustomerDAO extends IDAO {

    UUID insertCustomer(Customer customer);


    ICustomer getCustomer(UUID uuid);

    void createTable();

    void removeTable();

    // CREATE


    // READ
    Customer findByCustomerUuid(UUID uuid);

    // READ
    List<? extends Customer> getAllCustomers();

    // UPDATE
    boolean updateCustomer(Customer ic);

    String getFirstname(UUID uuid);

    void setFirstname(String firstName, UUID uuid);

    String getLastname(UUID uuid);

    void setLastname(String lastName, UUID uuid);


    // DELETE
    boolean deleteCustomer(UUID uuid);

    // DELETE ALL
    void truncate();
}
