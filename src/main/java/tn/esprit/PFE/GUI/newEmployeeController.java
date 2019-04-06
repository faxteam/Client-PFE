/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Departement;
import entities.Employee;
import entities.Site;
import iservice.DepartementFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.SiteFacadeRemote;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static tn.esprit.PFE.Main.MainApp.admin;
import tn.esprit.PFE.Utils.Proxy;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class newEmployeeController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> siteCombo;
    @FXML
    private JFXComboBox<String> DepCombo;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXComboBox<String> roleCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Proxy proxy = new Proxy();
        SiteFacadeRemote serviceSite = proxy.getSite();
        List<Site> sites = serviceSite.findBySchool(admin.getSchool());

        for (Site s : sites) {
            siteCombo.getItems().add(s.getName());
        }

        roleCombo.getItems().addAll("Departement Supervisor", "Director", "Professor");
    }

    @FXML
    private void Site(ActionEvent event) {
        DepCombo.getSelectionModel().clearSelection();
        DepCombo.getItems().clear();
        Proxy proxy = new Proxy();

        DepartementFacadeRemote service = proxy.getDepartement();
        List<Departement> departements = service.findBySiteName(siteCombo.getValue(), admin.getSchool());

        for (Departement s : departements) {
            DepCombo.getItems().add(s.getDepartment_name());
        }
    }

    @FXML
    private void Deparatement(ActionEvent event) {
    }

    @FXML
    private void newEmployee(ActionEvent event) {
        String nameString = name.getText();
        String lastNAmeString = lastName.getText();
        String emailString = email.getText();
        String pwsString = password.getText();
        String usernameString = username.getText();

        if (nameString.equals("") || lastNAmeString.equals("") || emailString.equals("")
                || pwsString.equals("") || usernameString.equals("") || (DepCombo.getSelectionModel().isEmpty())
                || (siteCombo.getSelectionModel().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Proxy proxy = new Proxy();
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        Departement departement = serviceDep.findByName(DepCombo.getValue());
        EmployeeFacadeRemote service = proxy.getEmployeeProxy();

        Employee e = new Employee();
        e.setDepartement(departement);
        e.setEmail(emailString);
        e.setFirstName(nameString);
        e.setLastName(lastNAmeString);
        e.setLogin(usernameString);
        e.setPassword(pwsString);

        if (roleCombo.getValue().equals("Departement Supervisor")) {
            e.setRole(Employee.Role.departement_supervisor);
        } else if (roleCombo.getValue().equals("Director")) {
            e.setRole(Employee.Role.director);
        } else if (roleCombo.getValue().equals("Professor")) {
            e.setRole(Employee.Role.professor);
        }
        
        if (service.uniqueEmployee(e)) {
            service.create(e);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You've succesfully added a new " + roleCombo.getValue());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("This Employee already exist");
            alert.showAndWait();
        }   
    }

}
