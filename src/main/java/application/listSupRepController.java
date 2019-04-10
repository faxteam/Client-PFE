package application;


import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Interfaces.IpfeformRemote;
import Services.pfe_form_service;
import entities.PFE_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class listSupRepController {

	@FXML
	private AnchorPane employeePane;
	@FXML
	private Button btnRep;
	@FXML
	private Button btnSup;
	@FXML
	private AnchorPane sup_pane;

	@FXML
	private TableView<PFE_Form> tableView_s;

	@FXML
	private TableColumn<PFE_Form, String> resp_name_s;

	@FXML
	private TableColumn<PFE_Form, String> resp_mail_s;

	@FXML
	private TableColumn<PFE_Form, String> title_pfe_s;

	@FXML
	private TableColumn<PFE_Form, String> prob_s;
	@FXML
	void showSupAction (ActionEvent event) throws NamingException {

		sup_pane.setVisible(true);
		Context context=new InitialContext();
		String jndiName1="pidev-ear/pidev-ejb/pfe_form_service!Interfaces.IpfeformRemote";
		IpfeformRemote proxy1=(IpfeformRemote) context.lookup(jndiName1);
		List<PFE_Form> l=proxy1.afficher_supervision(2);
		if(l.size()!=0)
		{
			ObservableList<PFE_Form> l1=FXCollections.observableArrayList();
			l1.addAll(l);
			for(int i=0;i<l.size();i++){
				resp_mail_s.setCellValueFactory(new PropertyValueFactory<>("responsibleEmail"));
				resp_name_s.setCellValueFactory(new PropertyValueFactory<>("responsibleFirstName"));
				title_pfe_s.setCellValueFactory(new PropertyValueFactory<>("title"));
				prob_s.setCellValueFactory(new PropertyValueFactory<>("problematic"));
				tableView_s.setItems(l1);



			}
			}
		else{Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(" information");
		alert.setHeaderText(null);
		alert.setContentText("No data to show !");
		alert.show();}
	}

	@FXML
	void showRepAction(ActionEvent event) throws NamingException {
		sup_pane.setVisible(true);
		Context context=new InitialContext();
		String jndiName1="pidev-ear/pidev-ejb/pfe_form_service!Interfaces.IpfeformRemote";
		IpfeformRemote proxy1=(IpfeformRemote) context.lookup(jndiName1);
		List<PFE_Form> l=proxy1.afficher_reporter(1);
		if(l.size()!=0)
		{
			ObservableList<PFE_Form> l1=FXCollections.observableArrayList();
			l1.addAll(l);
			for(int i=0;i<l.size();i++){
				resp_mail_s.setCellValueFactory(new PropertyValueFactory<>("responsibleEmail"));
				resp_name_s.setCellValueFactory(new PropertyValueFactory<>("responsibleFirstName"));
				title_pfe_s.setCellValueFactory(new PropertyValueFactory<>("title"));
				prob_s.setCellValueFactory(new PropertyValueFactory<>("problematic"));
				tableView_s.setItems(l1);



			}
			}
		else{Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(" information");
		alert.setHeaderText(null);
		alert.setContentText("No data to show !");
		alert.show();}
	}

	}


