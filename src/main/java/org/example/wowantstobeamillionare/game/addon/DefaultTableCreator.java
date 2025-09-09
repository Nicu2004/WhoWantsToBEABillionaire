package org.example.wowantstobeamillionare.game.addon;

import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultTableCreator{
    public static void createUserTable() {
        try{
            Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
            Statement statemnt = conn.createStatement();
            String createTableSQL = """
        CREATE TABLE Players (
            id SERIAL PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            score INTEGER NOT NULL,
            result VARCHAR(50) NOT NULL,
            playDate VARCHAR(50) NOT NULL
        )
        """;
        statemnt.execute(createTableSQL);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void createQuestionTable() {
        try{
            Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
            Statement statemnt = conn.createStatement();
            String createTableSQL = """
        
                    CREATE TABLE questions (
             id SERIAL PRIMARY KEY,
             question TEXT NOT NULL,
             ans1 TEXT NOT NULL,
             ans2 TEXT NOT NULL,
             ans3 TEXT NOT NULL,
             ans4 TEXT NOT NULL,
             correct_answer INTEGER NOT NULL
         );
        """;
            statemnt.execute(createTableSQL);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
