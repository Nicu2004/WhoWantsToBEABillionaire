package org.example.database;

import org.example.dbIMplemenations.pgDatabaseConnect;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


public class databaseConnection {

    private static pgDatabaseConnect pg;

    public databaseConnection(pgDatabaseConnect pg)
    {
        this.pg =pg;
    }

    public void initiateDataBaseConnection(pgDatabaseConnect pg) throws SQLException {

        pg.connect();
        String sql = "CREATE TABLE PLAYERS(id SERIAL PRIMARY KEY, name VARCHAR(255), score INT, result varchar(3), playDate varchar(30))";
        String sql2 ="DROP TABLE IF EXISTS PLAYERS";
        Statement st = pg.getConnection().createStatement();
        st.execute(sql2);
        if(st.execute(sql))
        {
            System.out.println("Table PLAYERS has been created");
        }
        else
            System.out.println("Table PLAYERS could not be created");


        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
    }

    public static pgDatabaseConnect getConn() {
        return pg;
    }

}