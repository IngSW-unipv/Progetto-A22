package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * classe con utilità per i file delle lingue
 * @author TawaHabib
 * @version 1.0
 */
public class FilesLanguageManager implements ILanguageManager {
	public static String currentLanguageFile="resources\\language\\currentLanguage";
	public static String availableGameLanguages="resources\\language\\availableGameLanguages";
	public static String languageFolder="resources\\language\\";
	public static String defaultLingua="ENGLISH";
	
	/**
	 * Recupera la lingua corrente; di defoult recupera la lingua presente in currentLanguageFile;
	 * </br>se il file non presente lo crea e ci mette Inglese
	 * @return Lingua Corrente 
	 */
	 public static String getCurrentLanguage() {
		BufferedReader fileReader = null;
		String lingua=defaultLingua;
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
					System.err.println("\t file created with defaultLingua="+defaultLingua);
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
			lingua=defaultLingua;
		}
		return lingua;
	}
	
	/**
	 * @param language
	 * @return
	 */
	public static String  getLanguageFilePath(String language) {
		String filePath=null;
		File fls=new File(FilesLanguageManager.languageFolder+language);
		if(fls.exists()) {
			filePath=FilesLanguageManager.languageFolder+language+".properties";
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
		System.out.println(FilesLanguageManager.getCurrentLanguage());
	}

}

