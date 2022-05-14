package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Matteo c
 * @version 1.0
 * @since 1.0
 */
public class Classifica {
	public static final String LISTA_PROP="lista";
	
	private List<Giocatore> lista;
	private PropertyChangeSupport changes;
	public Classifica(Giocatore... giocatori) {
		this.lista = new ArrayList<Giocatore>();
		for (int i = 0; i < giocatori.length; i++)
			this.lista.add(giocatori[i]);
		this.changes= new PropertyChangeSupport(this);
		this.aggiornaClassifica();
	}
	
	/**
	 * Creare una classifica vuota
	 */
	public Classifica() {
		this.lista=new ArrayList<>();
		this.changes= new PropertyChangeSupport(this);
	}
	
	/**
	 * Passando come parametro una List di Utenti ne fa la classifica per punteggio
	 * @param lista 
	 * lista dei giocatori di cui si fuole fare la classifica
	 */
	public Classifica(List<Giocatore> lista) {
		this.lista = new ArrayList<>();
		this.lista=lista;
		this.changes= new PropertyChangeSupport(this);
		this.aggiornaClassifica();
	}
	
	/**
	 * Passando un Utente come parametro permette 
	 * di aggiungerlo alla classifica. Una volta aggiunto la classifica si aggiorna.
	 * @param user
	 * User da aggiungere alla lista dei giocatori
	 */
	public void aggiungiUtente(Giocatore user) {
		List<Giocatore> oldList =new ArrayList<Giocatore>(lista);
		this.lista.add(user);
		Collections.sort(lista);
		List<Giocatore>  newList=lista;
		changes.firePropertyChange(LISTA_PROP, oldList,newList);
	}
	
	/**
	 * Restituisce la posizione all'interno della classifica dell'Utente passato come parametro
	 * @param user
	 * giocatore di cui si vuole conoscere la posizione
	 * @return
	 * posizione del giocatore
	 */
	public int getPosizione(Giocatore user) {
		return this.getPosizione(user.getNome());
	}
	
	/**
	 * Restituisce la posizione all'interno della classifica dell'Utente il cui nome è stato passato come paramtro
	 * @param user
	 * Nome del giocatore d'interesse
	 * @return
	 * posizione nella classifica
	 */
	public int getPosizione(String user) {
		int i=0;
		for(Giocatore g:lista)
			if(!user.equals(g.getNome()))
				i++;
			else
				return i;
		
		int risultato=0;;
		Giocatore prova=new Bot(user);
		risultato=this.lista.indexOf(prova);
		return risultato;
	}
	
	/**
	 * Aggiorna l'ordine della classifica 
	 *(fatta in base al punteggio del giocatore interessato)
	 */
	public void aggiornaClassifica() {
		
		List<Giocatore> oldList =lista;
		Collections.sort(lista);
		List<Giocatore>  newList=lista;
		changes.firePropertyChange(LISTA_PROP, oldList,newList);
	}
	
	/**
	 * Recupera la lista ordinata dei giocatori in classifica
	 * @return Classifica ordinata
	 */
	public List<Giocatore> getClassifica() {
		this.aggiornaClassifica();
		return lista; 
	}
	
	/**
	 * Sostituisce la classifica salvata con quella della List di Utenti passata come parametro
	 * @param lista
	 */
	public void setClassifica(List<Giocatore> lista) {
		this.lista = lista;
		if(lista!=null)
			this.aggiornaClassifica();
		
	}
	
	/**
	 * Restituisce il numero di Utenti contenuti nella classifica
	 * @return
	 * numero di utenti in classifica
	 */
	public int getLunghezzaClassifica() {
		return lista.size();
		
	}
	
	/**
	 * Permette di rimuovere dalla classifica l'Utente passato come parametro
	 * @param user
	 */
	public void removeUtente(Giocatore user) {
		changes.firePropertyChange(LISTA_PROP, lista, lista.remove(user));
	}
	
	/**
	 * Permette di rimuovere dalla classifica l'Utente la cui posizione 
	 * è stato passata come parametro
	 * @param pos_user
	 * posizione del giocatore da rimuovere
	 */
	public void removeUtente(int pos_user) {
		
		changes.firePropertyChange(LISTA_PROP, lista,lista.remove(pos_user));
	}
	
	/**
	 * Aggiorna punteggio del giocatore passato
	 * @param user
	 */
	public void aggiornaGiocatore(Giocatore user) {
		int i=this.getPosizione(user);
		lista.get(i).setPunteggio(user.getPunteggio());
		this.aggiornaClassifica();
	}
	
	public PropertyChangeSupport getPropertyChangeSupport() {
	    return changes;
	}
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.removePropertyChangeListener(propertyName,listener);
    }
    
    
	public List<Giocatore> getLista() {
		return lista;
	}

	public void setLista(List<Giocatore> lista) {
		this.lista = lista;
	}

	public PropertyChangeSupport getChanges() {
		return changes;
	}

	public void setChanges(PropertyChangeSupport changes) {
		this.changes = changes;
	}

	@Override
	public String toString() {
		String s = "Classifica:\n";
        for (Giocatore i : lista) {
            if (i != null) {
                s += "Nome utente: "+i.getNome()+" Punteggio: "+i.getPunteggio()+"\n";
            } 
        }
        return s;
	}
	public Giocatore getVincitore() {
		return lista.get(0);
	}
	
	public static void main(String[] args) {
		
	}
}
