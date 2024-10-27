package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class CanManageUserProfileController {
    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private TextField institution;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<String> conference;
    @FXML
    private TableView<KeySpeaker> table;
    @FXML
    private TableColumn<KeySpeaker, String> nameColumn;
    @FXML
    private TableColumn<KeySpeaker, String> countryColumn;
    @FXML
    private TableColumn<KeySpeaker, String> institutionColumn;
    @FXML
    private TableColumn<KeySpeaker, String> titleColumn;

    @FXML
    private void addKeySpeakerButtonOnClick(ActionEvent event) throws SQLException {
        loadConference();
        String speakerName = name.getText();
        String speakerCountry = country.getText();
        String speakerInstitution = institution.getText();
        String speakerTitle = title.getText();
        String selectedConference = conference.getValue();

        if (speakerName.isEmpty() || speakerCountry.isEmpty() || speakerInstitution.isEmpty() || speakerTitle.isEmpty() || selectedConference == null) {
            // Show an alert or message indicating that all fields are required
            System.out.println("Please fill in all fields.");
            return;
        }


        boolean success = database.addKeySpeaker(speakerName, speakerCountry, speakerInstitution, speakerTitle, selectedConference);

        if (success) {
            // Update the UI or show a success message
            System.out.println("Key speaker added successfully.");
            // You may want to refresh the table or update the UI in some way
            refreshTable(); // Example method to refresh the table
        } else {
            // Show an error message
            System.out.println("Failed to add key speaker.");
        }
    }

    // Example method to refresh the table after adding a key speaker
    private void refreshTable() throws SQLException {
        // Clear existing items and fetch updated data from the database
        table.getItems().clear();
        List<KeySpeaker> keySpeakers = database.getAllKeySpeakers();
        table.getItems().addAll(keySpeakers);
    }

    private void loadConference() throws SQLException {
        List<String> reviewerEmails = database.getAllConferenceTopics();
        conference.getItems().addAll(reviewerEmails);
    }
}


