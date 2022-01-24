package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Relazione che associa gli asset al UserAccount
 * @author ME
 * @version 1.0
 * @see Asset
 * @see AssetOwnId
 * @see UserAccount
 */
@Entity
@Table(name = "ASET_Own")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.asset",
        joinColumns = @JoinColumn(name = "ASSET")),
    @AssociationOverride(name = "primaryKey.userAccount",
        joinColumns = @JoinColumn(name = "USERNAME")) })
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
	@EmbeddedId
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
	@Transient
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
	@Transient
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
	@Column(name = "qantita")
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
	

}
