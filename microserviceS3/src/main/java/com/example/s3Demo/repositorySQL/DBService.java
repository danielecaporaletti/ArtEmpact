package com.example.s3Demo.repositorySQL;

import com.example.s3Demo.S3.exceptions.DB_ColumnNotFoundException;
import com.example.s3Demo.S3.exceptions.DB_FileNotFoundException;

/**
 * Interface for database operations related to file management in an SQL database.
 * This interface defines methods for updating, deleting, and checking files within the database.
 */
public interface DBService {

    /**
     * Updates the file name in the database for a given table, ID, and column.
     *
     * @param tableName  the name of the table in the database
     * @param id         the ID of the record to update
     * @param columnName the name of the column to update
     * @param fileName   the new file name to be updated in the column
     * @return true if the update operation was successful, false otherwise
     * @throws DB_FileNotFoundException if the file to be updated is not found
     */
    boolean updateFileDB(String tableName, String id, String columnName, String fileName) throws DB_FileNotFoundException;

    /**
     * Deletes a file entry from the database based on the provided parameters.
     *
     * @param id          the ID of the record from which the file should be deleted
     * @param tableName   the name of the table in the database
     * @param columnName  the name of the column from which the file should be deleted
     * @param valueToFind the value used to find the record for deletion
     * @throws DB_FileNotFoundException if the file to be deleted is not found
     */
    void deleteFile(String id, String tableName, String columnName, String valueToFind) throws DB_FileNotFoundException;

    /**
     * Checks if a user ID is associated with a card ID in the specified table.
     *
     * @param userId    the user ID to check
     * @param userType  the type of the user
     * @param cardId    the card ID to be checked against the user ID
     * @param tableName the name of the table where the check is to be performed
     * @throws DB_ColumnNotFoundException if the specified column is not found in the table
     */
    void checkUserIdAtCardId(String userId, String userType, String cardId, String tableName) throws DB_ColumnNotFoundException;

    /**
     * Finds the first empty position in a table for a given file type.
     *
     * @param id                 the ID to check for an empty position
     * @param tableName          the name of the table to search in
     * @param fileType           the type of the file for which an empty position is sought
     * @param throwExceptionIfFull whether to throw an exception if the table is full
     * @return the position if found, otherwise null or an exception if specified
     * @throws DB_ColumnNotFoundException if the table is full and an exception is requested
     */
    String findFirstEmptyPositionInTable(String id, String tableName, String fileType, boolean throwExceptionIfFull) throws DB_ColumnNotFoundException;

    /**
     * Checks if a file exists in the database for a given ID, table, and file type.
     *
     * @param id         the ID to check for the file existence
     * @param tableName  the name of the table where the check is to be performed
     * @param fileType   the type of the file to check
     * @param value      the value to check for the file's existence
     * @return a string indicating the status or location of the file
     * @throws DB_FileNotFoundException if the file does not exist
     */
    String checkIfFileExist(String id, String tableName, String fileType, String value) throws DB_FileNotFoundException;
}
