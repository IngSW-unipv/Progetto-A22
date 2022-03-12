package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * classe con utilit‡ per i file delle lingue
 * @author TawaHabib
 *
 */
public class LanguageFiles {
	public static final String currentLanguageFile="resources\\language\\currentLanguage";
	public static final String availableGameLanguages="resources\\language\\availableGameLanguages";
	public static final String invalidLinguaChar="[[^a-z]&&[^A-Z]&&[^Ë‡Ú˘ÈÁ]]";
	public static final String languageFolder="resources\\language\\";
	//lingua predefinita inglese\
	public static String defaultLingua="ENGLISH";
	/**
	 * Metodo per conoscere la lingua contenuta nel file currentLanguageFile
	 * @return Lingua Corrente 
	 */
	public static String  getCurrentLanguage() {
		BufferedReader fileReader = null;
		String lingua=defaultLingua;
		try {
			fileReader = new BufferedReader(new FileReader(LanguageFiles.currentLanguageFile));
			lingua=fileReader.readLine();
			try{
				fileReader.close();
			} catch (IOException e1) {
				System.err.println("Error while closing "+LanguageFiles.currentLanguageFile);
				e1.printStackTrace();
			}
		}catch (Exception e) {
			File f=new File(LanguageFiles.currentLanguageFile);
			if(!f.exists()) {
				System.err.println("file "+LanguageFiles.currentLanguageFile+ " dose not exist");
				FileWriter write=null;
				try {
					System.err.println("\t tring to crate file "+LanguageFiles.currentLanguageFile);
					write=new FileWriter(LanguageFiles.currentLanguageFile);
					write.append(lingua);
					System.err.println("\t file created with defaultLingua="+defaultLingua);
				} catch (Exception e1) {
					System.err.println("\t Error while try to create  "+LanguageFiles.currentLanguageFile);
				}finally {
					try {
						write.flush();
						write.close();
					} catch (Exception e1) {
						System.err.println("\t Error while try to close  "+LanguageFiles.currentLanguageFile);				
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
		File fls=new File(languageFolder+language);
		if(fls.exists()) {
			filePath=languageFolder+language+".properties";
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

	
	public static void main(String[] args) {
		System.out.println(LanguageFiles.getCurrentLanguage());
	}
}

