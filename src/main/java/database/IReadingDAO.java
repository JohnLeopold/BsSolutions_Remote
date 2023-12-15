package database;

import java.util.List;
import java.util.UUID;


public interface IReadingDAO<T> extends IDAO {

    boolean updateReading(Reading re);

    Reading findByReadingUuid(UUID uuid);

    List<? extends Reading> getAllReadings();

    boolean deleteReading(UUID uuid);

    UUID insertReading(Reading reading);

    String getUUID();



}
