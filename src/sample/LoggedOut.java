package sample;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoggedOut {
    public TextField usernameText;
    public TextField passwordText;

    public void getEmployee(ActionEvent actionEvent) throws SQLException {
        String login = usernameText.getText();
        String password = passwordText.getText();

        Database database = Database.getInstanceDb();
        Employee emp = database.getEmployee(login,password);
        if(emp != null){
            System.out.println("ID: "+emp.getId()+" | First name: "+emp.getFname()+" | Last name: "+emp.getLname()+" | Position ID: "+emp.getPosition());
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedIn.fxml"));
            Parent root = loader.load();
            LoggedIn loggedIn = loader.getController();
            loggedIn.setEmployee(emp);
            ((Node) (actionEvent.getSource())).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
