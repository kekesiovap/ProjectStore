package itsovy.sk.Main;

import itsovy.sk.Exception.BillException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws BillException, SQLException, IOException, TransformerException, ParserConfigurationException {
        Application app=Application.getInstance();
        app.example();
    }
}
