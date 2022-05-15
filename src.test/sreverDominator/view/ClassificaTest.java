package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ClassificaTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScrollPane sp=new ScrollPane();
		Giocatore g1=new Utente("TawaHabib",0);
		Giocatore g2=new Utente("Lucac999",0);
		Giocatore g3=new Utente("Gian",0);
		Giocatore g4=new Utente("MatteoPara",0);
		Giocatore g5=new Utente("PippCalzeLunghe158",0);
		Giocatore g6=new Utente("Matteoc",0);
		Giocatore g7=new Utente("marco",0);
		g7.aggiornaPunteggio(1000);
		Classifica c=new Classifica(g1,g2, g3, g4,g5, g6);
		ClassificaPane cp=new ClassificaPane(c);
		sp.setContent(cp);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		cp.dispone();
		g5.setPunteggio(800);
		g1.setPunteggio(820);
		g2.setPunteggio(820);
		c.aggiornaClassifica();
		
		c.aggiungiUtente(g7);
		g5.setPunteggio(10000);
		c.aggiornaClassifica();
		//c.removeUtente(0);
		System.out.println(c.getLista().toString());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
