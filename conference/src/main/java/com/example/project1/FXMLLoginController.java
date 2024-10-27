package com.example.project1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Pranto
 */
public class FXMLLoginController implements Initializable {

    private Label label;
    @FXML
    private TextField idtextField;
    @FXML
    private TextField passwordField;

    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void idtextFieldOnClick(ActionEvent event) {
        idtextField.setText(null);
    }

    @FXML
    private void passwordFieldOnClick(ActionEvent event) {
        passwordField.setText(null);
    }



    @FXML
    private void loginButtonOnClick(ActionEvent event) throws SQLException {
        String name = idtextField.getText();
        String pass = passwordField.getText();
        int id=0;
        int ids;
        boolean isAdmin = name.equals("admin") && pass.equals("admin");


        if (isAdmin) {
            loadAdminDashboard(event);
            return;
        }
if(database.checkUserType(name,pass).equals("Participant")){
id=database.getParticipantIdByEmail(name);

            loadParticipantDashboard(event,id);

        } else if (database.checkUserType(name,pass).equals("Reviewer")) {
    ids=database.getreviwerIdByEmail(name);
    System.out.println(ids);
            loadReviewerDashboard(event,ids);
        }else {
            showAlert("Invalid User Type", "Please select a user type.");
        }
    }

    private void loadAdminDashboard(ActionEvent event) {
        loadScene("AdminDashboard.fxml", event);
    }

    private void loadParticipantDashboard(ActionEvent event,int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("participant.fxml"));
            Parent sceneParent = loader.load();
            participantController participantController = loader.getController();

            participantController.setid(id);
            Scene scene = new Scene(sceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadReviewerDashboard(ActionEvent event ,int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewerDashboard.fxml"));
            Parent sceneParent = loader.load();
            ReviewerDashboardController participantController = loader.getController();

            participantController.setid(id);
            Scene scene = new Scene(sceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
            Parent sceneParent = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(sceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void signupButtonOnClick(ActionEvent event) {
        Parent scene2Parent = null;
        try {
            scene2Parent = FXMLLoader.load(getClass().getResource("CanSignUp.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }
}
