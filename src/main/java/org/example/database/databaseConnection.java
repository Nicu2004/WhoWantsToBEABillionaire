package org.example.database;

import org.example.database.dbIMplemenations.pgDatabaseConnect;
import java.sql.SQLException;


public class databaseConnection {

    private static pgDatabaseConnect pg;

    public databaseConnection(pgDatabaseConnect pg)
    {
        databaseConnection.pg =pg;
    }

    public void initiateDataBaseConnection(pgDatabaseConnect pg) throws SQLException {

       if(pg==null)
           throw new SQLException("pgDatabaseConnect is null");
        pg.connect();
        System.out.println("Connected to database successfully");
    }
    public static pgDatabaseConnect getConn() {

        if(pg==null)
            throw new IllegalStateException("Database vonnection not initailized");
        return pg;
    }

}