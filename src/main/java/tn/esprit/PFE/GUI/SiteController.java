/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXTextField;
import entities.Site;
import iservice.SiteFacadeRemote;
import java.net.URL;
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
public class SiteController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField adress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void newSite(ActionEvent event) {
        String nameString = name.getText();
        String addressString = adress.getText();

        Proxy proxy = new Proxy();
        SiteFacadeRemote service = proxy.getSite();

        if (nameString.equals("") || addressString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Site site = new Site();
        site.setName(nameString);
        site.setAddress(addressString);
        site.setSchool(admin.getSchool());

        if (service.UniqueSite(site)) {
            service.create(site);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You've succesfully added a new site");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("This site already exist");
            alert.showAndWait();
        }

    }

}
