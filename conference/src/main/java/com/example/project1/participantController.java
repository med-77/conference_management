package com.example.project1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class participantController  {


    @FXML
    private TableView<Conference> conferenceTable;

    @FXML
    private TableColumn<Conference, String> name;

    @FXML
    private TableColumn<Conference, String> topic;

    @FXML
    private TableColumn<Conference, Double> price;

    @FXML
    private TableColumn<Conference, String> place;

    @FXML
    private TableColumn<Conference, Date> startDate;

    @FXML
    private TableColumn<Conference, Date> endDate;

    @FXML
    private TableColumn<Conference, Date> applyStart;
   private  int selectedConferenceId;
    @FXML
    private TableColumn<Conference, Date> applyEnd;
    private int ids;
    public void setid(int id) {
        System.out.println(id);
        this.ids =id;
        System.out.println(id);

    }




    @FXML
    private void signOutButtonOnClick(ActionEvent event) {
        Parent mainSceneParent = null;
        try {
            mainSceneParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CanSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene1);
        window.show();
    }
    private void setupRowClickListener() {
        conferenceTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Conference> observable, Conference oldValue, Conference newValue) -> {
            if (newValue != null) {
                 selectedConferenceId = newValue.getId(); // Assuming getId() returns the conference ID
            }
        });
    }

    private void initializeTableColumns() {
        name.setCellValueFactory(new PropertyValueFactory<>("confoName"));
        topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        applyStart.setCellValueFactory(new PropertyValueFactory<>("applyDateStart"));
        applyEnd.setCellValueFactory(new PropertyValueFactory<>("applyDateFinish"));
    }

    private void displayAllConferences() {
        System.out.println("diplay " + ids);

        try {
            List<Conference> allConferences = database.getAllConferences();
            conferenceTable.getItems().clear();
            conferenceTable.getItems().addAll(allConferences);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) throws IOException {
        System.out.println("Before loading payment.fxml: " + selectedConferenceId);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
        Parent fileChooserViewParent = loader.load();
        paymentcontroller paymentController = loader.getController();
        System.out.println("from the loader"+ids);
        paymentController.setid(ids,selectedConferenceId);

        Scene fileChooserViewScene = new Scene(fileChooserViewParent);

        Stage window  = new Stage();

        window.setTitle("Conference Management");
        window.setScene(fileChooserViewScene);
        window.show();
    }


    public void submitshow(ActionEvent actionEvent) {
        initializeTableColumns();

        displayAllConferences();
        setupRowClickListener();

    }


}


