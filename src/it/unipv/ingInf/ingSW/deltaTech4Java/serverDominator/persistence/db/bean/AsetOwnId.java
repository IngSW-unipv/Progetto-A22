package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


/**
 * Identificativo composto di asset own
 * @author ME
 * @version 1.0
 * @see Asset
 * @see UserAccount
 */
@Embeddable
/* 
 * @Embeddable
 * sta per che questa classe può essere incorporata in altre entity
 * 
 */
public class AsetOwnId implements Serializable{

	/**
	 * asset posseduto dallo user
	 */
	private Asset asset;
	/**
	 * userAccount in possesso dell'asset 
	 */
	private UserAccount userAccount;

	/**
	 * Crea id vuoto
	 */
	public AsetOwnId() {
	}

	/**
	 * Crea id
	 * @param asset
	 * @param userAccount
	 */
	public AsetOwnId(Asset asset, UserAccount userAccount) {
		this.asset = asset;
		this.userAccount = userAccount;
	}
	 /**
	 * @return asset
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	public Asset getAsset() {
		return this.asset;
	}

	/**
	 * Settare asset 
	 * @param asset
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	 /**
	 * @return userAccount
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/**
	 * Settare userAccount
	 * @param userAccount
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount= userAccount;
	}

}
