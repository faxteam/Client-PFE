package tn.esprit.PFE.Main;



import entities.Admin;
import entities.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.NamingException;

public class MainApp extends Application {

    private static final double WIN_WIDTH = 800;
    private static final double WIN_HEIGHT = 600;
    public static final String PATH="tn/esprit/PFE/GUI/";
    public static Admin admin;
    public static Employee employee;
    @Override
    public void start(Stage stage) {
        try{
            
        String file = "/fxml/Login.fxml";
         FXMLLoader loader = new FXMLLoader();
        final Parent root = (Parent) loader.load(getClass().getResourceAsStream(file));
        //final Parent root = FXMLLoader.load(getClass().getResourceAsStream(file));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/styles/Styles.css");

        //stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        } catch(Throwable e)
        {
            System.out.println("error " + e.getMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("local " + e.getLocalizedMessage());
            System.out.println("stack trace " + e.getStackTrace());
        }
    }

    
    public static void main(String[] args) throws NamingException, InterruptedException {
        launch(args);
    }

}
