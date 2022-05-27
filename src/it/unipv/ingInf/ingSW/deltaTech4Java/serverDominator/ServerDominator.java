package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller.ControllerFacade;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerDominator extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		@SuppressWarnings("unused")
		ControllerFacade serverDominator=new ControllerFacade();

	}

}
