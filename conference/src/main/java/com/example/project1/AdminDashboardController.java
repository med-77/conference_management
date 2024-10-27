package com.example.project1;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class AdminDashboardController implements Initializable {

    @FXML
    private Button SignOutButton;
    @FXML
    private MenuItem userInterface;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem confoStage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    

  

    @FXML
    private void SignOutButtonOnClick(ActionEvent event) {
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


    @FXML
    private void userInterfaceOnClick(ActionEvent event) throws IOException {
        
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("CanManageUserProfile.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        
        Stage window  = new Stage();
        
        window.setTitle("Manage Profile Interface");
        window.setScene(fileChooserViewScene);
        window.show();
        
    }

    /*@FXML
    private void aboutOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Admin about");
        alert.setHeaderText("Information Alert");
        String s ="This is an admin panel...Choose the menu items to do various functions ";
        alert.setContentText(s);
        alert.show();     
    }*/

    @FXML
    private void confoStageOnClick(ActionEvent event) throws IOException {
        
           Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("Conference.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        
        Stage window  = new Stage();
        
        window.setTitle("Conference Management");
        window.setScene(fileChooserViewScene);
        window.show();
        
    }

    public void authorOnClick(ActionEvent actionEvent) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("AuthorDashboard.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);

        Stage window  = new Stage();

        window.setTitle("Author");
        window.setScene(fileChooserViewScene);
        window.show();



    }
}
