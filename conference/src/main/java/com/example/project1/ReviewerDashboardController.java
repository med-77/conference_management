package com.example.project1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ReviewerDashboardController {

    public RadioButton excellRButton;
    public RadioButton goodRButton;
    @FXML
    private TableView<Article> tableAuthor;
    @FXML
    private TableColumn<Article, String> topic;
    @FXML
    private TableColumn<Article, String> pdf;
    @FXML
    private TableColumn<Article, String> participant;
    @FXML
    private TableColumn<Article, String> status;
    private int id;

    @FXML
    private void signOutButtonOnClick(ActionEvent event) {
        Parent mainSceneParent = null;
        try {
            mainSceneParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CanSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene1);
        window.show();
    }
    private void initializeTableColumns() {
        topic.setCellValueFactory(new PropertyValueFactory<>("name"));
        pdf.setCellValueFactory(new PropertyValueFactory<>("pdfFile"));

        participant.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                int participantId = param.getValue().getIdParticipant();
                String participantEmail = database.getEmailByParticipantId(participantId);
                return new SimpleStringProperty(participantEmail);
            }
        });

        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }



    public void setid(int id) {
        this.id=id;
        System.out.println(id);
    }



    public void submitPaperStatusButtonOnClick(ActionEvent actionEvent) {
        Article selectedArticle = tableAuthor.getSelectionModel().getSelectedItem();

        if (selectedArticle != null) {
            String newStatus = "";

            if (excellRButton.isSelected()) {
                newStatus = "Accepted";
            } else if (goodRButton.isSelected()) {
                newStatus = "Rejected";
            }

            if (!newStatus.isEmpty()) {
                try {
                    // Update the status of the selected article
                    selectedArticle.setStatus(newStatus);
                    database.updateArticleStatus(selectedArticle.getId(), newStatus);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // Inform the user to select a status
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Status Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a status (Accepted or Rejected).");
                alert.showAndWait();
            }
        } else {
            // Inform the user to select an article
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Article Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an article from the table.");
            alert.showAndWait();
        }
    }

    private void displayAllArticles() {
        try {
            List<Article> allArticles = database.getArticlesByReviewerId(id);
            System.out.println(id);
            System.out.println(allArticles.toString());
            tableAuthor.getItems().clear();
            tableAuthor.getItems().addAll(allArticles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showallthearticales(ActionEvent actionEvent) {
        initializeTableColumns();
        displayAllArticles();
    }

    public void radioButtonOnClickaccpet(ActionEvent actionEvent) {
    }

    public void radioButtonOnClickreject(ActionEvent actionEvent) {
    }
}
