package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;

import java.util.Objects;

/**
 * Identificativo composto di asset own
 * @author TawaHabib
 * @version 1.0
 * @see Asset
 * @see UserAccount
 */

public class AssetOwnId{

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
	public AssetOwnId() {
	}

	/**
	 * Crea id
	 * @param asset
	 * @param userAccount
	 */
	public AssetOwnId(Asset asset, UserAccount userAccount) {
		this.asset = asset;
		this.userAccount = userAccount;
	}
	 /**
	 * @return asset
	 */
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

	@Override
	public String toString() {
		return "AsetOwnId [ "+ asset.toString() + ", userAccount[" + userAccount.getUsername() + "]]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetOwnId other = (AssetOwnId) obj;
		return Objects.equals(asset, other.asset) && Objects.equals(userAccount, other.userAccount);
	}
	
	

}
