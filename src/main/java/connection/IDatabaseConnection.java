package connection;

import customer.Customer;
import customer.ICustomer;
import database.IReading;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public interface IDatabaseConnection {

    void createTables();

    void removeAllTables();

    
     //Wird ein Kunde gelöscht, sollen dessen Ablesungen trotzdem
     //gespeichert bleiben. Die Kunden-ID soll bei den entsprechenden
     //Ablesungen auf null gesetzt werden.
     
     //@param uuid
     //@return


    void truncateTables();

    void closeConnection() throws SQLException;


}
