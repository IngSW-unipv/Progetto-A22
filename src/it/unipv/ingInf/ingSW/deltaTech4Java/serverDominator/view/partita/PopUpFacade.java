package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita;



import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Development;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Market;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Powerup;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Selectmalware;
import javafx.geometry.Insets;

public class PopUpFacade {

	public static int sX = 400;
	public static int sY = 400;	
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	private Powerup popUpPowerup;
	private Selectmalware popUpSelectmalware;
	private Development popUpDevelopment;
	private Market popUpMarket;
	private Mercato mkt = new Mercato();
	private  Nodo baseUtente;
	
	public PopUpFacade(Mercato mercato, Nodo baseUtente ) {
		this.mkt=mercato;
		this.baseUtente=baseUtente;
		this.popUpPowerup =new Powerup(baseUtente);
		this.popUpSelectmalware=new Selectmalware(baseUtente);
		this.popUpDevelopment= new Development(baseUtente);
		this.popUpMarket=new Market(mkt,baseUtente); 
		
	}
	
	public PopUpFacade() {
		super();
		this.mkt=new Mercato();
	}

	/**
	 * Metodo dentro il Pop up di azione, inizializza la scelta del malware per attaccare il nodo selezionato
	 * @param baseUtente
	 */
	public void avviaSelectMalware(Nodo baseUtente) {
		popUpSelectmalware.setBaseUtente(baseUtente);
		this.baseUtente=baseUtente;
		popUpSelectmalware.selectMalware();
		
	}

	/**
	 * Metodo dentro il pop up di azione, inizializza lo produzione di software
	 * @param baseUtente
	 */
	public void avviaDevelopment(Nodo baseUtente) {
		popUpDevelopment.setNodoUtente(baseUtente);;
		popUpDevelopment.development();
	}
	
	/**
	 * Metodo dentro il pop up di azione, inizializza il potenziamento delle risorse
	 * @param baseUtente
	 */
	
	public void avviaPowerUp(Nodo baseUtente) {
		popUpPowerup.setNodoUtente(baseUtente);
		popUpPowerup.powerUp();	
	}
	
	/**
	 * Metodo che inizializza l'interfaccia di mercato
	 * @param baseUtente
	 */
	
	public void avviaMarket(Nodo baseUtente) {
		popUpMarket.setBaseUtente(baseUtente);
		popUpMarket.market();
	}
	
	/**
	 * Metodo per la selezione del malware di attacco
	 */
	
	public void avviaSelectMalware() {
		popUpSelectmalware.selectMalware();
		
	}
	
	/**
	 * Metodo per la
	 */

	public void avviaDevelopment() {
		popUpDevelopment.development();
	}
	
	public void avviaPowerUp() {
		popUpPowerup.powerUp();	
	}
	
	public void avviaMarket() {
		popUpMarket.market();
	}

	public Powerup getPopUpPowerup() {
		return popUpPowerup;
	}

	public void setPopUpPowerup(Powerup popUpPowerup) {
		this.popUpPowerup = popUpPowerup;
	}

	public Selectmalware getPopUpSelectmalware() {
		return popUpSelectmalware;
	}

	public void setPopUpSelectmalware(Selectmalware popUpSelectmalware) {
		this.popUpSelectmalware = popUpSelectmalware;
	}

	public Development getPopUpDevelopment() {
		return popUpDevelopment;
	}

	public void setPopUpDevelopment(Development popUpDevelopment) {
		this.popUpDevelopment = popUpDevelopment;
	}

	public Market getPopUpMarket() {
		return popUpMarket;
	}

	public void setPopUpMarket(Market popUpMarket) {
		this.popUpMarket = popUpMarket;
	}

	public Mercato getMkt() {
		return mkt;
	}

	public void setMkt(Mercato mkt) {
		this.mkt = mkt;
	}

	public Nodo getBaseUtente() {
		return baseUtente;
	}

	public void setBaseUtente(Nodo baseUtente) {
		this.baseUtente = baseUtente;
	}
	
}
