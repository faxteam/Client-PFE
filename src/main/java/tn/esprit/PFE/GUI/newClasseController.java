/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Classe;
import entities.Departement;
import entities.Opt;
import entities.Site;
import iservice.ClassFacadeRemote;
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
public class newClasseController implements Initializable {

    @FXML
    private JFXComboBox<String> siteCombo;
    @FXML
    private JFXComboBox<String> DepCombo;
    @FXML
    private JFXComboBox<String> OptionCombo;
    @FXML
    private JFXTextField className;

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
        OptionCombo.getSelectionModel().clearSelection();
        OptionCombo.getItems().clear();
        Proxy proxy = new Proxy();

        OptFacadeRemote service = proxy.getOption();
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        Departement department = serviceDep.findByName(DepCombo.getValue());
        List<Opt> options = service.findByDepartement(department);

        for (Opt s : options) {
            OptionCombo.getItems().add(s.getName());
        }
    }

    @FXML
    private void newEmployee(ActionEvent event) {
        String name = className.getText();

        if (name.equals("") || (DepCombo.getSelectionModel().isEmpty())
                || (siteCombo.getSelectionModel().isEmpty()) || (OptionCombo.getSelectionModel().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Proxy proxy = new Proxy();

        OptFacadeRemote service = proxy.getOption();
        ClassFacadeRemote serviceClasse = proxy.getClasse();
        List<Opt> options = service.findByName(OptionCombo.getValue());

        Classe classe = new Classe();
        classe.setName(name);
        classe.setOption(options.get(0));

        if (serviceClasse.uniqueClass(classe)) {
            serviceClasse.create(classe);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You have successfully added a new class");
            alert.showAndWait();
            return;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("This class already exist!");
            alert.showAndWait();
            return;
        }
    }

}
