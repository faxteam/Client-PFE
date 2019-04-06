/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXComboBox;
import entities.Classe;
import entities.Departement;
import entities.Employee;
import entities.Opt;
import entities.School;
import entities.Site;
import iservice.ClassFacadeRemote;
import iservice.DepartementFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.OptFacadeRemote;
import iservice.SiteFacadeRemote;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static tn.esprit.PFE.Main.MainApp.admin;
import tn.esprit.PFE.Utils.Proxy;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class SchoolController implements Initializable {

    @FXML
    private AnchorPane centerAnchore;

    @FXML
    private TableView<Site> siteTable;

    @FXML
    private TableColumn<Site, String> colNumSite, colNameSite;

    @FXML
    private JFXComboBox<String> comboboxSite;

    @FXML
    private TableView<Departement> depTable;

    @FXML
    private TableColumn<Departement, String> col_numDep, colDepName;

    @FXML
    private JFXComboBox<String> ComboboxDep;

    private static ObservableList<Site> obsSite;
    private static ObservableList<Departement> obsDepartement;
    private static ObservableList<Opt> obsOption;
    private static ObservableList<Employee> obsEmployee;
    private static ObservableList<Classe> obsClasses;
    @FXML
    private TableColumn<Employee, String> colHeadDep;
    @FXML
    private TableView<Opt> tableOpt;
    @FXML
    private TableColumn<Opt, String> colNumOpt, colOptName;
    @FXML
    private JFXComboBox<String> ComboboxDepEmp;
    @FXML
    private TableView<Employee> tableEmpl;
    @FXML
    private TableColumn<Employee, String> colId, colEmpName, colLNameEmp, colRole, email;
    @FXML
    private JFXComboBox<String> comboboxOptionForClass;
    @FXML
    private TableView<Classe> tableClass;
    @FXML
    private TableColumn<Classe, String> colClassId, colClassName;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Proxy proxy = new Proxy();
        SiteFacadeRemote serviceSite = proxy.getSite();
        List<Site> sites = serviceSite.findBySchool(admin.getSchool());
        for (Site s : sites) {
            comboboxSite.getItems().add(s.getName());
        }
        initSite();
        loadSiteData();

    }

    @FXML
    void comboDep(ActionEvent event) {

        initOptions();
//        obsOption.clear();
        loadOptionData(ComboboxDep.getValue(), admin.getSchool());
        
        Proxy proxy = new Proxy();
        OptFacadeRemote optionService = proxy.getOption();
        DepartementFacadeRemote depService = proxy.getDepartement();
        List<Opt> options = optionService.findByDepartement(depService.findByName(ComboboxDep.getValue()));
        for(Opt o : options)
            comboboxOptionForClass.getItems().add(o.getName());
    }

    @FXML
    void comboSite(ActionEvent event) {
        initDepartement();
//        obsDepartement.clear();
        loadDepartementData(comboboxSite.getValue(), admin.getSchool());

        ComboboxDep.getSelectionModel().clearSelection();
        ComboboxDep.getItems().clear();

        ComboboxDepEmp.getSelectionModel().clearSelection();
        ComboboxDepEmp.getItems().clear();

        Proxy proxy = new Proxy();
        DepartementFacadeRemote service = proxy.getDepartement();
        List<Departement> dep = service.findBySiteName(comboboxSite.getValue(), admin.getSchool());
        for (Departement d : dep) {
            ComboboxDep.getItems().add(d.getDepartment_name());
            ComboboxDepEmp.getItems().add(d.getDepartment_name());
        }
        //private JFXComboBox<String> ComboboxDepEmp;
    }

    @FXML
    void newDep(ActionEvent event) {
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
    void newOpt(ActionEvent event) {
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
    void newSite(ActionEvent event) {
        try {

            String file = "/fxml/Site.fxml";
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

    private void initSite() {
        //colNumSite ,colNameSite; Column
        //table siteTable

        colNumSite.setCellValueFactory(new PropertyValueFactory<>("site_id"));
        colNameSite.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadSiteData() {
        Proxy proxy = new Proxy();
        SiteFacadeRemote service = proxy.getSite();

        List<Site> sites = service.findBySchool(admin.getSchool());

        obsSite = FXCollections.observableArrayList(sites);
        siteTable.setItems(obsSite);
    }

    private void initDepartement() {
        //depTable;col_numDep ,colDepName;

        col_numDep.setCellValueFactory(new PropertyValueFactory<>("departement_id"));
        colDepName.setCellValueFactory(new PropertyValueFactory<>("department_name"));
        colHeadDep.setCellValueFactory(new PropertyValueFactory<>("chef.email"));
    }

    private void loadDepartementData(String siteString, School school) {
        Proxy proxy = new Proxy();
        DepartementFacadeRemote service = proxy.getDepartement();
        List<Departement> departements = service.findBySiteName(siteString, school);
        obsDepartement = FXCollections.observableArrayList(departements);
        System.out.println(departements.get(0).getChef());
        depTable.setItems(obsDepartement);
    }

    private void initOptions() {
        //colNumOpt, colOptName;tableOpt

        colNumOpt.setCellValueFactory(new PropertyValueFactory<>("opt_id"));
        colOptName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadOptionData(String depString, School school) {
        Proxy proxy = new Proxy();
        OptFacadeRemote service = proxy.getOption();
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        Departement dep = serviceDep.findByName(ComboboxDep.getValue());
        List<Opt> options = service.findByDepartement(dep);
        obsOption = FXCollections.observableArrayList(options);
        tableOpt.setItems(obsOption);
    }

    private void initEmployee() {
        //colId, colEmpName, colLNameEmp, colRole, email;tableEmpl

        colId.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLNameEmp.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadEmployeeData(String depString, School school) {
        Proxy proxy = new Proxy();
        OptFacadeRemote service = proxy.getOption();
        EmployeeFacadeRemote serviceEmployee = proxy.getEmployeeProxy();
        DepartementFacadeRemote serviceDep = proxy.getDepartement();
        Departement dep = serviceDep.findByName(ComboboxDepEmp.getValue());
        //  List<Opt> options = service.findByDepartement(dep);

        List<Employee> employees = serviceEmployee.findByDepartement(dep);
        obsEmployee = FXCollections.observableArrayList(employees);
        tableEmpl.setItems(obsEmployee);
        
//        obsOption = FXCollections.observableArrayList(options);
//        tableOpt.setItems(obsOption);
    }

    @FXML
    private void newEmployee(ActionEvent event) {
         try {

            String file = "/fxml/newEmployee.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("New Employee");
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
    private void comboDepEm(ActionEvent event) {
        initEmployee();
        loadEmployeeData(ComboboxDepEmp.getValue(), admin.getSchool());
        
    }

    @FXML
    private void newClass(ActionEvent event) {
        try {

            String file = "/fxml/newClasse.fxml";
            FXMLLoader loader = new FXMLLoader();
            final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("New Class");
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
    private void comboOptClass(ActionEvent event) {
        
        //comboboxOptionForClass.getValue()
        initClasses();
        loadDataClasses(comboboxOptionForClass.getValue());
    }
    
    private void initClasses()
    {
        //tableClass;colClassId , colClassName;
//        colClassId.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    
    private void loadDataClasses(String optionName)
    {
        Proxy proxy = new Proxy();
        OptFacadeRemote optionService = proxy.getOption();
        ClassFacadeRemote classService = proxy.getClasse();
        List<Opt> options = optionService.findByName(optionName);
        List<Classe> classes = classService.findClassByOption(options.get(0));
        obsClasses = FXCollections.observableArrayList(classes);
        tableClass.setItems(obsClasses);        
    }

}
