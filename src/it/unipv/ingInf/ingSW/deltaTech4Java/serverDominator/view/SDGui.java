package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import javafx.stage.Stage;

public class SDGui {
	
	public Stage createSDGui(String titolo) {
		
		final Stage primaryStage = new Stage();
		
		primaryStage.setTitle(titolo);
		primaryStage.setIconified(false); // se TRUE lo avvia ridotto a icona
		primaryStage.setFullScreen(true); // apre in full screen
		primaryStage.setX(0); // definisce la coordinata X dell'angolo in alto a sinistra della finestra
		primaryStage.setY(0); // definisce la coordinata Y dell'angolo in alto a sinistra della finestra
		//primaryStage.centerOnScreen();
		
		return primaryStage;
	}

}
