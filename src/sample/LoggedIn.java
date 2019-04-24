package sample;

import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class LoggedIn {

    public TextField employeeText;
    public SplitMenuButton clientsMenu;
    public Label name;
    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
        name.setText(employee.getFname() + " " + employee.getLname());
    }
}


