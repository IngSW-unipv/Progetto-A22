package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;


/**
 * Obiettivi di punteggio
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivi
 */
public class ObPunteggio extends Obiettivi{

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
	public ObPunteggio(Obiettivi obiettivi, Integer punteggioObiettivo) {
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
	public Obiettivi getObiettivi() {
		return super.getObiettivi();
	}

	/**
	 * Setta l'obiettivo
	 * @param obiettivi
	 */
	public void setObiettivi(Obiettivi obiettivi) {
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObPunteggio other = (ObPunteggio) obj;
		return punteggioObiettivo == other.punteggioObiettivo;
	}

}
