package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.pane;
// Java program to illustrate the use of progressbar
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.*;
public class progress extends Application {
  
    static double ii = 0;
  
    // launch the application
    public void start(Stage s) throws Exception
    {
        // set title for the stage
        s.setTitle("creating progressbar");
  
        // create a progressbar
        ProgressBar pb = new ProgressBar();
  
        // create a tile pane
        TilePane r = new TilePane();
  
        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // set progress to different level of progressbar
                ii += 0.1;
                pb.setProgress(ii);
            }
  
        };
  
        // creating button
        Button b = new Button("increase");
  
        // set on action
        b.setOnAction(event);
  
        // add button
        r.getChildren().add(pb);
        r.getChildren().add(b);
  
        // create a scene
        Scene sc = new Scene(r, 200, 200);
  
        // set the scene
        s.setScene(sc);
  
        s.show();
    }
  
    public static void main(String args[])
    {
        // launch the application
        launch(args);
    }
}