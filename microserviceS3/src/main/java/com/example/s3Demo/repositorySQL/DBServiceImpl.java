package com.example.s3Demo.repositorySQL;

import com.example.s3Demo.S3.exceptions.DB_ColumnNotFoundException;
import com.example.s3Demo.S3.exceptions.DB_FileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.util.regex.Pattern;

/**
 * Implementation of {@link DBService} that provides database operations using {@link JdbcTemplate}.
 */
@Service
@Slf4j
public class DBServiceImpl implements DBService {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new DBServiceImpl with the specified JdbcTemplate.
     * @param jdbcTemplate the JdbcTemplate to use for database operations.
     */
    @Autowired
    public DBServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean updateFileDB(String id, String tableName, String columnName, String fileName) throws DB_FileNotFoundException {

        // If column exists, update the file in the specified column
        String sql = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, fileName, id);
        if (affectedRows > 0) {
            log.info("DB -> {} insert successful in DB. TableName: {}, ColumnName: {}, Id: {}", fileName, tableName, columnName, id);
            return true;
        } else {
            log.error("DB -> Unable to upload {} successful in DB. TableName: {}, ColumnName: {}, Id: {}", fileName, tableName, columnName, id);
            throw new DB_FileNotFoundException("Unable to upload " + fileName + " in DB.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteFile(String id, String tableName, String columnName, String valueToFind) throws DB_FileNotFoundException {

        // Cancella il valore dalla colonna corrente
        String updateSql = "UPDATE " + tableName + " SET " + columnName + " = NULL WHERE id = ? AND " + columnName + " = ?";
        int updatedRows = jdbcTemplate.update(updateSql, id, valueToFind);

        if (updatedRows == 0) {
            log.error(valueToFind + " was not found in the database for deletion");
            throw new DB_FileNotFoundException(valueToFind + " was not found in the database for deletion");
        }

        // Calcola il nome della colonna successiva
        int nextColumnNumber = Integer.parseInt(columnName.replaceAll("\\D+", "")) + 1;
        String nextColumnName = "photo" + nextColumnNumber;

        // Ciclo while per la riorganizzazione
        while (true) {
            // Verifica se la colonna successiva esiste
            String columnExistsSql = "SELECT COUNT(*) FROM information_schema.columns WHERE table_name = ? AND column_name = ?";
            int columnCount = jdbcTemplate.queryForObject(columnExistsSql, new Object[]{tableName, nextColumnName}, Integer.class);

            // Se la colonna successiva non esiste, esci dal ciclo
            if (columnCount == 0) {
                break;
            }

            // Verifica se esiste un valore non-null nella colonna successiva
            String checkSql = "SELECT COALESCE(" + nextColumnName + ", 'nextColumnNotFound') " +
                    "FROM " + tableName + " WHERE id = ? ";

            String nextColumnValue = jdbcTemplate.queryForObject(checkSql, new Object[]{id}, String.class);

            // Se non è stato trovato alcun valore valido, esci dal ciclo
            if ("nextColumnNotFound".equals(nextColumnValue) || nextColumnValue == null) {
                break;
            }

            // Copia il valore dalla colonna successiva a quella corrente
            String shiftSql = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE id = ?";
            jdbcTemplate.update(shiftSql, nextColumnValue, id);

            // Imposta il valore della colonna successiva a NULL
            String nullifySql = "UPDATE " + tableName + " SET " + nextColumnName + " = NULL WHERE id = ?";
            jdbcTemplate.update(nullifySql, id);

            // Aggiorna il nome della colonna corrente
            columnName = nextColumnName;

            // Calcola il nome della prossima colonna successiva
            nextColumnNumber++;
            nextColumnName = "photo" + nextColumnNumber;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void checkUserIdAtCardId(String userId, String userType, String cardId, String tableName) throws DB_ColumnNotFoundException {
        String sql = "SELECT " + "profile_" + userType.toLowerCase() + "_id" + " FROM " + tableName + " WHERE id = ?";

        String targetId = jdbcTemplate.queryForObject(sql, new Object[]{cardId}, String.class); // potrebbe ritornare EmptyResultDataAccessException !

        assert targetId != null;
        if (!targetId.equals(userId)) {
            log.error("User ID " + userId + " not found inside " + tableName);
            throw new DB_ColumnNotFoundException("This card is not your!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String findFirstEmptyPositionInTable(String id, String tableName, String fileType, boolean throwExceptionIfFull) throws DB_ColumnNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        log.info(id);
        log.info(tableName);
        log.info(fileType);
        log.info(throwExceptionIfFull + "");

        String result;

        try {
            result = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    // Check that the column name begins with the fileType followed by a number
                    if (columnName.matches("^" + Pattern.quote(fileType) + "\\d+$")) {
                        String value = rs.getString(columnName);
                        if (value == null || value.isEmpty()) {
                            // Extract the number from the column and return
                            return columnName.substring(fileType.length());
                        }
                    }
                }
                return "full"; // Indicate that no empty column was found
            });
        } catch (EmptyResultDataAccessException e) {
            throw new DB_ColumnNotFoundException("No profile found with the given ID: " + id);
        } catch (Exception e) {
            throw new DB_ColumnNotFoundException("An error occurred while searching for an empty " + fileType + " column.");
        }

        log.info("result" + result);
        assert result != null;
        if (result.equals("full")) {
            if (throwExceptionIfFull) {
                throw new DB_ColumnNotFoundException("The database is full for the " + fileType + " file, or no empty " + fileType + " column exists.");
            } else {
                return "full"; // All columns are full
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String checkIfFileExist(String id, String tableName, String fileType, String value) throws DB_FileNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    // Verify that the column name matches the fileType followed by a number
                    if (columnName.matches("^" + Pattern.quote(fileType) + "\\d+$") && value.equals(rs.getString(columnName))) {
                        // Extract the number from the column and return it
                        return columnName.substring(fileType.length());
                    }
                }
                // If no match is found, throw an exception
                // Throw a RuntimeException to bypass lambda expressions restrictions
                throw new RuntimeException(new DB_FileNotFoundException("No match found for " + fileType + " with value " + value));
            });
        } catch (RuntimeException e) {
            // Check if the cause is DB_FileNotFoundException, and if so, re-throw it
            if (e.getCause() instanceof DB_FileNotFoundException) {
                throw (DB_FileNotFoundException) e.getCause();
            } else {
                throw e; // Throw more unchecked exceptions
            }
        }
    }

}