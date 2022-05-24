package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller.MainController;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.Partita;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class sdGame extends Application{

	@Override
	public void start(Stage arg0) throws Exception {/*
		MainDefinitivo mainDefinitivo=new MainDefinitivo();
		mainDefinitivo.avvioPartita(20, 15, STYLESHEET_CASPIAN, 0);
		PartitaStage partitaStage=new Partita(mainDefinitivo,mainDefinitivo.getTabellone().trovaBase(new Utente(STYLESHEET_CASPIAN, 0)), 30*60*1000);
		partitaStage.show();*/
		MainController c=new MainController();
	}
	public static void main(String[] args) {
	launch(args);
}
}
