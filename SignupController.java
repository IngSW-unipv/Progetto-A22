package com.example.loginsignup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Button button_signup;

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        //1 09 32 Togglegroup per scegliere solo una option tra tante nell'esempio
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //toggle name String richiamato quando si logga
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){ //se non sono vuoti
                    DBUtil.signUpUser(actionEvent, tf_username.getText(), tf_password.getText());
                } else{
                    System.out.println("Inserisci tutti i dati");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.show();
                }
            }
        });
        //cambia in login page quando click in login button

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtil.changeScene(actionEvent, "sample.fxml", "Log in", null);


            }
        });
    }
}
