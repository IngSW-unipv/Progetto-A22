package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.util.ArrayList;
import java.util.Properties;

public interface ILanguageManager {
	public static final String invalidLinguaChar="[[^a-z]&&[^A-Z]&&[^ñ]]";
	/**
	 * Recupera la lista delle lingue disponibili
	 * @return Lingue disponibili
	 */
	public ArrayList<String> getAviableLanguage();
	
	/**
	 * Recupera la chiave dato il suo valore in una data lingua
	 * @param value
	 * @param lingua
	 * @return
	 */
	public String getLanguageKayByValue(String value, String lingua);
	
	/**
	 * Recupera la valore che ha la chiave passata chiave in una data lingua
	 * @param kay
	 * @param lingua
	 * @return
	 */
	public String getLanguageValueByKay(String kay, String lingua);

	/**
	 * Inserimento di una nuova Lingua
	 * @param list
	 * @param lingua
	 * @return
	 */
	public boolean insertLanguegeList(Properties list, String lingua);
	
	/**
	 * Recupero le coppie chiave valore di una lingua
	 * @param lingua
	 * @return
	 */
	public Properties getLanguegeList (String lingua);
}
