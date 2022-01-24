package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Una tipologia di asset utilizzata per l'attacco 
 * @author ME
 * @version 1.0
 * @see Asset
 *
 */
@Entity
@Table(name = "VIRUS")
public class Virus extends Asset {

	/**
	 * Capacità di attacco del virus
	 */
	private int capacitaOffensiva;
	/**
	 * Categoria di virus a cui appartine il virus
	 */
	private String tipo;

	/**
	 * Crea virus vuoto
	 */
	public Virus() {
		super();
	}

	/**
	 * Crea virus
	 * @param asset
	 * @param capacitaOffensiva
	 */
	public Virus(Asset asset, int capacitaOffensiva) {
		super(	asset.getIdAsset(), asset.getCosto(), 
				asset.getNome(), asset.getDescrizione(), 
				asset.getLivello());
		this.capacitaOffensiva = capacitaOffensiva;
	}

	/**
	 * Crea virus
	 * @param asset
	 * @param capacitaOffensiva
	 * @param tipo
	 */
	public Virus(Asset asset, int capacitaOffensiva, String tipo) {
		super(	asset.getIdAsset(), asset.getCosto(), 
				asset.getNome(), asset.getDescrizione(), 
				asset.getLivello());
		this.capacitaOffensiva = capacitaOffensiva;
		this.tipo = tipo;
	}

	/**
	 * @return
	 */
	public int getAssetIdAsset() {
		return super.getIdAsset();
	}

	/**
	 * setta id del virus (asset padre)
	 * @param assetIdAsset
	 */
	public void setAssetIdAsset(int assetIdAsset) {
		super.setIdAsset(assetIdAsset);;
	}

	/**
	 *@return Asset padre 
	 */
	public Asset getAsset() {
		return super.getAsset();
	}

	/**
	 * Cambia i l'istanza dell'asset padre 
	 * @param asset
	 */ 
	public void setAsset(Asset asset) {
		super.setIdAsset(asset.getIdAsset());
		super.setCosto(asset.getCosto());
		super.setNome(asset.getNome());
		super.setDescrizione(asset.getDescrizione());
		super.setLivello(asset.getLivello());
		super.setAsetOwns(asset.getAsetOwns());
	}

	/**
	 * @return capacità di attacco del virus
	 */
	public int getCapacitaOffensiva() {
		return this.capacitaOffensiva;
	}

	/**
	 * setta copacità di attacco del virus
	 * @param capacitaOffensiva
	 */
	public void setCapacitaOffensiva(int capacitaOffensiva) {
		this.capacitaOffensiva = capacitaOffensiva;
	}

	/**
	 * @return Categoria di virus a cui appartine il virus
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Settare tipologia dell'antivirus
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
