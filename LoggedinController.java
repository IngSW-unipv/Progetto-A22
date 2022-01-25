package com.example.loginsignup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedinController implements Initializable{

    @FXML
    private Button button_logout; //collegato al FXML

    @FXML
    private Label label_welcome;


    //listener logout button, cambia in login screen
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtil.changeScene(actionEvent, "sample.fxml", "Log in!", "null" ); //logout porta alla pagina di accesso sample.fxml
            }
        });

        //custom labeled info 34:57 nel video
        //public void setUserInformation(String username){

       // }
    }





}
