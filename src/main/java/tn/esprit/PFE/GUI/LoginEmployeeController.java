/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Employee;
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
import static tn.esprit.PFE.Main.MainApp.employee;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class LoginEmployeeController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Text error;

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
    private void handleLoginButtonAction(ActionEvent event) {
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

        Employee employ = service.getAutheticatedEmployee(usrn, pwd);

//        System.out.println("add " + employ);
        //Email incorrect
        if (employ.getEmail().equals("-")) {
            error.setText("Your username is inccorect");
        } else if (employ.getEmail().equals("*")) {
            error.setText("Your password is inccorect");
        } else {
            employee = employ;
//            System.out.println("Employee connected " + employee);
            //anchore.setPrefSize(800, 600);
            //FXRouter.goTo("home");
            try {

                String file = "/fxml/Home.fxml";
                FXMLLoader loader = new FXMLLoader();
                final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Home Internship director");
                stage.setScene(scene);
                
                final Node source = (Node) event.getSource();
                final Stage stages = (Stage) source.getScene().getWindow();
                stages.close();
                
                stage.show();
            } catch (Throwable e) {
                System.out.println("error " + e.getMessage());
            }
        }
    }


    @FXML
    private void loginAsAdmin(ActionEvent event) throws IOException {
        FXRouter.goTo("login");
    }

}
