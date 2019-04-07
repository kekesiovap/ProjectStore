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

    public static final String username="store";
    public static final String password="pass";
    public static final String url="jdbc:mysql://localhost:3306/storedb";
    public static final String host="localhost";
    public static final String port="27017";
}
