/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Departement;
import entities.Site;
import iservice.DepartementFacadeRemote;
import iservice.SiteFacadeRemote;
import java.net.URL;
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
public class newDepartementController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> combobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /*
         Proxy proxy = new Proxy();
        SiteFacadeRemote serviceSite = proxy.getSite();
        List<Site> sites = serviceSite.findBySchool(admin.getSchool());
        for(Site s : sites)
        {
            comboboxSite.getItems().add(s.getName());
        }
         */
        Proxy proxy = new Proxy();
        SiteFacadeRemote serviceSite = proxy.getSite();
        List<Site> sites = serviceSite.findBySchool(admin.getSchool());

        for (Site s : sites) {
            combobox.getItems().add(s.getName());
        }
        //List<Departement> departments = service.findBySiteName(, admin.getSchool());

    }

    @FXML
    private void newSite(ActionEvent event) {
        String depString = name.getText();
        if (depString.equals("") || (combobox.getSelectionModel().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }
        Proxy proxy = new Proxy();
        DepartementFacadeRemote service = proxy.getDepartement();
        SiteFacadeRemote serviceSite = proxy.getSite();
        Departement departement = new Departement();
        departement.setDepartment_name(depString);
        departement.setSite(serviceSite.findByName(combobox.getValue()));

        if (service.uniqueDepartement(depString)) {
            service.create(departement);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You've succesfully added a new departement");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("This departement already exist");
            alert.showAndWait();
        }

    }

}
