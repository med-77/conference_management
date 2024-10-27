package com.example.project1;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConferenceController implements Initializable {


    @FXML private TableView<Conference> table;
    @FXML private TableColumn<Conference, String> tableConfoName;
    @FXML private TableColumn<Conference, Integer> tableconfoID;
    @FXML private TableColumn<Conference, String> tableTopic;


    @FXML private TableColumn<Conference, String> tablePlace;
    @FXML private TableColumn<Conference, Date> tableStartDate;
    @FXML private TableColumn<Conference, Date> tableEndDate;
    @FXML private TableColumn<Conference, Date> TableStartApply;
    @FXML private TableColumn<Conference, Date> TableEndApply;
    @FXML private TextField confoNameField;
    @FXML private TextField topicField;

    @FXML private Button createConferenceButton;
    @FXML private Button showAllConferenceButton;
    @FXML private Button showThisConferenceButton;
    @FXML private TextField showThisConfoField;
    @FXML private Button countryReportButton;
    @FXML private Button topicReportButton;
    @FXML private PieChart countryPieChart;
    @FXML private BarChart<?, ?> topicBarChart;
    @FXML private TextField startDate;
    @FXML private TextField endDate;
    @FXML private TextField applyDateEnd;
    @FXML private TextField applyDateStart;
    @FXML private TextField place;
    @FXML private TextField price;
    @FXML private TableColumn<Conference, Double> Tableprice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeTableColumns();
    }



    @FXML
    private void createConferenceButtonOnClick(ActionEvent event) throws ParseException, SQLException {

        createConferenceFromInput();
    }

    @FXML
    private void showAllConferenceButtonOnClick(ActionEvent event) {
        displayAllConferences();
    }

    @FXML
    private void showThisConferenceButtonOnClick(ActionEvent event) throws SQLException {
        int id =Integer.parseInt(showThisConfoField.getText());
        Conference f=database.getConferenceById(id);
        table.getItems().clear();
        table.getItems().addAll(f);
        showThisConfoField.clear();

    }

    @FXML
    private void countryReportButtonOnClick(ActionEvent event) {

    }

    @FXML
    private void topicReportButtonOnClick(ActionEvent event) {
    }


    private void initializeTableColumns() {
        tableConfoName.setCellValueFactory(new PropertyValueFactory<>("confoName"));
        tableconfoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableTopic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        Tableprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tablePlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        tableStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TableStartApply.setCellValueFactory(new PropertyValueFactory<>("applyDateStart"));
        TableEndApply.setCellValueFactory(new PropertyValueFactory<>("applyDateFinish"));
    }

    private void createConferenceFromInput() throws ParseException, SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDateValue = formatter.parse(startDate.getText());
        Date endDateValue = formatter.parse(endDate.getText());
        Date applyDateStartValue = formatter.parse(applyDateStart.getText());
        Date applyDateEndValue = formatter.parse(applyDateEnd.getText());

        Conference confo = new Conference(0,
                confoNameField.getText(),
                topicField.getText(),
                startDateValue,
                endDateValue,
                applyDateStartValue,
                applyDateEndValue,
                place.getText(),
                Double.parseDouble(price.getText()));
        database.insertConference(confo);
        clearInputFields();

    }

    private void displayAllConferences() {
        try {
            List<Conference> allConferences = database.getAllConferences();
            table.getItems().clear();
            table.getItems().addAll(allConferences);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clearInputFields() {
        confoNameField.clear();
        topicField.clear();
        startDate.clear();
        endDate.clear();
        applyDateStart.clear();
        applyDateEnd.clear();
        place.clear();
        price.clear();
    }
    @FXML
    private void deleteSelectedConferenceButtonOnClick(ActionEvent event) {
        Conference selectedConference = table.getSelectionModel().getSelectedItem();

        if (selectedConference != null) {
            try {
                // Delete the conference from the database
                database.deleteConference(selectedConference.getId());

                // Remove the conference from the TableView
                table.getItems().remove(selectedConference);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Inform the user to select a conference
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Conference Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a conference from the table to delete.");
            alert.showAndWait();
        }
    }
}
