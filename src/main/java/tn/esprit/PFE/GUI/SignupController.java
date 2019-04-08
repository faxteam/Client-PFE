/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.*;
import iservice.AdminFacadeRemote;
import iservice.SchoolFacadeRemote;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static tn.esprit.PFE.Main.MainApp.PATH;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class SignupController implements Initializable {

    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private Text error;
    @FXML
    private JFXTextField school;
    @FXML
    private AnchorPane anchore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        error.setText("");
        FXRouter.when("login", "Login.fxml");
        FXRouter.setRouteContainer("login", anchore);
    }

    @FXML
    private void SignUp(ActionEvent event) throws IOException {

        error.setText("");
        String firstN = name.getText();
        String lastN = lastName.getText();
        String emailStr = email.getText();
        String pwd = password.getText();
        String usrn = username.getText();
        String scl = school.getText();

        if (firstN.equals("")) {
            error.setText("Would you please provide your First name");
            return;
        }
        if (lastN.equals("")) {
            error.setText("Would you please provide your last name");
            return;
        }
        if (emailStr.equals("")) {
            error.setText("Would you please provide your email");
            return;
        }
        if (pwd.equals("")) {
            error.setText("Would you please provide a password");
            return;
        }
        if (usrn.equals("")) {
            error.setText("Would you please provide a username");
            return;
        }

        Proxy proxy = new Proxy();
        AdminFacadeRemote serviceAdmin = proxy.getAdminProxy();
        SchoolFacadeRemote serviceSchool = proxy.getSchoolProxy();

        Admin admin = new Admin();
        admin.setEmail(emailStr);
        admin.setFirstName(firstN);
        admin.setLastName(lastN);
        admin.setPassword(pwd);
        admin.setLogin(usrn.toLowerCase());

        if (scl.equals("")) {
            error.setText("Would you please provide Your school name");
            return;
        }

        School schol = new School();
        schol.setName(scl);
        if (!serviceAdmin.uniqueEmail(emailStr)) {
            System.out.println("Email already registred");
            error.setText("Email already Registred");
            return;
        }

        if (!serviceAdmin.uniqueUsername(usrn)) {
            System.out.println("Username already registred");
            error.setText("Username already Registred");
            return;
        }

        System.out.println("admin :: " + admin);
        serviceAdmin.create(admin);
        serviceSchool.create(schol);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Now you have successfully created an account");

        alert.showAndWait();

        FXRouter.goTo("login");
        //System.out.println();
    }
 
    @FXML
    private void Reset(ActionEvent event) {
        lastName.setText("");
        username.setText("");
        password.setText("");
        name.setText("");
        email.setText("");

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
       
        FXRouter.goTo("login");
    }

}
