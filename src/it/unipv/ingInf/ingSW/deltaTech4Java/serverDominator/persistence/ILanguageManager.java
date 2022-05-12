package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public interface ILanguageManager {
	public static final String invalidLinguaChar="";
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
	
	/**
	 * Recupera la lingua corrente; di defoult recupera la lingua presente in currentLanguageFile;
	 * </br>se il file non presente lo crea e ci mette la lingua del sistema 
	 * @return Lingua Corrente 
	 */
	public static String getCurrentLanguage() {
		BufferedReader fileReader = null;
		String lingua=Locale.getDefault().getDisplayLanguage();
		try {
			fileReader = new BufferedReader(new FileReader(FilesLanguageManager.currentLanguageFile));
			lingua=fileReader.readLine();
			try{
				fileReader.close();
			} catch (IOException e1) {
				System.err.println("Error while closing "+FilesLanguageManager.currentLanguageFile);
				e1.printStackTrace();
			}
		}catch (Exception e) {
			File f=new File(FilesLanguageManager.currentLanguageFile);
			if(!f.exists()) {
				System.err.println("file "+FilesLanguageManager.currentLanguageFile+ " dose not exist");
				FileWriter write=null;
				try {
					System.err.println("\t tring to crate file "+FilesLanguageManager.currentLanguageFile);
					write=new FileWriter(FilesLanguageManager.currentLanguageFile);
					write.append(lingua);
					System.err.println("\t file created with default lingua="+Locale.getDefault().getDisplayLanguage());
				} catch (Exception e1) {
					System.err.println("\t Error while try to create  "+FilesLanguageManager.currentLanguageFile);
				}finally {
					try {
						write.flush();
						write.close();
					} catch (Exception e1) {
						System.err.println("\t Error while try to close  "+FilesLanguageManager.currentLanguageFile);				
					}
				}
			}else {
				e.printStackTrace();
			}
		}
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		if(lingua.length()<3) {
			lingua=Locale.getDefault().getDisplayLanguage();
		}
		return lingua;
	}
}
