package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;


/**
 * Obiettivi di punteggio
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivo
 */
public class ObPunteggio extends Obiettivo{

	/**
	 * Punteggio necessario per raggiungere l'obiettivo
	 */
	private Integer punteggioObiettivo;

	/**
	 * Crea obiettivo di punteggio vuoto
	 */
	public ObPunteggio() {
		super();
	}

	/**
	 * Crea obiettivo di punteggio
	 * @param obiettivi
	 * @param punteggioObiettivo
	 */
	public ObPunteggio(Obiettivo obiettivi, Integer punteggioObiettivo) {
		super(	obiettivi.getIdObiettivo(),obiettivi.getDescrizione(),
				obiettivi.getRicompensa(),obiettivi.getObiettiviUsers());
		this.punteggioObiettivo = punteggioObiettivo;
	}

	/**
	 * @return identificatore obiettivo di punteggio
	 */
	public Integer getObiettiviIdObiettivo() {
		return super.getIdObiettivo();
	}


	/**
	 *Cambia l'identificativo dell' obiettivo punteggio
	 *@param idObiettivo
	 */
	public void setIdObiettivo(Integer obiettiviIdObiettivo) {
		super.setIdObiettivo(obiettiviIdObiettivo);
	}

	/**
	 *@return Obiettivo padre
	 */
	public Obiettivo getObiettivi() {
		return super.getObiettivi();
	}

	/**
	 * Setta l'obiettivo
	 * @param obiettivi
	 */
	public void setObiettivi(Obiettivo obiettivi) {
		super.setIdObiettivo(obiettivi.getIdObiettivo());
		super.setDescrizione(obiettivi.getDescrizione());
		super.setRicompensa(obiettivi.getRicompensa());
		super.setObiettiviUsers(obiettivi.getObiettiviUsers());
	}

	/**
	 * @return Punteggio necessario per raggiungere l'obiettivo
	 */
	public Integer getPunteggioObiettivo() {
		return this.punteggioObiettivo;
	}

	/**
	 * Cambia punteggio necessario per raggiungere l'obiettivo
	 * @param punteggioObiettivo
	 */
	public void setPunteggioObiettivo(Integer punteggioObiettivo) {
		this.punteggioObiettivo = punteggioObiettivo;
	}

	@Override
	public String toString() {
		return "ObPunteggio [punteggioObiettivo=" + punteggioObiettivo + "]"+super.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		ObPunteggio other = (ObPunteggio) obj;
		return 
				(punteggioObiettivo.equals(other.getPunteggioObiettivo())&&
				super.equals(other.getObiettivi()));
	}

	@Override
	public int compareTo(Obiettivo o) {
		Integer i=0;
		if(ObPunteggio.class.isAssignableFrom(o.getClass())) {
			ObPunteggio o1=(ObPunteggio)o;
			i=this.getPunteggioObiettivo()-o1.getPunteggioObiettivo();
		}else {
			i=this.getRicompensa()-o.getRicompensa();
		}
		return i!=null?i:0;
	}

}
