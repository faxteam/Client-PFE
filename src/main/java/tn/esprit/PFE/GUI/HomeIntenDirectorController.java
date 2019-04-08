/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import entities.Classe;
import entities.Student;
import iservice.ClassFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.StudentFacadeRemote;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import static tn.esprit.PFE.Main.MainApp.employee;
import tn.esprit.PFE.Utils.Proxy;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class HomeIntenDirectorController implements Initializable {

    @FXML
    private AnchorPane centerAnchore;
    @FXML
    private TableView<Student> StudentTable;
    @FXML
    private JFXComboBox<String> comboboxChoose;
    @FXML
    private TableColumn<Student, String> email, Identifier, colClass, credit;

    private static ObservableList<Student> obsStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        List<String> choose = new ArrayList<>();
        Proxy proxy = new Proxy();
        EmployeeFacadeRemote service = proxy.getEmployeeProxy();
        // Departement departement = service.findEmployeeDepartement(employee);

        //System.out.println("dep " + departement);
        System.out.println("employye dep " + employee.getDepartement());
        choose.add("By Identifier");
        choose.add("By Class");
        choose.add("By Email");
        choose.add("By Credit");

        comboboxChoose.getItems().addAll(choose);
        initStudentList();
        loadListAllStudents();
    }

    @FXML
    private void viewList(ActionEvent event) {

    }

    @FXML
    private void orderByCombobox(ActionEvent event) {

        obsStudent.clear();
        loadData(comboboxChoose.getValue());
    }

    private void initStudentList() {
        Identifier.setCellValueFactory(new PropertyValueFactory<>("ident"));
        email.setCellValueFactory(new PropertyValueFactory<>("professionalEmail"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("classe"));
        credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }

    private List<Student> retrieveList() {
        Proxy proxy = new Proxy();
        StudentFacadeRemote service = proxy.getStudent();
        ClassFacadeRemote serviceClasse = proxy.getClasse();
        List<Classe> classes = serviceClasse.findClassByDepartement(employee.getDepartement());
        List<Student> students = service.findListByClass(classes);

        return students;
    }

    private void loadListAllStudents() {
        List<Student> students = this.retrieveList();

        obsStudent = FXCollections.observableArrayList(students);
        StudentTable.setItems(obsStudent);
    }

    private void loadData(String criteria) {

        List<Student> students = this.retrieveList();

        if (criteria.equals("By Identifier")) {
            students.sort((Student S1, Student S2)
                    -> S1.getIdent().compareTo(S2.getIdent())
            );
            obsStudent = FXCollections.observableArrayList(students);
            StudentTable.setItems(obsStudent);
        } 
        else if (criteria.equals("By Class")) {
            students.sort((Student S1, Student S2)
                    -> S1.getClasse().getName().compareTo(S2.getClasse().getName())
            );
            obsStudent = FXCollections.observableArrayList(students);
            StudentTable.setItems(obsStudent);
        } 
        else if (criteria.equals("By Email")) {
            students.sort((Student S1, Student S2)
                    -> S1.getProfessionalEmail().compareTo(S2.getProfessionalEmail())
            );
            obsStudent = FXCollections.observableArrayList(students);
            StudentTable.setItems(obsStudent);
        } 
        else if (criteria.equals("By Credit")) {
            students.sort((Student S1, Student S2)
                    -> (S1.getCredit() - S2.getCredit())
            );
            obsStudent = FXCollections.observableArrayList(students);
            StudentTable.setItems(obsStudent);
        }
    }

}
