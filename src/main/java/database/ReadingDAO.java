package database;

import javax.swing.text.DateFormatter;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ReadingDAO implements IReadingDAO {

    private final String JDBC_URL;
    private final String USERNAME;
    private final String PASSWORD;

    public ReadingDAO(String jdbcUrl, String username, String password) {
        JDBC_URL = jdbcUrl;
        USERNAME = username;
        PASSWORD = password;
    }


    @Override
    public boolean updateReading(Reading ir) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String updateCustomerQuery = "UPDATE readings SET DateOfReading = ?, KindOfMeter = ?, Metercount = ?, MeterId = ?, Substitute = ? WHERE uuid = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateCustomerQuery);
            updateStatement.setString(2, ir.getDateOfReading());
            updateStatement.setString(3, ir.getKindOfMeter());
            updateStatement.setString(4, ir.getMeterCount());
            updateStatement.setString(5, ir.getMeterId());
            updateStatement.setString(6, ir.getSubstitute());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void createTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createReadingsTable = "CREATE TABLE IF NOT EXISTS readings (" +
                    "uuid VARCHAR(36)," +
                    "dateOfReading DATE," +
                    "kindOfMeter varchar(50)," +
                    "meterCount double," +
                    "meterId varchar(50)," +
                    "substitute boolean" +
                    ")";
            PreparedStatement createTableStatement = connection.prepareStatement(createReadingsTable);
            createTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String removeAllTables = "DROP TABLE IF EXISTS readings";
            PreparedStatement removeTableStatement = connection.prepareStatement(removeAllTables);
            removeTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reading findByReadingUuid(UUID uuid) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectCustomerQuery = "SELECT * FROM readings WHERE uuid = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectCustomerQuery);
            selectStatement.setObject(1, uuid.toString());

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Reading reading = new Reading();
                reading.setUuid(uuid);
                reading.setDateOfReading(resultSet.getDate("dateOfReading").toLocalDate());
                reading.setMeterCount(resultSet.getDouble("metercount"));
                reading.setKindOfMeter(resultSet.getString("kindOfMeter"));
                reading.setMeterId(resultSet.getString("meterId"));
                reading.setSubstitute(resultSet.getBoolean("substitute"));
                return reading;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<? extends Reading> getAllReadings() {
        List<Reading> readings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectCustomersQuery = "SELECT * from readings;";
            PreparedStatement selectStatement = connection.prepareStatement(selectCustomersQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                Date DateOfReading = resultSet.getDate("dateOfReading");
                String KindOfMeter = resultSet.getString("kindOfMeter");
                Double Metercount = resultSet.getDouble("meterCount");
                String MeterId = resultSet.getString("meterId");
                Boolean Substitute = resultSet.getBoolean("substitute");
                //UUID uuid1 = UUID.fromString(uuid);
                Date myDate = new Date(2023-12-12);
                Reading reading = new Reading(myDate,  "placeholder", 22.56, "1", true);
                readings.add(reading);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readings;
    }


    @Override
    public boolean deleteReading(UUID uuid) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteCustomerQuery = "DELETE FROM readings WHERE uuid = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteCustomerQuery);
            deleteStatement.setObject(1, uuid.toString());
            deleteStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UUID insertReading(Reading reading) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String insertCustomerQuery = "INSERT INTO readings (uuid, dateOfReading, kindOfMeter, meterCount, meterId, substitute) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertCustomerQuery);
            UUID generatedUUID = UUID.randomUUID();
            insertStatement.setString(1, generatedUUID.toString());
            insertStatement.setString(2, reading.getDateOfReading());
            insertStatement.setString(3, reading.getKindOfMeter());
            insertStatement.setString(4, reading.getMeterCount());
            insertStatement.setString(5, reading.getMeterId());
            insertStatement.setString(6, reading.getSubstitute());
            insertStatement.executeUpdate();

            return generatedUUID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void truncate() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String truncateCustomersTable = "TRUNCATE TABLE readings";
            PreparedStatement truncateStatement = connection.prepareStatement(truncateCustomersTable);
            truncateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getUUID() {
        String uuid = null;
        String selectQuery = "SELECT uuid FROM readings WHERE id = ?";
        try (java.sql.Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, Integer.parseInt(uuid));

            // Rest des Codes bleibt unverändert
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uuid;
    }
}