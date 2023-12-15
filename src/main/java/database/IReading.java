package database;

import customer.ICustomer;

import java.time.LocalDate;
import java.util.UUID;


public interface IReading{

   String getComment();

   ICustomer getCustomer();

   String getDateOfReading();

   UUID getUuid();

   String getKindOfMeter();

   String getMeterCount();

   String getMeterId();

   String getSubstitute();

   void setComment(String comment);

   void setCustomer(ICustomer customer);

   void setDateOfReading(LocalDate dateOfReading);

   void setUuid(UUID uuid);

   void setKindOfMeter(String kindOfMeter);

   void setMeterCount(Double meterCount);

   void setMeterId(String meterId);

   void setSubstitute(Boolean substitute);
   

}

