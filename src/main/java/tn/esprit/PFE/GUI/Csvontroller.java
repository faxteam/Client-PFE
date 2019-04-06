/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import entities.Classe;
import entities.Student;
import iservice.ClassFacadeRemote;
import iservice.StudentFacadeRemote;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class Csvontroller implements Initializable {

    @FXML
    private StackPane rootContainer;
    @FXML
    private AnchorPane anchore;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> colId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colLast;
    @FXML
    private TableColumn<Student, String> colEmail;
    @FXML
    private TableColumn<Student, String> colCredit;
    @FXML
    private TableColumn<Classe, String> colClasse;

    private static ObservableList<Student> obsStudent;
    public List<Student> students;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        students = (List<Student>) FXRouter.getData();
        initData();
        loadData(students);

    }

    private void initData() {
        //password
        colId.setCellValueFactory(new PropertyValueFactory<>("ident"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("professionalEmail"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
    }

    private void loadData(List<Student> students) {
        obsStudent = FXCollections.observableArrayList(students);
        studentTable.setItems(obsStudent);
    }

    @FXML
    private void save(ActionEvent event) {

        int i = 0;
        Proxy proxy = new Proxy();
        StudentFacadeRemote service = proxy.getStudent();
        for (Student s : students) {
            System.out.println(s);

            if (service.uniqueStundet(s)) {
                service.create(s);
                i++;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("this student is already registred in the database " + s.getProfessionalEmail());
                alert.showAndWait();
            }
        }
        if (i > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Succcess");
            alert.setContentText("You have uploded " + i + " new student(s) succesfully");
            alert.showAndWait();
        }
    }

}
