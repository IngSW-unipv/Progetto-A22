package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Account con username, enail e password
 * @author ME
 * @version 1.0
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account{

	/**
	 * Username
	 */
	private String username;
	/**
	 * Email
	 */
	private String email;
	/**
	 * Password
	 */
	private String passw;

	/**
	 * Crea account vuoto
	 */
	public Account() {
	}

	/**
	 * Crea account
	 * @param username
	 * @param passw
	 */
	public Account(String username, String passw) {
		this.username = username;
		this.passw = passw;
	}

	/**
	 * Crea account
	 * @param username
	 * @param email
	 * @param passw
	 */
	public Account(String username, String email, String passw) {
		this.username = username;
		this.email = email;
		this.passw = passw;
	}

	/**
	 * @return username dell'account
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Settare username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return email dell'account
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Settare email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return password dell'account
	 */
	public String getPassw() {
		return this.passw;
	}

	/**
	 * @param passw (password dell'account)
	 */
	public void setPassw(String passw) {
		this.passw = passw;
	}

}
