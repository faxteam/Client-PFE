/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Classe;
import entities.Departement;
import entities.Form;
import entities.PFE_Form;
import entities.Student;
import iservice.ClassFacadeRemote;
import iservice.DepartementFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.PFE_FormFacadeRemote;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    @FXML
    private TableView<PFE_Form> FormTable;
    private static ObservableList<PFE_Form> obsForms;
    @FXML
    private TableColumn<PFE_Form, String> Id;
    @FXML
    private TableColumn<PFE_Form, String> studentEmail;
    @FXML
    private TableColumn<PFE_Form, String> colCategory;
    @FXML
    private TableColumn<PFE_Form, String> state;
    @FXML
    private JFXButton planBtn;
    public static PFE_Form PFE = new PFE_Form();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        List<String> choose = new ArrayList<>();
        Proxy proxy = new Proxy();
        EmployeeFacadeRemote service = proxy.getEmployeeProxy();
        //Departement serviceRe
        // Departement departement = service.findEmployeeDepartement(employee);

        //System.out.println("dep " + departement);
        System.out.println("employye dep name " + employee.getDepartement().getDepartment_name());
        System.out.println("employye dep id " + employee.getDepartement().getDepartement_id());
        //Departement departement = 
        choose.add("By Identifier");
        choose.add("By Class");
        choose.add("By Email");
        choose.add("By Credit");

        comboboxChoose.getItems().addAll(choose);
        initStudentList();
        loadListAllStudents();
        
        
        /*List<PFE_Form> forms =  getListForms();
        for(PFE_Form p : forms)
            System.out.println(p.getStudent() + " " + p.getTitle());
        */
        initForms();
        loadDataForms();
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
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        System.out.println("trying to resolve this one departement retriever..");
        Departement departement = serviceDep.findByName(employee.getDepartement().getDepartment_name());
        System.out.println("DEpartement found " + departement.getDepartment_name());
        
        ClassFacadeRemote serviceClasse = proxy.getClasse();
        List<Classe> classes = serviceClasse.findClassByDepartement(departement);
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
    
    private List<PFE_Form> getListForms()
    {
        Proxy proxy = new Proxy();
        PFE_FormFacadeRemote service = proxy.getPFEFormProxy();
        //List<PFE_Form> list = service.findAll();
        
        System.out.println("enterging here");
        List<Form> forms = proxy.getFormProxy().findAll();
        
        List<PFE_Form> pfeForms = new ArrayList<>();
        for(Form f : forms)
        {
            PFE_Form form = service.find(f.getForm_id());
            System.out.println(f);
            pfeForms.add(form);
            System.out.println(form.getForm_id());
        }
        return pfeForms;
    }
    
    private void initForms()
    {
        //studentEmail;colCategory Id;FormTable
         Id.setCellValueFactory(new PropertyValueFactory<>("student"));
         colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
         studentEmail.setCellValueFactory(new PropertyValueFactory<>("title"));
         state.setCellValueFactory(new PropertyValueFactory<>("accepted"));
         System.out.println("initialising");
    }
    
    private void loadDataForms()
    {
        //obsForms
       
        List<PFE_Form> lists =  getListForms();
        List<PFE_Form> forms = new ArrayList<>();
        System.out.println("loading");
        System.out.println("size " + lists.size());
        for(PFE_Form p : lists)
        {
            PFE_Form tmp = new PFE_Form();
            tmp.setAccepted(p.isAccepted());
            tmp.setStudent(p.getStudent());
            tmp.setCategory(p.getCategory());
            tmp.setTitle(p.getTitle());
            tmp.setStudent(p.getStudent());
            forms.add(tmp);
            //System.out.println("tmp " + tmp.getCategory() + " "+ tmp.getStudent().getIdent() + " "+ p.isAccepted());
        }
        for(PFE_Form p : forms)
        {
            System.out.print("Form ");
            System.out.println(p);
        }
        System.out.println("to be show size " + forms.size());
        obsForms = FXCollections.observableArrayList(forms);
        FormTable.setItems(obsForms);
    }

    @FXML
    private void getSelectedForm(MouseEvent event) {
        planBtn.setDisable(false);
        PFE = getSelectedForm();
    }
    
    private PFE_Form getSelectedForm()
    {
        PFE_Form form = FormTable.getSelectionModel().getSelectedItem();
        return form;
    }

    @FXML
    private void plan(ActionEvent event) {
        try {

            String file = "/fxml/Plan.fxml";
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
    private void pfeTab(Event event) {
        //planBtn.setDisable(true);
    }

    @FXML
    private void studentTab(Event event) {
    }

}
