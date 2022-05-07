package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione della classe Bot, simulatore di giocatore reale, 
 * con scelta di comportamenti randomici.
 */

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;

public class Bot extends Giocatore{
	
	private MappaDefinitiva map;
	private Coordinate base;
	private Timer time;
	private Battaglia battle;
	private final int T_UNITARIO=10;
	private int t_timer;
	private int cont;
	private Coordinate[] confini;
	int incremento; 
	/*incremento e' un parametro per individuare una coordinata vicina
	 * che ciclicamente verrà aumentato per puntare a coordinate 
	 * maggiormente lontane
	 */
	
	/**Permette di creare un oggetto Bot passando come parametro il suo nome*/
	
	public Bot(String nome) {
		super(nome);
		super.setPunteggio(0);
		time= new Timer();
		confini= new Coordinate[6];
		incremento=1;
		cont=0;
	}
	
	@Override
	public void run() {
		this.setMap(map);
		this.datiBase();
		this.cambiaTarget();
		while(map.getNodo(base.getX(), base.getY()).getPossessore().getNome()==this.getNome() ) {
			time.timer(30);
			this.comportamento();
		}
	}

	public void comportamento() {
		int min=1;
		int max=3;
		int scelta;
		String risorsa="Energia";
			
		scelta= (int)(Math.random()*(max-min))+ min;
		switch(scelta) {
		case 1: 
			/** potenziamento risorse del nodo base del bot */
			
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
			map.trovaBase(this).potenzia_risorsa(risorsa);
			break;
		case 2:
			/** creazione software nel nodo base del bot */
			
			map.trovaBase(this).crea_software("Antivirus", 5);
			map.trovaBase(this).crea_software("Virus", 5);
			map.trovaBase(this).crea_software("Rootcrash", 5);
			break;
		case 3:
			/** attacco da parte del bot verso un nodo prossimo */
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
			this.cambiaTarget();
			break;
		}
			
	}
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
	

	/**getter and setter */
	
	public void setMap(MappaDefinitiva map) {
		this.map = map;
	}

	
}

