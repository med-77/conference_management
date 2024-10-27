package com.example.project1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CanSignUpController implements Initializable {

    @FXML private TextField emailField;
    @FXML private TextField nameField;
    @FXML private TextField institutionField;
    @FXML private TextField passwordField;
    @FXML private TextField clefField;

    @FXML private Button submitButton;
    @FXML private Button backButtonMain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        String username = nameField.getText();
        String email = emailField.getText();
        String institution = institutionField.getText();
        String password = passwordField.getText();
        String clef = clefField.getText();

        if (clef.equals("1111")) {
            boolean insertedReviewer = database.insertReviewer(username, institution, email, password);
            if (insertedReviewer) {
                System.out.println("Insertion successful! Reviewer added.");
            } else {
                System.out.println("Insertion failed.");
            }
        } else {
            boolean insertedParticipant = database.insertParticipant(username, institution, email, password);
            if (insertedParticipant) {
                System.out.println("Insertion successful! Participant added.");
            } else {
                System.out.println("Insertion failed.");
            }
        }
    }

    @FXML
    private void backButtonMainOnClick(ActionEvent event) {
        try {
            Parent mainSceneParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(CanSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
