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

public class AssetOwn{

	/**
	 * Id composto da: Asset, UserAccount
	 *@see AssetOwnId
	 */
	private AssetOwnId primaryKey ;
 
	/**
	 * Quantita posseduta
	 */
	private Integer quantita;

	/**
	 * Crea AssetOwn vuoto
	 */
	public AssetOwn() {
	}

	/**
	 * Crea assetOwn con quntità=0
	 * @param id
	 */
	public AssetOwn(AssetOwnId id) {
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
	public AssetOwn(AssetOwnId id, Integer quantita) {
		this.primaryKey  = id;
		this.quantita = quantita;
	}
	public AssetOwn(Asset aid, UserAccount ucc,Integer quantita) {
		this.primaryKey  = new AssetOwnId(aid,ucc);
		this.quantita = quantita;
	}
	/**
	 * @return id AssetOwm
	 */
	
	public AssetOwnId getPrimaryKey () {
		return this.primaryKey ;
	}

	/**
	 * Setta id assetOwm
	 * @param id
	 */
	public void setPrimaryKey (AssetOwnId id) {
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
	
	public Integer getQuantita() {
		return this.quantita;
	}

	/**
	 * setta quantita
	 * @param quantita
	 */
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "AsetOwn [quantita=" + quantita + "]"+ primaryKey.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetOwn other = (AssetOwn) obj;
		return Objects.equals(primaryKey, other.primaryKey) && quantita == other.quantita;
	}
	
	

}
