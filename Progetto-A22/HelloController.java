package com.example.loginsignup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable { //controller per pulsanti login e signup
        //da non confondere con login signup funzioni di DB e log_in sign_up funzioni per cambiare scenes!!!

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private Button button_login;

    @FXML
    private Button button_sign_up;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) { //richiamo metodi per il pulsante di login
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtil.logInUser(actionEvent, tf_username.getText(), tf_password.getText()); //passo tutto da DBUtil per il login
            }
        });

        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtil.changeScene(actionEvent,"signup.fxml", "Sign Up", "null");
            }
        });

    }
}