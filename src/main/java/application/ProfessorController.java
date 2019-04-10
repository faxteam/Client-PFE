
package application;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Interfaces.ICategorieRemote;
import Interfaces.IEmployeRemote;
import Interfaces.IpfeformRemote;
import entities.Category;
import entities.PFE_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ProfessorController {

	//Add Category
	@FXML
	private AnchorPane employeePane;

	@FXML
	private JFXButton btn_add_categories;

	@FXML
	private AnchorPane Category_add_Pane;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXButton cancelButton;

	//Choose Category

	@FXML
	AnchorPane Choose_category_Pane;

	@FXML
	ComboBox<String> combo;

	@FXML
	JFXButton  saveButton1;

	//My internships


	@FXML
	private AnchorPane MyinternshipPane;

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
	private JFXButton btnSup;

	@FXML
	private JFXButton btnRep;
	@FXML
	AnchorPane Panebtns;

	//Addd Category
	@FXML
	void addCategory(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();

			String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
			ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
			Category c=new Category();
			System.out.println("long ="+name.getText().length());

			if(name.getText().length()!=0){
				System.out.println(name.getText());
				if(proxy3.existe(name.getText()).size()==0){
					c.setCategory_name(name.getText());
					proxy3.ajouter_categorie(c);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(" information");
					alert.setHeaderText(null);
					alert.setContentText("Category added !");
					alert.show();}
				else{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(" information");
					alert.setHeaderText(null);
					alert.setContentText("Category exists !");
					alert.show();

				}
			}
			else{ 
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle(" information");
				alert.setHeaderText(null);
				alert.setContentText("insert a name !");
				alert.show();

			}}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void show_categories_add_action(ActionEvent event) {
		Category_add_Pane.setVisible(true);
		Choose_category_Pane.setVisible(false);
		MyinternshipPane.setVisible(false);


	}

	//Choisir Category

	@FXML
	void show_categories_toChoose_action(ActionEvent evt) throws NamingException{
		Category_add_Pane.setVisible(false);
		Choose_category_Pane.setVisible(true);
		MyinternshipPane.setVisible(false);
		


		Context context=new InitialContext();
		String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
		ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
		List<Category> l=proxy3.categorie_accepter();
		System.out.println(l.get(0).getCategory_name());
		for(int i=0;i<l.size();i++){
			combo.getItems().addAll(l.get(i).getCategory_name());
		}
	}



	@FXML
	void choisir( ActionEvent evt) throws NamingException{
		Context context=new InitialContext();
		String jndiName2="pidev-ear/pidev-ejb/EmployeService!Interfaces.IEmployeRemote";
		IEmployeRemote proxy2=(IEmployeRemote) context.lookup(jndiName2);
		String nom_c=combo.getValue();
		System.out.println("le nom ="+combo.getValue());

		if(nom_c!=null){

			String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
			ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
			List<Category> c=proxy3.existe(nom_c);
			proxy2.choisir_categorie(c.get(0).getCategory_id(), 1);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle(" information");
			alert.setHeaderText(null);
			alert.setContentText("Category added !");
			alert.show();
		}
		else{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(" information");
			alert.setHeaderText(null);
			alert.setContentText("Choose a category !");
			alert.show();
		}
	}
	//My internnships
	@FXML
	void Show_my_internships_action(ActionEvent event) {
		MyinternshipPane.setVisible(true);
		Category_add_Pane.setVisible(false);
		Choose_category_Pane.setVisible(false);
		Panebtns.setVisible(true);
		sup_pane.setVisible(false);

	}
	@FXML
	void showSupAction (ActionEvent event) throws NamingException {

	
		sup_pane.setVisible(true);
		Panebtns.setVisible(false);
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
		Panebtns.setVisible(false);


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

