/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.google.common.collect.ImmutableList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import entities.Classe;
import entities.MailServer;
import entities.Student;
import iservice.ClassFacadeRemote;
import iservice.MailServerFacadeLocal;
import iservice.StudentFacadeRemote;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import static tn.esprit.PFE.Main.MainApp.employee;
import tn.esprit.PFE.Utils.MailSender;
import tn.esprit.PFE.Utils.Proxy;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class sendNotifToStudentController implements Initializable {

    @FXML
    private StackPane rootContainer;
    @FXML
    private AnchorPane anchore;
    @FXML
    private Tab schoolDataTab;
    @FXML
    private Tab mailTab;
    @FXML
    private JFXButton btnTest;
    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private TableColumn<Student, String> Colident, colEmail, colCredit, colClass;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXTextArea content;
    private static ObservableList<Student> obsStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // 
        initStudentList();
        loadListAllStudents();

    }

    private void checkForMailServerConfig() {
        JFXButton button = new JFXButton("Okay");
        button.setOnAction((ActionEvent event) -> {
            ((Stage) rootContainer.getScene().getWindow()).close();
        });
        Proxy proxy = new Proxy();
        MailServerFacadeLocal service = proxy.getMailServer();
        MailServer mailServerInfo = service.findByMailerEmployee(employee);
        System.out.println("mailserver  " + mailServerInfo);
        if (mailServerInfo.getId() == 0) {
            System.out.println("Mail server not configured");
            sendNotifToStudentController.showMaterialDialog(rootContainer, anchore, ImmutableList.of(button), "Mail server is not configured", "Please configure mail server first.\nIt is available under Settings");
        }
    }

    @FXML
    private void sendEmail(ActionEvent event) {
        String subjectString = subject.getText();
        String contentString = content.getText();

        if (subjectString.equals("") || contentString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please verify your missing data");
            return;
        }

        Proxy proxy = new Proxy();
        System.out.println("email test sending ... ");

        MailServerFacadeLocal service = proxy.getMailServer();
        MailServer mailer = service.findByMailerEmployee(employee);
        List<Student> students = this.retrieveList();

        for (Student S : students) {
            try {
                MailSender.sendMail("smtp.gmail.com", "587", mailer.getEmail(),
                        mailer.getEmail(), mailer.getPassword(), S.getProfessionalEmail(),
                        subjectString, contentString);
                Notifications notificationBuilder =  Notifications.create()
                        .title("Success")
                        .text("Emails has been sent successfully")
                        .graphic(null)
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.BOTTOM_RIGHT);
                
                notificationBuilder.show();
                
            } catch (MessagingException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("An error has occured! Would you please check\nyour internet connexion");
                alert.showAndWait();
            }
        }
    }

    private void initStudentList() {
        Colident.setCellValueFactory(new PropertyValueFactory<>("ident"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("professionalEmail"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("classe"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }

    private List<Student> retrieveList() {
        Proxy proxy = new Proxy();
        StudentFacadeRemote service = proxy.getStudent();
        ClassFacadeRemote serviceClasse = proxy.getClasse();
        List<Classe> classes = serviceClasse.findClassByDepartement(employee.getDepartement());
        List<Student> students = service.findListByClass(classes);

        List<Student> stds = new ArrayList<>();
        for (Student s : students) {
            if (s.getForm() == null) {
                stds.add(s);
            }
        }

        return stds;
    }

    private void loadListAllStudents() {
        List<Student> students = this.retrieveList();
        obsStudent = FXCollections.observableArrayList(students);
        tableStudent.setItems(obsStudent);
    }

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        System.out.println("adding ");
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }

    @FXML
    private void notTab(Event event) {
        this.checkForMailServerConfig();
    }
}
