package itsovy.sk.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Global {
    public static final int MAXITEMS=7;
    public void printLn(String message){
        System.out.println(message);
    }
    public void print(String message){
        print(message);
    }

    private final String username="store";
    private final String password="pass";
    private final String url="jdbc:mysql://localhost:3306/storeDb";

    private Connection getConnection() {
        Connection connection;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            connection= DriverManager.getConnection(url,username,password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
