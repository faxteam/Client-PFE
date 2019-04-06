/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import entities.Departement;
import entities.Employee;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import static tn.esprit.PFE.Main.MainApp.admin;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class AssignController implements Initializable {

    @FXML
    private AnchorPane centerAnchore;
    @FXML
    private Text name;
    @FXML
    private Text email;
    @FXML
    private Text id;
    @FXML
    private Text dep;
    @FXML
    private JFXComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Employee e = (Employee) FXRouter.getData();
        name.setText("Name : " + e.getFirstName() + " " + e.getLastName());
        email.setText("Email : " + e.getEmail());
        id.setText("Name : " + e.getPassword());
        dep.setText("Departement " + e.getDepartement().getDepartment_name());

        Proxy proxy = new Proxy();
        DepartementFacadeRemote service = proxy.getDepartement();
        SiteFacadeRemote serviceSite = proxy.getSite();
        Site s =  serviceSite.findBySchool(admin.getSchool()).get(0);
        System.out.println("site " + s);
        List<Departement> departements = service.findByChefNull(s);

        for (Departement d : departements) {
            combo.getItems().addAll(d.getDepartment_name());
        }
    }

    @FXML
    private void confirm(ActionEvent event) {

        if (combo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Proxy proxy = new Proxy();
        DepartementFacadeRemote service = proxy.getDepartement();
        Departement d = service.findByName(combo.getValue());

        d.setChef((Employee) FXRouter.getData());
        service.edit(d);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Success");
        alert.setContentText("You have succesfully assigned a new departement head to " + d.getDepartment_name());
        alert.showAndWait();

    }

}
