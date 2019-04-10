package application;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Interfaces.ICategorieRemote;
import Interfaces.IFormsRemote;
import entities.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class categoryController {
	@FXML
	JFXTextField name;
	@FXML
	JFXButton saveButton;
	@FXML
	JFXButton cancelButton;

	@FXML
	AnchorPane addCategoryPane;
	@FXML
	void addCategory(ActionEvent e) throws NamingException{
		Context context=new InitialContext();
		String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
		ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
		Category c=new Category();
		if(name.getText()!=null){
			c.setCategory_name(name.getText());
			if(proxy3.existe(name.getText())!=null){
				proxy3.ajouter_categorie(c);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle(" information");
				alert.setHeaderText(null);
				alert.setContentText("Category added !");
				alert.show();
			}
			else{ Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(" information");
			alert.setHeaderText(null);
			alert.setContentText("Category existed !");
			alert.show();

			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(" information");
			alert.setHeaderText(null);
			alert.setContentText("Enter a name !");
			alert.show();
		}

	}
}
