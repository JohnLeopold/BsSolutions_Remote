package customer;

import java.util.UUID;

public class Customer implements ICustomer {
    UUID uuid;

    private String firstname;
    private String lastname;

    public Customer(UUID uuid, String firstname, String lastname) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer() {

    }

    @Override
    public String getFirstname() {
        return this.firstname;
    }

    @Override
    public void setFirstname(String firstName) {
        this.firstname = firstName;

    }


    @Override
    public UUID getUuid() {
        return uuid;
    }


    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uuid=" + uuid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}