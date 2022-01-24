package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Obiettivi di punteggio
 * @author ME
 * @version 1.0
 * @see Obiettivi
 */
@Entity
@Table(name = "OB_PUNTEGGIO")
public class ObPunteggio extends Obiettivi{

	/**
	 * Punteggio necessario per raggiungere l'obiettivo
	 */
	private int punteggioObiettivo;

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
	public ObPunteggio(Obiettivi obiettivi, int punteggioObiettivo) {
		super(	obiettivi.getIdObiettivo(),obiettivi.getDescrizione(),
				obiettivi.getRicompensa(),obiettivi.getObiettiviUsers());
		this.punteggioObiettivo = punteggioObiettivo;
	}

	/**
	 * @return identificatore obiettivo di punteggio
	 */
	public int getObiettiviIdObiettivo() {
		return super.getIdObiettivo();
	}


	/**
	 *Cambia l'identificativo dell' obiettivo punteggio
	 *@param idObiettivo
	 */
	public void setIdObiettivo(int obiettiviIdObiettivo) {
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
	public int getPunteggioObiettivo() {
		return this.punteggioObiettivo;
	}

	/**
	 * Cambia punteggio necessario per raggiungere l'obiettivo
	 * @param punteggioObiettivo
	 */
	public void setPunteggioObiettivo(int punteggioObiettivo) {
		this.punteggioObiettivo = punteggioObiettivo;
	}

}
