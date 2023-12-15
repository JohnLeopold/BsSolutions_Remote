package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDAOTest {

    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/bssolutions";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";

    Reading readingWithId = new Reading(UUID.fromString("7cf48e30-ea4b-48fd-8ae0-6e44f7c61fe2"),
            Date.valueOf(LocalDate.parse("2023-10-20")), "placeholder", 22.56, "1", true);

    Reading readingWithoutId = new Reading(UUID.randomUUID(), Date.valueOf(LocalDate.parse("2023-10-20")),
            "placeholder", 22.56, "1", true);
    ReadingDAO readingDAO = new ReadingDAO(JDBC_URL, USERNAME, PASSWORD);

    @BeforeEach
    public void setUp() throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createReadingsTable = "CREATE TABLE IF NOT EXISTS readings (" +
                    "uuid VARCHAR(36)," +
                    "DateOfReading DATE," +
                    "KindOfMeter varchar(50)," +
                    "MeterCount double," +
                    "MeterId varchar(50)," +
                    "Substitute boolean" +
                    ")";
            connection.createStatement().executeUpdate(createReadingsTable);
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS readings");
        }
    }


    @Test
    public void testUpdateReading() {
        UUID uuid = readingDAO.insertReading(readingWithoutId);
        Reading updatedReading = new Reading(uuid, Date.valueOf(LocalDate.parse("2023-11-10")),
                "newMeter", 30.0, "2", false);
        readingDAO.updateReading(updatedReading);
        Reading retrievedReading = readingDAO.findByReadingUuid(uuid);

        assertEquals(updatedReading.getDateOfReading(), retrievedReading.getDateOfReading());
        assertEquals(updatedReading.getKindOfMeter(), retrievedReading.getKindOfMeter());
        assertEquals(updatedReading.getMeterCount(), retrievedReading.getMeterCount());
        assertEquals(updatedReading.getMeterId(), retrievedReading.getMeterId());
    }


    @Test
    public void testCreateTable() {
        readingDAO.createTable();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            assertTrue(connection.createStatement().execute("SELECT * FROM readings"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*

    @Test
    void testRemoveTable() {
        readingDAO.removeTable();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            assertFalse(connection.createStatement().execute("SELECT * FROM readings"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    @Test
    void testFindByReadingUuid() {
        UUID uuid = readingDAO.insertReading(readingWithId);
        Reading retrievedReading = readingDAO.findByReadingUuid(uuid);

        assertEquals(readingWithId.getDateOfReading(), retrievedReading.getDateOfReading());
        assertEquals(readingWithId.getKindOfMeter(), retrievedReading.getKindOfMeter());
        assertEquals(readingWithId.getMeterCount(), retrievedReading.getMeterCount());
        assertEquals(readingWithId.getMeterId(), retrievedReading.getMeterId());
    }

    @Test
    void testGetAllReadings() {
        UUID uuid1 = readingDAO.insertReading(readingWithId);
        UUID uuid2 = readingDAO.insertReading(readingWithoutId);

        List<Reading> readings = (List<Reading>) readingDAO.getAllReadings();

        assertNotNull(readings, "Reading list should not be null");
        assertEquals(2, readings.size(), "Unexpected number of readings");

        Reading retrievedReading1 = readings.get(0);
        assertEquals(readingWithId.getDateOfReading(), retrievedReading1.getDateOfReading());
        assertEquals(readingWithId.getKindOfMeter(), retrievedReading1.getKindOfMeter());
        assertEquals(readingWithId.getMeterCount(), retrievedReading1.getMeterCount());
        assertEquals(readingWithId.getMeterId(), retrievedReading1.getMeterId());

        Reading retrievedReading2 = readings.get(1);
        assertEquals(readingWithoutId.getDateOfReading(), retrievedReading2.getDateOfReading());
        assertEquals(readingWithoutId.getKindOfMeter(), retrievedReading2.getKindOfMeter());
        assertEquals(readingWithoutId.getMeterCount(), retrievedReading2.getMeterCount());
        assertEquals(readingWithoutId.getMeterId(), retrievedReading2.getMeterId());
    }

    @Test
    void testDeleteReading() {
        UUID uuid = readingDAO.insertReading(readingWithoutId);
        assertTrue(readingDAO.deleteReading(uuid));

        Reading deletedReading = readingDAO.findByReadingUuid(uuid);
        assertNull(deletedReading, "Deleted reading should be null");
    }


    @Test
    void testGetUUID() {
        // You need to implement the logic for getUUID() and test accordingly
    }
}