package database;

import customer.ICustomer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


public class Reading implements IReading {

    UUID uuid;
    private Date dateOfReading;
    private String kindOfMeter;
    private Double meterCount;
    private String meterId;
    private Boolean Substitute;

    public Reading (UUID uuid, Date dateOfReading, String kindOfMeter, Double meterCount, String meterId, Boolean Substitute){
        this.uuid = uuid;
        this.dateOfReading = dateOfReading;
        this.kindOfMeter = kindOfMeter;
        this.meterCount = meterCount;
        this.meterId = meterId;
        this.Substitute = Substitute;

    }

    public Reading (Date dateOfReading, String kindOfMeter, Double meterCount, String meterId, Boolean Substitute){
        this.dateOfReading = dateOfReading;
        this.kindOfMeter = kindOfMeter;
        this.meterCount = meterCount;
        this.meterId = meterId;
        this.Substitute = Substitute;

    }

    public Reading() {
        
    }


    @Override
    public String getComment() {
        return null;
    }

    @Override
    public void setComment(String comment) {

    }

    @Override
    public ICustomer getCustomer() {
        return null;
    }

    @Override
    public void setCustomer(ICustomer customer) {

    }

    @Override
    public String getDateOfReading() {
        return null;
    }

    @Override
    public void setDateOfReading(LocalDate dateOfReading) {

    }

    @Override
    public UUID getUuid() {
        return null;
    }

    @Override
    public void setUuid(UUID uuid) {

    }

    @Override
    public String getKindOfMeter() {
        return null;
    }

    @Override
    public String getMeterCount() {
        return null;
    }

    @Override
    public void setKindOfMeter(String kindOfMeter) {

    }

    @Override
    public void setMeterCount(Double meterCount) {

    }


    @Override
    public String getMeterId() {
        return null;
    }

    @Override
    public void setMeterId(String meterId) {

    }

    @Override
    public String getSubstitute() {
        return null;
    }

    @Override
    public void setSubstitute(Boolean substitute) {

    }
}
