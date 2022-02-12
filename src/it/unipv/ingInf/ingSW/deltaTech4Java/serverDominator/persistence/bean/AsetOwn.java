package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;

import java.util.Objects;

/**
 * Relazione che associa gli asset al UserAccount
 * @author TawaHabib
 * @version 1.0
 * @see Asset
 * @see AssetOwnId
 * @see UserAccount
 */

public class AsetOwn{

	/**
	 * Id composto da: Asset, UserAccount
	 *@see AssetOwnId
	 */
	private AsetOwnId primaryKey ;
 
	/**
	 * Quantita posseduta
	 */
	private int quantita;

	/**
	 * Crea AssetOwn vuoto
	 */
	public AsetOwn() {
	}

	/**
	 * Crea assetOwn con quntità=0
	 * @param id
	 */
	public AsetOwn(AsetOwnId id) {
		this.primaryKey  = id;
		this.quantita=0;
	}

	/**
	 * Crea AssetOwm con 
	 * @param id
	 * @param asset
	 * @param userAccount
	 * @param quantita
	 */
	public AsetOwn(AsetOwnId id, int quantita) {
		this.primaryKey  = id;
		this.quantita = quantita;
	}
	/**
	 * @return id AssetOwm
	 */
	
	public AsetOwnId getPrimaryKey () {
		return this.primaryKey ;
	}

	/**
	 * Setta id assetOwm
	 * @param id
	 */
	public void setPrimaryKey (AsetOwnId id) {
		this.primaryKey  = id;
	}
	/**
	 * @return Asset associato
	 */
	public Asset getAsset() {
		return this.getPrimaryKey ().getAsset();
	}

	/**
	 * Setta asset associato
	 * @param asset
	 */
	public void setAsset(Asset asset) {
		this.getPrimaryKey ().setAsset(asset);;
	}
	/**
	 * @return UserAccount associato
	 */
	public UserAccount getUserAccount() {
		return this.getPrimaryKey ().getUserAccount();
	}

	/**
	 * @param userAccount
	 * @see UserAccount
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.getPrimaryKey ().setUserAccount(userAccount);;
	}
	/**
	 * @return quantita posseduta
	 */
	
	public int getQuantita() {
		return this.quantita;
	}

	/**
	 * setta quantita
	 * @param quantita
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "AsetOwn [quantita=" + quantita + "]"+ primaryKey.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(primaryKey, quantita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsetOwn other = (AsetOwn) obj;
		return Objects.equals(primaryKey, other.primaryKey) && quantita == other.quantita;
	}
	
	

}
