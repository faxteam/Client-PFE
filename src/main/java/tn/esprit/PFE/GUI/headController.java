/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXButton;
import entities.Departement;
import entities.Employee;
import iservice.DepartementFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.OptFacadeRemote;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.PFE.Utils.Proxy;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class headController implements Initializable {

    @FXML
    private AnchorPane centerAnchore;
    @FXML
    private TableView<Employee> siteTable1;
    @FXML
    private TableColumn<Employee, String> colNumSite1, colNameSite1, role, email;
    @FXML
    private JFXButton addignBtn;
    private static ObservableList<Employee> obsListEmployee;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addignBtn.setDisable(true);
        initData();
        loadData();
        FXRouter.when("assign" , "Assign.fxml");
        FXRouter.setRouteContainer("assign", centerAnchore);
    } 
    
    private void initData()
    {
        colNumSite1.setCellValueFactory(new PropertyValueFactory<>("password"));
        colNameSite1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    
    private void loadData()
    {
        Proxy proxy = new Proxy();
        EmployeeFacadeRemote serviceEmployee = proxy.getEmployeeProxy();
        List<Employee> employees = serviceEmployee.findByRole(Employee.Role.departement_supervisor);
        obsListEmployee = FXCollections.observableArrayList(employees);
        siteTable1.setItems(obsListEmployee);
    }

    @FXML
    private void assign(ActionEvent event) {
    }

    public Employee selectedEmployee()
    {
        Employee e = new Employee();
        e = siteTable1.getSelectionModel().getSelectedItem();
        System.out.println("selected one is :: " + e);
        return e;
    }
    
    
    
    @FXML
    private void getSelectedEmployee(MouseEvent event) throws IOException {
        addignBtn.setDisable(false);
        System.out.println("selected one is :: " + this.selectedEmployee());
        FXRouter.goTo("assign", this.selectedEmployee());
    }
     
    
    
}
