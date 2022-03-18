package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * classe con utilità per i file delle lingue
 * @author TawaHabib
 * @version 1.0
 */
public class LanguageFiles implements ILanguageManager {
	
	/**
	 * Metodo per conoscere la lingua contenuta nel file currentLanguageFile
	 * @return Lingua Corrente 
	 */
	public static String  getCurrentLanguage() {
		return ILanguageManager.getCurrentLanguage();
	}
	/**
	 * @param language
	 * @return
	 */
	public static String  getLanguageFilePath(String language) {
		String filePath=null;
		File fls=new File(ILanguageManager.languageFolder+language);
		if(fls.exists()) {
			filePath=ILanguageManager.languageFolder+language+".properties";
		}
		return filePath;
	}
	/**
	 * @param language
	 * @return
	 */
	public static Properties getPropertiesLanguage(String language) {
		Properties prop=null;
		String filelLanguagePath=getLanguageFilePath(language);
		try {
			prop=PropertiesFile.loadPropertiesFromFile(filelLanguagePath);
		} catch (Exception e) {
				DBLinguaManager man=new DBLinguaManager("resources/config/persistence/dataBase/connWith_sd_sys");
				prop=man.getLanguegeList(language);
				try {
					if(prop.size()>2) {
						PropertiesFile.savePropertyInFile(prop, filelLanguagePath);
					}
				} catch (Exception e2) {
					e.printStackTrace();
				}
		}
		return prop;
	}
	@Override
	public ArrayList<String> getAviableLanguage(){
		ArrayList<String> result=new ArrayList<String>();
		return result;
	}
	@Override
	public String getLanguageKayByValue(String value, String lingua) {
		String result="";
		return result;
	}
	@Override
	public String getLanguageValueByKay(String kay, String lingua) {
		String result="";
		return result;
	}
	@Override
	public boolean insertLanguegeList(Properties list, String lingua) {
		boolean result=true;
		return result;
	}
	@Override
	public Properties getLanguegeList (String lingua) {
		Properties result=new Properties();
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(LanguageFiles.getCurrentLanguage());
	}

}

