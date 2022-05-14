package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import java.util.TimerTask;
import java.util.Timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author para
 * 
 */

// per la compilazione passare alla VM i parametri: --add-modules javafx.controls,javafx.base,javafx.graphichs,javafx.fxml
// per inserirli: Run>RunConfiguration>(x)=>VMarguments

public class BoxTester extends Application {
	int i = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		ProgressBarConteiner p= new ProgressBarConteiner();
		ScrollPane sp=new ScrollPane();
		p.setBackground(
				new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), new Insets(10, 10, 10, 10))));

		String battleTitle1 = new String("User attacca xBot in nodo (4,1)");
		long durata1 = 4000;
		p.addElement(battleTitle1, durata1);

		String battleTitle2 = new String("User attacca xBot in nodo (6,2)");
		long durata2 = 6000;
		p.addElement(battleTitle2, durata2);

		String battleTitle3 = new String("User attacca xBot in nodo (7,3)");
		long durata3 = 7000;
		String battleTitle4 = new String("User attacca xBot in nodo (8,4)");
		long durata4 = 8000;
		String battleTitle5 = new String("User attacca xBot in nodo (19,5)");
		long durata5 = 19000;
		String battleTitle6 = new String("User attacca xBot in nodo (10,6)");
		long durata6 = 10000;


		p.addElement(battleTitle3, durata3);
		p.addElement(battleTitle4, durata4);
		p.addElement(battleTitle5, durata5);
		p.addElement(battleTitle6, durata6);


		sp.setContent(p);
        sp.setPannable(true);;


		Timer t = new Timer();						//creato un timer
		t.scheduleAtFixedRate(new TimerTask() {		// imposto schedulazione del task 

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						p.disponiBattaglie();

					}
				});


			}
		}, 0, 1000);		// la schedulazione parte da 0 e arriva fino a 1000



        Scene s=new Scene(sp);
        primaryStage.setScene(s);
        primaryStage.show();
        String battle = new String("uuu");
		long durat = 50000;
		p.addElement(battle, durat);
		 //*/
	
// ----------------- test del TextBox ------------ //		
		
		ScrollPane sp = new ScrollPane();
		sp.setBackground(new Background(
				new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(10, 10, 10, 10))));
		TextBox txtB = new TextBox(null, sp);
		txtB.insertText("ciao");

		
		Timer t = new Timer(); // creato un timer
		t.scheduleAtFixedRate(new TimerTask() { // imposto schedulazione del task

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						txtB.insertText("ciao");
						i++;
						if (i%10 == 0) {
							txtB.generateFile();
						}
					}
				});

			}
		}, 0, 1000); // la schedulazione parte da 0 e arriva fino a 1000

		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
//*/
	}

	public static void main(String[] args) {
		launch(args);
	}

}
