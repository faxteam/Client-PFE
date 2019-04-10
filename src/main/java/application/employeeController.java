package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import Interfaces.ICategorieRemote;
import Interfaces.IEmployeRemote;
import entities.Category;
import entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class employeeController {
	@FXML
	AnchorPane mainContainer;

	@FXML
	ComboBox<String> combo;
	@FXML
	JFXButton  saveButton;


	@FXML
	void afficher_cat_name (ActionEvent evt) throws NamingException{
		Context context=new InitialContext();
		String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
		ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
		List<Category> l=proxy3.categorie_accepter();
		System.out.println(l.get(0).getCategory_name());
		for(int i=0;i<l.size();i++){
			combo.getItems().addAll(l.get(i).getCategory_name());
		}}




	@FXML
	void choisir( ActionEvent evt) throws NamingException{
		Context context=new InitialContext();
		String jndiName2="pidev-ear/pidev-ejb/EmployeService!Interfaces.IEmployeRemote";
		IEmployeRemote proxy2=(IEmployeRemote) context.lookup(jndiName2);
		String nom_c=combo.getValue();
	//	Category c=
	//	proxy2.choisir_categorie(id_c, id_emp);


		/*Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(" information");
		alert.setHeaderText(null);
		alert.setContentText("Category added !");
		alert.show();*/
	}

}
