package main;

import java.sql.*;

public class DatabaseConnection
{
    public static Connection LoginConnector()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hung Do\\Desktop\\Exe3\\src\\Database\\dict_hh.db");
            return conn;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
