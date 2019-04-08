/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Admin;
import iservice.AutheticatorInterfaceRemote;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static tn.esprit.PFE.Main.MainApp.admin;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Text error;
    @FXML
    private AnchorPane anchore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        error.setText("");
        FXRouter.when("signup", "Signup.fxml");
        FXRouter.when("home", "Home.fxml");
        FXRouter.setRouteContainer("signup", anchore);
        FXRouter.setRouteContainer("home", anchore);

        FXRouter.when("loginEmployee", "LoginEmployee.fxml");
        FXRouter.setRouteContainer("loginEmployee", anchore);

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        Proxy proxy = new Proxy();
        String usrn = username.getText();
        String pwd = password.getText();

        AutheticatorInterfaceRemote service = proxy.getAuthenticated();

        if (usrn.equals("")) {
            error.setText("Please type your username");
            return;
        }

        if (pwd.equals("")) {
            error.setText("Please type your password");
            return;
        }

        Admin admn = service.getAutheticatedAdmin(usrn, pwd);

        System.out.println("add " + admn);
        //Email incorrect
        if (admn.getEmail().equals("-")) {
            error.setText("Your username is inccorect");
        } else if (admn.getEmail().equals("*")) {
            error.setText("Your password is inccorect");
        } else {
            admin = admn;
            System.out.println("admin connected " + admin);
            //anchore.setPrefSize(800, 600);
            //FXRouter.goTo("home");
            try {

                String file = "/fxml/Home.fxml";
                FXMLLoader loader = new FXMLLoader();
                final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Home Admin");
                stage.setScene(scene);

                final Node source = (Node) event.getSource();
                final Stage stages = (Stage) source.getScene().getWindow();
                stages.close();

                stage.show();
            } catch (Throwable e) {
                System.out.println("error " + e.getMessage());
                System.out.println("cause " + e.getCause());
                System.out.println("local " + e.getLocalizedMessage());
                System.out.println("stack trace " + e.getStackTrace());
            }
        }

    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException {
        username.setText("");
        password.setText("");
        /* try {
            java.net.URL url = new File(PATH + "Signup.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Admin Sign Up");
            stage.setScene(scene);
            
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.close();
            
            stage.show();
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }*/
        FXRouter.goTo("signup");
    }

    @FXML
    private void loginAsEmployee(ActionEvent event) throws IOException {
        FXRouter.goTo("loginEmployee");
    }

}
