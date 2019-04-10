package application;

import java.awt.Desktop.Action;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Interfaces.IpfeformRemote;
import entities.PFE_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShowAcceptedPfeController {
	
	@FXML
	AnchorPane PFEAcceptedPane;
	@FXML
	TableView<PFE_Form> table_pfe_accepted;
	@FXML
	TableColumn<PFE_Form,String> titleCol;
	@FXML
	TableColumn<PFE_Form,String>respensable_client;
	@FXML
	TableColumn<PFE_Form,String>  discription;
	@FXML
	TableColumn<PFE_Form,String> fonctionalite;
	@FXML
	TableColumn<PFE_Form,String> probleme;
	
	@FXML
	JFXButton btn_show;
	@FXML
	void afficher_pfe_accpter(ActionEvent ev) throws NamingException
	{
		 Context context=new InitialContext();
		 String jndiName1="pidev-ear/pidev-ejb/pfe_form_service!Interfaces.IpfeformRemote";
		 IpfeformRemote proxy1=(IpfeformRemote) context.lookup(jndiName1);
		 List<PFE_Form> l=proxy1.form_pfe_accepted();
		 ObservableList<PFE_Form >L1=FXCollections.observableArrayList();
		 if (l.size()!=0){
			 L1.addAll(l);
			 for(int i=0;i<l.size();i++){
				 respensable_client.setCellValueFactory(new PropertyValueFactory<>("responsibleEmail"));
				 table_pfe_accepted.setItems(L1);
			 }
		 }else {
			 Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Internship Form information");
			alert.setHeaderText(null);
			alert.setContentText("Sorry, There isn't Internship Form to show !");
			alert.show();
	}
}}
