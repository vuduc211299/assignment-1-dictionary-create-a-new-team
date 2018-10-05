package main;

import java.sql.*;

public class DatabaseConnection
{
    public static Connection getConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:C:/Users/Hung Do/Desktop/InterfaceVersion/src/Database/dict_hh.db");
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
