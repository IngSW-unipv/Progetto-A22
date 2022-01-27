package com.example.loginsignup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage.setTitle("Log in");
        stage.setScene(new Scene(root, 600, 400));
        stage.show(); //1:14:24

    }

    public static void main(String[] args) {
        launch(args);
    }
}