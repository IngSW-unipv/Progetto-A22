package com.example.fxmltojavafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/*** Author : Gian Castillo
 *  interfaccia Logged In dopo aver effettuato con successo il set-up o login nel sistema con database
 */
public class LoggedIn {
    private VBox root;

    public LoggedIn(){

        root = new VBox(10); //inserendo int nell'istanza vuol dire inserire uno spazio uniforme tra gli elementi
        root.setAlignment(Pos.CENTER); //allinea tutti gli elementi all'interno della VBox
        root.getChildren().add(new Label("SERVER DOMINATOR : choose difficulty"));

        //buttons per la scelta genera mappa
        
        final Button buttonEasy = new Button("EASY");
        //LoggedInController.methodName();
        buttonEasy.setOnAction(this); //comando che connette controller al pulsante
        root.getChildren().add(buttonEasy);

        final Button buttonMedium = new Button("MEDIUM");
        root.getChildren().add(buttonMedium);

        final Button buttonHard = new Button("HARD");
        root.getChildren().add(buttonHard);
    }

    public Parent getRoot() {
        return root ;
    }


}
