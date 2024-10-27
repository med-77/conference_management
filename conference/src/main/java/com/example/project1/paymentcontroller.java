package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class paymentcontroller {

    public RadioButton check;
    public RadioButton bankTransfer;
    public RadioButton order;
    public RadioButton cash;

    public Button submit;
    private int id;
    private int conferenceId;

    public void setid(int id, int selectedConferenceId) {
        System.out.println(id);
this.conferenceId=selectedConferenceId  ;
    this.id=id;
    }
    @FXML
    private void upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            System.out.println("Selected PDF file: " + selectedFile.getAbsolutePath());
            try {
                database.insertPDFFile(id, conferenceId, selectedFile.getName(), selectedFile);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No PDF file selected.");
        }
    }
}
