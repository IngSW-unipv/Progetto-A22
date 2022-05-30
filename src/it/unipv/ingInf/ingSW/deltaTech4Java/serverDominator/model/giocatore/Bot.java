package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.main.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa.MappaDefinitiva;

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
	private Timer time2;
	private Battaglia battle;
	private final int T_UNITARIO=5;
	private int t_timer;
	private int cont;
	private Coordinate[] confini;
	private int incremento; 
	
	
	private PropertyChangeSupport changes;
	public static final String BOT_PROP="bot";
	public static final String PUNTEGGIO_BOT="fineBattagliaBot";
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
		super.setLife(true);
		
		
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
		while(map.getNodo(base.getX(), base.getY()).getPossessore().getNome().equals(this.getNome()) && super.getLife()==true) {
			this.comportamento();			
		}
		super.setLife(false);
	}

	/** metodo che simula il comportamento di un giocatore scegliendo in modo casuale
	 * tra 3 possibili azioni: potenziamento risorse nodo, creazione software, attacco nodi vicini
	 */
	public void comportamento() {
		int min=1;
		int max=5;
		int scelta;
		String risorsa="Energia";
		int n_virus;
		boolean end_battle=false;
			
		scelta= (int)(Math.random()*(max-min))+ min;
		switch(scelta) {
		case 1: 
			/* potenziamento risorse del nodo base del bot
			 * incentivato il potenziamento energetico
			 */
			
			if (map.trovaBase(this). getLvl_cpu()==10 || map.trovaBase(this).getLvl_ram()==10 ) {
				break;
			}
			
			scelta=(int)(Math.random()*(max-min));
			switch(scelta) {
			case 0: 
				risorsa="Cpu";
				break;
			case 1: 
				risorsa= "Ram";
				break;
			case 2:
				risorsa= "Energia";
				break;
			case 3:
				risorsa= "Firewall";
				break;
			default:
				risorsa= "Firewall";
				break;
			}
			if(map.trovaBase(this).getRisorse()[2].getStat1()<20) {
				System.out.println(this.getNome()+ " potenzia Energia per potenziamento ");
				time2.timer(map.trovaBase(this).getTempoRisorsa("Energia"));
				map.trovaBase(this).potenzia_risorsa("Energia");
			}
			System.out.println(this.getNome()+ " sta potenziando " + risorsa);
			time2.timer(map.trovaBase(this).getTempoRisorsa(risorsa));
			map.trovaBase(this).potenzia_risorsa(risorsa);
			break;
		case 2:
			/* creazione software nel nodo base del bot	 */
			
			/*controllare se bot dispone di spazio per creare software, se non possiede spazio in ram, allora
			 * la potenzia, se non possiede energia per potenziare la ram allora prima potenzia energia
			 */
			if(map.trovaBase(this).getSoftware_disponibile()+5 > map.trovaBase(this).getRisorse()[1].getStat1()) {	
				if(map.trovaBase(this).getRisorse()[1].getLivello_risorsa()==10) {
					break;
				} else {
					System.out.println(this.getNome()+ " potenzia Ram per creazione");
					if(map.trovaBase(this).getRisorse()[1].getE_richiesta()< map.trovaBase(this).getE_disponibile()) {
						map.trovaBase(this).potenzia_risorsa("Energia");
					}
				}				
				time2.timer(map.trovaBase(this).getTempoRisorsa("Ram"));
				map.trovaBase(this).potenzia_risorsa("Ram");
			}
			
			System.out.println(this.getNome()+" sta creando software");
			time2.timer(map.trovaBase(this).getTempoSoftware("Virus")*5);	
			map.trovaBase(this).crea_software("Virus", 5);
			time2.timer(map.trovaBase(this).getTempoSoftware("Antivirus")*5);
			map.trovaBase(this).crea_software("Antivirus", 5);
			
			time2.timer(map.trovaBase(this).getTempoSoftware("Rootcrash")*5);
			map.trovaBase(this).crea_software("Rootcrash", 5);
			break;
		case 3:
			/* attacco da parte del bot verso un nodo prossimo */
			if(map.attaccabile(confini[cont].getX(), confini[cont].getY(), this)) {
				t_timer= T_UNITARIO;
			//	t_timer=T_UNITARIO+(T_UNITARIO*map.dist_minima(confini[cont].getX(), confini[cont].getY(), this).getDist_base() );
				
				/*controllare se bot dispone di virus per attaccare, se non li possiede li crea*/
				if(map.trovaBase(this).getStats_software_creati()[1].getQuantita()==0) {
					System.out.println(this.getNome()+" sta creando per attaccare");
					time2.timer(map.trovaBase(this).getTempoSoftware("Virus")*10);	
					map.trovaBase(this).crea_software("Virus", 10);
				}
				
				battle= new Battaglia(map.trovaBase(this), map.getNodo(confini[cont].getX(), confini[cont].getY() ), t_timer) ;
			//	battle.setPartenza(map.dist_minima(confini[cont].getX(), confini[cont].getY(), this) );
				System.out.println(this.getNome()+ " crea battaglia vs " + map.getNodo(confini[cont].getX(), confini[cont].getY()).getPossessore().getNome());
				
				/*scelta randomica di virus da mandare, se ne sceglie 0 
				 * oppure se il numero di virus inviato è inferiore al valore di difesa di un 
				 * firewall livello 1, ossia 8, allora manda tutti i virus a disposizione
				 */
				n_virus=(int)Math.random()*(map.trovaBase(this).getSoftware_disponibile()-1)+1;
				if(n_virus==0 || n_virus < 8) {
					n_virus= map.trovaBase(this).getStats_software_creati()[1].getQuantita();
				}	
				battle.selezione( n_virus, 1);
				System.out.println(this.getNome() + "ha selezionato virus: " + n_virus );
			
				changes.firePropertyChange(new PropertyChangeEvent(this, BOT_PROP, new Coordinate(confini[cont].getX(), confini[cont].getY()), base));	
				time2.timer(t_timer);
				end_battle= battle.calcola_vincitore();
				
				System.out.println(this.getNome() + battle.stampa_report(end_battle)); 
				
				if(end_battle) {
					battle.aggiornastati();
					changes.firePropertyChange(PUNTEGGIO_BOT,0,1);
					cont++;
					if (cont>5) {
						incremento++;
						cont=0;
					}
				}				
			}
						
			break;
		default: 
			/* attacco da parte del bot verso un nodo prossimo */
			if(map.attaccabile(confini[cont].getX(), confini[cont].getY(), this)) {
				t_timer= T_UNITARIO;
			//	t_timer=T_UNITARIO+(T_UNITARIO*map.dist_minima(confini[cont].getX(), confini[cont].getY(), this).getDist_base() );
				
				/*controllare se bot dispone di virus per attaccare, se non li possiede li crea*/
				if(map.trovaBase(this).getStats_software_creati()[1].getQuantita()==0) {
					System.out.println(this.getNome()+" sta creando per attaccare");
					time2.timer(map.trovaBase(this).getTempoSoftware("Virus"));	
					map.trovaBase(this).crea_software("Virus", 10);
				}
				
				battle= new Battaglia(map.trovaBase(this), map.getNodo(confini[cont].getX(), confini[cont].getY() ), t_timer) ;
			//	battle.setPartenza(map.dist_minima(confini[cont].getX(), confini[cont].getY(), this) );
				System.out.println(this.getNome()+ " crea battaglia vs " + map.getNodo(confini[cont].getX(), confini[cont].getY()).getPossessore().getNome());
				
				/*scelta randomica di virus da mandare, se ne sceglie 0 
				 * oppure se il numero di virus inviato è inferiore al valore di difesa di un 
				 * firewall livello 1, ossia 8, allora manda tutti i virus a disposizione
				 */
				n_virus=(int)Math.random()*(map.trovaBase(this).getSoftware_disponibile()-1)+1;
				if(n_virus==0 || n_virus < 8) {
					n_virus= map.trovaBase(this).getStats_software_creati()[1].getQuantita();
				}	
				battle.selezione( n_virus, 1);
				System.out.println(this.getNome() + "ha selezionato virus: " + n_virus );
			
				changes.firePropertyChange(new PropertyChangeEvent(this, BOT_PROP, new Coordinate(confini[cont].getX(), confini[cont].getY()), base));	
				time2.timer(t_timer);
				end_battle= battle.calcola_vincitore();
				
				System.out.println(this.getNome() + battle.stampa_report(end_battle)); 
				
				if(end_battle) {
					battle.aggiornastati();
					changes.firePropertyChange(PUNTEGGIO_BOT,0,1);
					cont++;
					if (cont>5) {
						incremento++;
						cont=0;
						this.cambiaTarget();
					}
				}				
			}
			break;
		}
			
	}
	
	/**usato per aggiornare il bersaglio di un attacco */
	public void cambiaTarget() {
		
		
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
	@Override
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

