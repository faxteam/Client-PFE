/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import entities.Classe;
import entities.MailServer;
import entities.School;
import entities.Student;
import iservice.ClassFacadeRemote;
import iservice.MailServerFacadeLocal;
import iservice.SchoolFacadeRemote;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import static tn.esprit.PFE.Main.MainApp.admin;
import static tn.esprit.PFE.Main.MainApp.employee;
import tn.esprit.PFE.Utils.MailSender;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class SettingsController implements Initializable {

    @FXML
    private StackPane rootContainer;
    @FXML
    private JFXTextField schoolName;
    @FXML
    private JFXTextField schoolAddress;
    @FXML
    private JFXTextField schoolWebsite;
    @FXML
    private JFXTextField serverName;
    @FXML
    private JFXTextField smtpPort;
    @FXML
    private JFXTextField emailAddress;
    @FXML
    private JFXPasswordField emailPassword;
    @FXML
    private JFXSpinner progressSpinner;

    private final ObservableList<Student> dataList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchore;
    @FXML
    private Tab schoolDataTab;
    @FXML
    private Tab mailTab;
    @FXML
    private Tab importTab;
    @FXML
    private JFXButton btnTest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Proxy proxy = new Proxy();
        MailServerFacadeLocal service = proxy.getMailServer();

        if (employee != null) {
            importTab.getTabPane().getTabs().remove(importTab);
            schoolDataTab.getTabPane().getTabs().remove(schoolDataTab);

            MailServer server = service.findByMailerEmployee(employee);
            if (server.getId() != 0) {
                emailAddress.setText(server.getEmail());
                emailPassword.setText(server.getPassword());
                btnTest.setDisable(false);
            } else {
                btnTest.setDisable(true);
            }
            System.out.println("server employee :: " + server);
        } else {
            schoolName.setText(admin.getSchool().getName());
            MailServer server = service.findByMailer(admin);
            if (server.getId() != 0) {
                emailAddress.setText(server.getEmail());
                emailPassword.setText(server.getPassword());
                btnTest.setDisable(false);
            } else {
                btnTest.setDisable(true);
            }
            System.out.println("server Admin :: " + server);
        }

        FXRouter.when("students", "csv.fxml");
        FXRouter.setRouteContainer("students", anchore);
    }

    @FXML
    private void updateSchool(ActionEvent event) {
        String name = schoolName.getText();
        String address = schoolAddress.getText();
        String website = schoolWebsite.getText();
        if (name.equals("") || address.equals("") || website.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Proxy proxy = new Proxy();
        SchoolFacadeRemote service = proxy.getSchoolProxy();

        School school = admin.getSchool();
        school.setName(name);
        school.setAddress(address);
        school.setWebsite(website);
        service.edit(school);
        System.out.println("updating ");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Success");
        alert.setContentText("You have successfully updated your school data");
        alert.showAndWait();
    }

    @FXML
    private void handleTestMailAction(ActionEvent event) throws MessagingException {
        Proxy proxy = new Proxy();
        System.out.println("email test sending ... ");
        MailServer mailer = new MailServer();
        MailServerFacadeLocal service = proxy.getMailServer();

        if (admin != null) {
            mailer = service.findByMailer(admin);
            if (mailer != null) {
                try {
                    MailSender.sendMail("smtp.gmail.com", "587", mailer.getEmail(),
                            mailer.getEmail(), mailer.getPassword(), mailer.getEmail(),
                            "Email test sender",
                            "Chekicng email services ... ");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("Test email has been sent please check your inbox");
                    alert.showAndWait();
                } catch (MessagingException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("An error has occured would you please check\nyou internet connexion");
                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                    System.out.println("mailing failure");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Please retry after saving your data");
                alert.showAndWait();
            }
        } else if (employee != null) {
            mailer = service.findByMailerEmployee(employee);
            if (mailer != null) {
                try {
                    MailSender.sendMail("smtp.gmail.com", "587", mailer.getEmail(),
                            mailer.getEmail(), mailer.getPassword(), mailer.getEmail(),
                            "Email test sender",
                            "Chekicng email services ... ");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("Test email has been sent please check your inbox");
                    alert.showAndWait();
                } catch (MessagingException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("An error has occured would you please check\nyou internet connexion");
                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                    System.out.println("mailing failure");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Please retry after saving your data");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void saveMailServerConfuration(ActionEvent event) {
        String email = emailAddress.getText();
        String password = emailPassword.getText();

        if (email.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Missing data! please check your inputs");
            alert.showAndWait();
            return;
        }

        Proxy proxy = new Proxy();
        MailServerFacadeLocal service = proxy.getMailServer();

        if (admin != null) {
            MailServer MailerData = service.findByMailerEmployee(employee);
            if (MailerData == null) {
                MailServer newData = new MailServer();
                newData.setEmail(email);
                newData.setPassword(password);
                newData.setMailer(admin);
                service.create(newData);
                btnTest.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Now you have successfuly set up your mail data");
                alert.showAndWait();
            } else {
                MailerData.setEmail(email);
                MailerData.setPassword(password);
                service.edit(MailerData);
                btnTest.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Now you have successfuly updated your mail data");
                alert.showAndWait();
            }

        } else if (employee != null) {
            MailServer MailerData = service.findByMailerEmployee(employee);
            if (MailerData == null) {
                MailServer newData = new MailServer();
                newData.setEmail(email);
                newData.setPassword(password);
                newData.setMailerEmployee(employee);
                service.create(newData);
                btnTest.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Now you have successfuly set up your mail data");
                alert.showAndWait();
            } else {
                MailerData.setEmail(email);
                MailerData.setPassword(password);
                service.edit(MailerData);
                btnTest.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Now you have successfuly updateds up your mail data");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void handleDatabaseExportAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        ArrayList<String> listFiles = new ArrayList<>();
        listFiles.add("*.csv");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));
        File csvFile = fc.showOpenDialog(null);

        try {
            FXRouter.goTo("students", this.readCSV(csvFile));

        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Student> readCSV(File CsvFile) throws IOException {

        List<Student> students = new ArrayList<>();
        try {

            String FieldDelimiter = ",";

            BufferedReader br;

            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);

                /* Student record = new Student(fields[0], fields[1], fields[2],
                fields[3], fields[4], fields[5]);*/
                Student student = new Student();
                String name = fields[2];
                String lastName = fields[3];
                String credit = fields[1];
                String ident = fields[0];
                String pwd = fields[4];
                String ProfEmail = fields[5];
                String PersoEmail = fields[6];
                String classe = fields[7];

                Proxy proxy = new Proxy();
                ClassFacadeRemote service = proxy.getClasse();
                List<Classe> classes = service.findClassByName(classe);

                if (classes.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Class not found for student " + student.getFirstName() + " " + student.getLastName());
                    alert.showAndWait();
                }

                student.setFirstName(name);
                student.setLastName(lastName);
                student.setCredit(Integer.parseInt(credit));
                student.setPersonalEmail(PersoEmail);
                student.setProfessionalEmail(ProfEmail);
                student.setPassword(ident);
                student.setClasse(classes.get(0));
                student.setIdent(pwd);

                students.add(student);
                //dataList.add(record);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found " + ex.getMessage());
        }
        return students;
    }

}
