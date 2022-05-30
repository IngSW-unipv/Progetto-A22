package sreverDominator.view;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.main.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;
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
		Giocatore g1=new Utente("q",0);
		Giocatore g2=new Utente("q",0);
		Giocatore g3=new Utente("q",0);
		Giocatore g4=new Utente("q",0);
		Giocatore g5=new Utente("q",0);
		Giocatore g6=new Utente("q",0);
		Giocatore g7=new Utente("q",0);
		Giocatore g8=new Utente("q",0);
		Giocatore g9=new Utente("q",0);
		Giocatore g10=new Utente("q",0);
		g7.aggiornaPunteggio(1000);
		Classifica c=new Classifica(g1,g2, g3, g4,g5, g6,g7,g8,g9,g10);
		//*solo unn esempio di come va usata PartitaStage*/
		PartitaStage ps=new PartitaStage(c, bU, nodiView, 30, 20,90) {
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
			//metodo che deve essere sovrascritto per far uscire il giocatore 
			//dalla partita quando vuole
			@Override
			public void fineGioco() {
				// TODO Auto-generated method stub
				
			}
		};
		ps.disponiPannelli();
		ps.addAvviso("prova");
		
		ps.show();
		ps.addAttacco("prova", 90000);
		ps.addAttacco("prova", 9000);
		ps.addDifesa("prova", 90000);
		ps.addAvviso("prova");
		for (int i=0;i<100;i++) {
			ps.addAvviso("prova\t"+i);
			
		}
		g5.aggiornaPunteggio(100000);
		c.aggiornaClassifica();
		mainView.stopBot();
	}
	
	public static void main(String[] args) {
		//*solo unn esempio di come va usata PartitaStage*/

		launch(args);
	}

}