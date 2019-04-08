/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import entities.Admin;
import entities.Employee;
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
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static tn.esprit.PFE.Main.MainApp.admin;
import static tn.esprit.PFE.Main.MainApp.employee;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class HomeController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane anchore;
    @FXML
    private AnchorPane adminCentralAnchore;
    @FXML
    private ImageView disconnect;
    @FXML
    private JFXButton btnSchool;
    @FXML
    private JFXButton btnInternshipDirector;
    @FXML
    private JFXButton btnHEadOfDepartement;
    @FXML
    private Menu addMenuButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Admin conncted :: " + admin);
        FXRouter.when("school", "School.fxml");
        FXRouter.when("internshipDirector", "HomeIntenDirector.fxml");
        FXRouter.when("head", "headOfDepartement.fxml");
        FXRouter.setRouteContainer("school", adminCentralAnchore);
        FXRouter.setRouteContainer("head", adminCentralAnchore);
        FXRouter.setRouteContainer("internshipDirector", adminCentralAnchore);

        if (admin != null) {
            try {
                FXRouter.goTo("school");
            } catch (IOException ex) {
                System.out.println("unable to open shcool anchore " + ex.getMessage());
            }
        }

        if (employee != null) {
            try {
                FXRouter.goTo("internshipDirector");
            } catch (IOException ex) {
                System.out.println("unable to open shcool anchore " + ex.getMessage());
            }
            btnSchool.setVisible(false);
            btnSchool.setDisable(true);
            btnHEadOfDepartement.setVisible(false);
            btnHEadOfDepartement.setDisable(true);
            btnInternshipDirector.setVisible(false);
            btnInternshipDirector.setDisable(true);
            addMenuButton.setVisible(false);
            addMenuButton.setDisable(true);
        }

    }

    @FXML
    private void handleMenuSettings(ActionEvent event) {
        try {

            String file = "/fxml/settings.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Adding new Site");
            stage.setScene(scene);
            stage.show();
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    private Stage getStage() {
        return (Stage) anchore.getScene().getWindow();
    }

    @FXML
    private void handleAboutMenu(ActionEvent event) {
    }

    @FXML
    private void MenuAddSite(ActionEvent event) {
        try {

            String file = "/fxml/Site.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Home Admin");
            stage.setScene(scene);
            stage.show();
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    @FXML
    private void menuAddDep(ActionEvent event) {
        try {

            String file = "/fxml/newDepartement.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Adding new Site");
            stage.setScene(scene);
            stage.show();

        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    @FXML
    private void MenuAddOption(ActionEvent event) {
        try {

            String file = "/fxml/newOption.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Home Admin");
            stage.setScene(scene);
            stage.show();
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    @FXML
    private void intenshipsDirectorList(ActionEvent event) {
    }

    @FXML
    private void EmployeeList(ActionEvent event) {
    }

    @FXML
    private void headDepList(ActionEvent event) {
    }

    @FXML
    private void sendNotif(ActionEvent event) {
        if (employee != null) {
            try {

                String file = "/fxml/sendNotifToStudent.fxml";
                FXMLLoader loader = new FXMLLoader();
                final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Home Admin");
                stage.setScene(scene);
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
    private void MenuAddEmployee(ActionEvent event) {
        try {

            String file = "/fxml/sendNotifToStudent.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Home Admin");
            stage.setScene(scene);
            stage.show();
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    @FXML
    private void school(ActionEvent event) {
        try {
            FXRouter.goTo("school");
        } catch (IOException ex) {
            System.out.println("unable to open shcool anchore " + ex.getMessage());
        }
    }

    @FXML
    private void internship(ActionEvent event) {
    }

    @FXML
    private void head(ActionEvent event) {
        try {
            FXRouter.goTo("head");
        } catch (IOException ex) {
            System.out.println("unable to open shcool anchore " + ex.getMessage());
        }
    }

    @FXML
    private void discconnectAction(MouseEvent event) {
        employee = new Employee();
        admin = new Admin();

        try {

            String file = "/fxml/Login.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
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
