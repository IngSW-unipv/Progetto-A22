package sreverDominator.view;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import javafx.application.Application;
import javafx.stage.Stage;


//IMportante leggere i commenti
//*solo unn esempio di come va usata PartitaStage*/
public  class PartitaStageTester extends Application{
	//*solo unn esempio di come va usata PartitaStage*/

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainDefinitivo mainView = new MainDefinitivo();
		try {
			mainView.avvioPartita(30, 20, "Matteo", 10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 
		//IMportante leggere i commenti
		Giocatore[] gioc = mainView.getGiocatori();
		Nodo baseU = mainView.getTabellone().trovaBase(gioc[1]);
		Base bU = new Base(baseU.getPossessore());
		//IMportante leggere i commenti
		MappaDefinitiva mappa = new MappaDefinitiva(30, 20, gioc);
		mappa.assegnamento(gioc.length-1, gioc);
		Nodo[][] nodiView = mappa.getMap();
		//IMportante leggere i commenti
		Giocatore g1=new Utente("TawaHabib",0);
		Giocatore g2=new Utente("Lucac999",0);
		Giocatore g3=new Utente("Gian",0);
		Giocatore g4=new Utente("MatteoPara",0);
		Giocatore g5=new Utente("PippCalzeLunghe158",0);
		Giocatore g6=new Utente("Matteoc",0);
		Giocatore g7=new Utente("marco",0);
		Giocatore g8=new Utente("PippCalzeLunghe1588",0);
		Giocatore g9=new Utente("Matteoc9",0);
		Giocatore g10=new Utente("marco10",0);
		g7.aggiornaPunteggio(1000);
		Classifica c=new Classifica(g1,g2, g3, g4,g5, g6,g7,g8,g9,g10);
		//*solo unn esempio di come va usata PartitaStage*/
		PartitaStage ps=new PartitaStage(c, bU, nodiView, 30, 20) {
			@Override 
			
			public void doOnClic() {
				//IMportante leggere i commenti
				// TODO Auto-generated method stub
				// non fa niene di aggiuntico
				// RIVOLTO A GIAN: QUI PUOI METTERE LE ISTRUZIONI (AGGIUNTIVE) CHE
				// VENGONO ESEGUITE QUANDO IL GIOCATORECLICCA SU UN NODO 
				//(ESEMPIO LE POLITICHE E I CONTROLLI CHE SI FANNO PER ATTIVAZIONE DEI PULSANTI)
				//SOLO A TITOLO DIMOSTRATIVO STAMPO LE COORDINATE DEL NODO CLICCATO
				System.out.println("coordinate del nodo secondo la convenzione matriciale\t"
				+this.getSelectedPoint().getIntX()+"\t"+this.getSelectedPoint().getIntY());
			}
		};
		ps.disponiPannelli();
		ps.addAvviso("prova");
		ps.show();
		ps.addAvviso("prova");
		for (int i=0;i<100;i++) {
			ps.addAvviso("prova\t"+i);
			
		}
		g5.aggiornaPunteggio(100000);
		c.aggiornaClassifica();
	}
	
	public static void main(String[] args) {
		//*solo unn esempio di come va usata PartitaStage*/

		launch(args);
	}

}
