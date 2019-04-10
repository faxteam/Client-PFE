/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import entities.Employee;
import iservice.EmployeeFacadeRemote;
import iservice.PFE_FormFacadeRemote;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import static tn.esprit.PFE.GUI.HomeIntenDirectorController.PFE;
import tn.esprit.PFE.Utils.Proxy;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class PlanController implements Initializable {

    @FXML
    private StackPane rootContainer;
    @FXML
    private AnchorPane anchore;
    @FXML
    private Tab schoolDataTab;
    @FXML
    private Text student;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> president;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Proxy proxy = new Proxy();
        EmployeeFacadeRemote service = proxy.getEmployeeProxy();
        List<Employee> employees = service.findByRole(Employee.Role.professor);

        for (Employee E : employees) {
            president.getItems().add(E.getEmail());
        }

        student.setText(student.getText() + " : " + PFE.getStudent());
    }

    @FXML
    private void addDefense(ActionEvent event) {

        //jXDatePicker1.getDate().toString("yyyy-MM-dd");
        //String dateDef;
        LocalDate dateDef = date.getValue();
        //Instant instant = Instant.from(dateDef);
        //DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String dateToStore = oDateFormat.format(dateDef);

        if (PFE.getDefenseDate() != null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Internship defense already setted");
            alert.setTitle("Error");
            alert.showAndWait();
        } else {
            System.out.println("Date " + dateDef);
            Proxy proxy = new Proxy();
            EmployeeFacadeRemote service = proxy.getEmployeeProxy();
            Instant instant = Instant.from(dateDef.atStartOfDay(ZoneId.systemDefault()));
            PFE.setDefenseDate(Date.from(instant));
            Employee employee = service.findByName(president.getValue());
            PFE.setPresident(employee);

            PFE_FormFacadeRemote servicePFE = proxy.getPFEFormProxy();
            servicePFE.edit(PFE);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Internship Defense added with success");
            alert.setTitle("Success");
            alert.showAndWait();
        }

    }

}
