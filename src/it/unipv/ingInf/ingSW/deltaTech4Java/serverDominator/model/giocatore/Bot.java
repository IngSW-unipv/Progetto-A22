package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione della classe Bot, simulatore di giocatore reale, 
 * con scelta di comportamenti randomici.
 */
public class Bot extends Giocatore{
	
	private MappaDefinitiva map;
	private Coordinate base;
	private Timer time;
	private Timer time2;
	private Battaglia battle;
	private final int T_UNITARIO=5;
	private int t_timer;
	private int cont;
	private Coordinate[] confini;
	int incremento; 
	
	private PropertyChangeSupport changes;
	public static final String BOT_PROP="bot";
	
	/*incremento e' un parametro per individuare una coordinata vicina
	 * che ciclicamente verr� aumentato per puntare a coordinate 
	 * maggiormente lontane
	 */
	
	/**Permette di creare un oggetto Bot passando come parametro il suo nome
	 * @param nome
	 * nome del bot
	 */
	public Bot(String nome) {
		super(nome);
		super.setPunteggio(0);
		time= new Timer();
		time2 = new Timer();
		confini= new Coordinate[6];
		incremento=1;
		cont=0;
		this.changes= new PropertyChangeSupport(this);
	}
	
	@Override
	public void run() {
		this.setMap(map);
		this.datiBase();
		this.cambiaTarget();
		while(map.getNodo(base.getX(), base.getY()).getPossessore().getNome().equals(this.getNome())) {
			time.timer(30);
			this.comportamento();
		}
	}

	/** metodo che simula il comportamento di un giocatore scegliendo in modo casuale
	 * tra 3 possibili azioni: potenziamento risorse nodo, creazione software, attacco nodi vicini
	 */
	public void comportamento() {
		int min=1;
		int max=3;
		int scelta;
		String risorsa="Energia";
			
		scelta= (int)(Math.random()*(max-min))+ min;
		switch(scelta) {
		case 1: 
			/* potenziamento risorse del nodo base del bot */
			
			scelta=(int)(Math.random()*(max-min));
			switch(scelta) {
			case 0: 
				risorsa="Cpu";
				break;
			case 1: 
				risorsa= "Ram";
				break;
			case 2:
				risorsa= "Firewall";
				break;
			case 3:
				risorsa= "Energia";
				break;
			}
			time2.timer(map.trovaBase(this).getTempoRisorsa(risorsa));
			map.trovaBase(this).potenzia_risorsa(risorsa);
			break;
		case 2:
			/* creazione software nel nodo base del bot */
			
			time2.timer(map.trovaBase(this).getTempoSoftware("Antivirus"));
			map.trovaBase(this).crea_software("Antivirus", 5);
			time2.timer(map.trovaBase(this).getTempoSoftware("Virus"));	
			map.trovaBase(this).crea_software("Virus", 5);
			time2.timer(map.trovaBase(this).getTempoSoftware("Rootcrash"));
			map.trovaBase(this).crea_software("Rootcrash", 5);
			break;
		case 3:
			/* attacco da parte del bot verso un nodo prossimo */
			if(map.attaccabile(confini[cont].getX(), confini[cont].getY(), this)) {
				t_timer=T_UNITARIO+(T_UNITARIO*map.dist_minima(confini[cont].getX(), confini[cont].getY(), this).getDist_base() );
				battle= new Battaglia(map.trovaBase(this), map.getNodo(confini[cont].getX(), confini[cont].getY() ), t_timer) ;
				battle.setPartenza(map.dist_minima(confini[cont].getX(), confini[cont].getY(), this) );
				battle.selezione( (int)Math.random()*(map.trovaBase(this).getSoftware_disponibile()-1)+1, 1);
				if(battle.calcola_vincitore() ) {
					battle.aggiornastati();
					cont++;
				}
			}
			/*risvegliare il listener se bot attacca l'utente
			 * usare getter t_timer per visualizzazione a schermo della battaglia avviata
			 * dal bot verso il nodo dell'utente
			 */
			changes.firePropertyChange(new PropertyChangeEvent(this, BOT_PROP, new Coordinate(confini[cont].getX(), confini[cont].getY()), base));	
			
			this.cambiaTarget();
			break;
		}
			
	}
	
	/**usato per aggiornare il bersaglio di un attacco */
	public void cambiaTarget() {
		if (cont>5) {
			incremento++;
			cont=0;
		}
		confini[0]= new Coordinate(base.getX()+incremento, base.getY());
		confini[1]= new Coordinate(base.getX()+incremento, base.getY()-incremento);
		confini[2]= new Coordinate(base.getX(), base.getY()-incremento);
		confini[3]= new Coordinate(base.getX()-incremento, base.getY());
		confini[4]= new Coordinate(base.getX()-incremento, base.getY()+incremento);
		confini[5]= new Coordinate(base.getX(), base.getY()+incremento);
				
	}
	
	/**usato per fornire al bot le informazioni della sua base
	 * quali le coordinate
	 */
	public void datiBase() {
		int i;
		int x=0;
		int y=0;
		
		for(i=0;i<map.getBasi().length; i++) {
			if(map.getBasi()[i].getNome()==this.getNome() ) {
				x=map.getBasi()[i].getX();
				y=map.getBasi()[i].getY();
			} 
		}
		base= new Coordinate(x, y, this.getNome());
	}
	

	//------------getter and setter------------//
	/**
	 * setta il valore di mappa uguale a quella passata come parametro
	 * @param mappa
	 */
	public void setMap(MappaDefinitiva map) {
		this.map = map;
	}
	/**
	 * returna il valore corrispondente al tempo di attessa per eseguire l'attacco verso il nodo selezionato dal bot
	 * @return
	 */
	public int getT_timer() {
		return t_timer;
	}

	public PropertyChangeSupport getChanges() {
		return changes;
	}
	
	
}

