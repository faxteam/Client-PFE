/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tn.esprit.PFE.Utils.Router.FXRouter;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class MainController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab bookIssueTab;
    @FXML
    private HBox book_info;
    @FXML
    private JFXTextField bookIDInput;
    @FXML
    private StackPane bookInfoContainer;
    @FXML
    private Text bookName;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookStatus;
    @FXML
    private HBox member_info;
    @FXML
    private JFXTextField memberIDInput;
    @FXML
    private StackPane memberInfoContainer;
    @FXML
    private Text memberName;
    @FXML
    private Text memberMobile;
    @FXML
    private JFXButton btnIssue;
    @FXML
    private Tab renewTab;
    @FXML
    private JFXTextField bookID;
    @FXML
    private JFXButton renewButton;
    @FXML
    private JFXButton submissionButton;
    @FXML
    private HBox submissionDataContainer;
    @FXML
    private Text memberNameHolder;
    @FXML
    private Text memberEmailHolder;
    @FXML
    private Text memberContactHolder;
    @FXML
    private Text bookNameHolder;
    @FXML
    private Text bookAuthorHolder;
    @FXML
    private Text bookPublisherHolder;
    @FXML
    private Text issueDateHolder;
    @FXML
    private Text numberDaysHolder;
    @FXML
    private Text fineInfoHolder;
    @FXML
    private JFXHamburger hamburger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    }    

    @FXML
    private void loadBookInfo(ActionEvent event) {
    }

    @FXML
    private void loadMemberInfo(ActionEvent event) {
    }

    @FXML
    private void handleIssueButtonKeyPress(KeyEvent event) {
    }

    @FXML
    private void loadIssueOperation(ActionEvent event) {
    }

    @FXML
    private void loadBookInfo2(ActionEvent event) {
    }

    @FXML
    private void loadRenewOp(ActionEvent event) {
    }

    @FXML
    private void loadSubmissionOp(ActionEvent event) {
    }

    @FXML
    private void handleMenuSettings(ActionEvent event) {
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
    }

    @FXML
    private void handleMenuAddBook(ActionEvent event) {
    }

    @FXML
    private void handleMenuAddMember(ActionEvent event) {
    }

    @FXML
    private void handleMenuViewBook(ActionEvent event) {
    }

    @FXML
    private void handleMenuViewMemberList(ActionEvent event) {
    }

    @FXML
    private void handleIssuedList(ActionEvent event) {
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
    }

    @FXML
    private void handleMenuOverdueNotification(ActionEvent event) {
    }

    @FXML
    private void handleAboutMenu(ActionEvent event) {
    }
    
}
