package com.example.loginsignup;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;


public class DBUtil { //utility class comunicazione con il database, signup utente, log in e cambia scenes
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null; //base class per il node con children nel scene, sarà la prima scene che si vedrà

        if (username != null ){ //se null, sta cambiando da sign up a login e viceversa
           try{
               FXMLLoader loader = new FXMLLoader(DBUtil.class.getResource(fxmlFile));//fxml loader carica scene da fxml file
               root = loader.load();//trasformo FXML files in oggetti che possono essere esposti nella window, passo oggetto ritornato alla root
               //LoggedinController LoggedinController = loader.getController();//passo userame e data alla prossima window, dalla login page
               //logincontroller.setUserInformation(username); metodo optional da login controller

           } catch(IOException e){
               e.printStackTrace();
           }
        } else{      //signup page senza ritornare al controller, perche non passa info da DB, cambia solo scena a signup
            try{
                root = FXMLLoader.load(DBUtil.class.getResource(fxmlFile));
            } catch(IOException e){
                e.printStackTrace();
                                    //set scene di stage
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();//double cast, uso event del button, uso il suo source e uso la scene come node e la metto in questa window
        stage.setTitle(title);
        stage.setScene(new Scene(root,  600, 400)); //v: width , v1: height
        stage.show();

    }

    //signup user
    public static void signUpUser(ActionEvent actionEvent, String username, String password){
        Connection connection = null; //connessione al DB
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;//controlla se user è gia in DB
        ResultSet resultSet = null; //ritorna data dal DB quando lo richiamo

        try{ //istaura connessione al DB    26012022 "java.sql.sqlexception no suitable driver found for jdbc" risolto aggiunto ":" in "mysql://
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "rootchannel()"); //prova a connettersi al DB, passandogli 3 parametri 27012022 usare password del root in mysqlbenchmark
                psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?"); // da rivedere prepared satement più sicuro, controlla se c'è gia in DB 26012022 Risolto
                //psCheckUserExists = connection.prepareStatement("GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'rootchannel()' WITH GRANT OPTION;"); //26012022 da StackOverflow
                psCheckUserExists.setString(1, username); //cotrolla il primo "?" per il relativo username inserito e se esiste effettivamente nel DB
                resultSet = psCheckUserExists.executeQuery(); //se vuoto, user non eiste e può scriersi con quel username inserito

                if(resultSet.isBeforeFirst()) {
                    System.out.println("User gia in esistenza");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Non puoi usare questo username");
                    alert.show();
                } else{
                    psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                    psInsert.setString(1, username);
                    psInsert.setString(2, password);
                    psInsert.executeUpdate(); //refresh senza ritornare niente, update

                    changeScene(actionEvent, "loggedin.fxml", "Welcome!", username );

                }



        }catch(SQLException e){ //controllo in sql
            e.printStackTrace();

            //54:08 25012022
        } finally {

            if(resultSet != null){ //chiudo la connessione con close, per ogni ps e connessione
                try {           //chiudo in ordine resultset, preparedstatement e connection
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (psInsert != null){
                try{
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
        //chiudo db connection per non danneggiare i dati
    }

    public static void logInUser(ActionEvent actionEvent, String username, String password){//funzionalità di login
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{ //connessione DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "rootchannel()"); //27012022 password per sql corretta
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?"); //seleziono PW dove username è uguale a quello che user ha scritto
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()) { //controllo se username è nel DB, opposto al vero
                System.out.println("user not found in database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else { //se invece username esiste, confronta la pw in DB con quella scrita dal user
                while(resultSet.next()) { //loop it
                    String retrievePassword = resultSet.getString("password"); //nome della colonna in DB è password
                    //stessa command line per ogni caratteristica del player salvata in DB dello user
                    if(retrievePassword.equals(password)){
                        changeScene(actionEvent, "loggedin.fxml", "Welcome", username); //messaggio di login corretto
                    } else{
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e){
            e.printStackTrace();

        } finally { //stesso processo di chiusura per il login

            if(resultSet != null){ //chiudo la connessione con close, per ogni ps e connessione
                try {           //chiudo in ordine resultset, p preparedstatement e connection
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }



        }
    }

