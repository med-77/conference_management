package com.example.project1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.List;

public class AuthorDashboardController {

    @FXML
    private ComboBox<String> reviewer;
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

    private void displayAllArticles() {
        try {
            List<Article> allArticles = database.getAllArticlesWithParticipants();
            System.out.println(allArticles.toString());
            tableAuthor.getItems().clear();
            tableAuthor.getItems().addAll(allArticles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showArticlesButtonOnClick(ActionEvent actionEvent) {
        displayAllArticles();
        initializeTableColumns();
        loadReviewerEmails();

    }

    public void trackPaperButtonOnClick(ActionEvent actionEvent) {
        Article selectedArticle = tableAuthor.getSelectionModel().getSelectedItem();

        if (selectedArticle != null) {
            int selectedArticleId = selectedArticle.getId();

            String selectedReviewerEmail = reviewer.getValue();
            try {
                int selectedReviewerId = database.getReviewerIdByEmail(selectedReviewerEmail);
                database.updateArticleReviewerAndStatus(selectedArticleId, selectedReviewerId);
            } catch (SQLException e) {
                e.printStackTrace();
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




    private void loadReviewerEmails() {
        List<String> reviewerEmails = database.getAllReviewerEmails();
        reviewer.getItems().addAll(reviewerEmails);
    }
}

