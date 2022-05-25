package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class sdGame extends Application{

	@Override
	public void start(Stage arg0) throws Exception {/*
		MainDefinitivo mainDefinitivo=new MainDefinitivo();
		mainDefinitivo.avvioPartita(20, 15, STYLESHEET_CASPIAN, 0);
		PartitaStage partitaStage=new Partita(mainDefinitivo,mainDefinitivo.getTabellone().trovaBase(new Utente(STYLESHEET_CASPIAN, 0)), 30*60*1000);
		partitaStage.show();*/
		//@SuppressWarnings("unused")
		@SuppressWarnings("unused")
		MainController c=new MainController();
	/*	MainDefinitivo mainDefinitivo=new MainDefinitivo();
		mainDefinitivo.avvioPartita(30, 20, "tawa", 0);
		
		PartitaStage partitaStage=new Partita(mainDefinitivo, 
					mainDefinitivo.getTabellone().trovaBase
					(new Utente("tawa", 0)), 5*60*1000);
			
		PartitaController partitaCont = new PartitaController(mainDefinitivo, partitaStage,
			
						(Base)mainDefinitivo.getTabellone().trovaBase(new Utente("tawa", 0)));
		partitaStage.show();
		mainDefinitivo.stopBot();*/
	}
	public static void main(String[] args) {
	launch(args);
}
}
