/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Departement;
import entities.Opt;
import entities.Site;
import iservice.DepartementFacadeRemote;
import iservice.OptFacadeRemote;
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
public class newOptionController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> combobox;
    @FXML
    private JFXComboBox<String> comboboxDepartement;

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
            combobox.getItems().add(s.getName());
        }
    }

    @FXML
    private void newOption(ActionEvent event) {
        String nameString = name.getText();
        if (nameString.equals("") || (combobox.getSelectionModel().isEmpty()) || (comboboxDepartement.getSelectionModel().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Opt option = new Opt();
        option.setName(nameString);

        Proxy proxy = new Proxy();
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        OptFacadeRemote service = proxy.getOption();
        Departement dep = serviceDep.findByName(comboboxDepartement.getValue());
        option.setDepartement(dep);

        if (service.UniqueSite(option)) {
            service.create(option);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You've succesfully added a new Option");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("This option already exist");
            alert.showAndWait();
        }

    }

    @FXML
    private void changeDepList(ActionEvent event) {

        comboboxDepartement.getSelectionModel().clearSelection();
        comboboxDepartement.getItems().clear();
        Proxy proxy = new Proxy();

        DepartementFacadeRemote service = proxy.getDepartement();
        List<Departement> departements = service.findBySiteName(combobox.getValue(), admin.getSchool());

        for (Departement s : departements) {
            comboboxDepartement.getItems().add(s.getDepartment_name());
        }
    }

}
