package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe che gestisce i file delle lingue
 * @author TawaHabib
 * @version 1.0
 */
public class FilesLanguageManager implements ILanguageManager {
	public static String currentLanguageFile="resources/language/currentLanguage";
	public static final String CONN_DEF_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	public static String languageFolder="resources/language/";
	//private static String ppp0p="ENGLISH";
	private DBLinguaManager man;
	
	/**
	 * Crea FilesLanguageManager della lingua passata 
	 * @param lingua
	 * Lingua principale che si gestisce  
	 */
	public FilesLanguageManager(String lingua) {
		this.man=new DBLinguaManager(CONN_DEF_FILE);
		try {
			PropertiesFile.savePropertyInFile(this.man.getLanguegeList(lingua), languageFolder+lingua);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setCurrentLanguege(lingua);
	}
	
	/**
	 * Crea FilesLanguageManager, aggiornando  il contenuto
	 * del file che gestisce la lingua corrente con il dataBase
	 */
	public FilesLanguageManager() {
		this.man=new DBLinguaManager(CONN_DEF_FILE);
		try {
			PropertiesFile.savePropertyInFile(this.man.getLanguegeList(getCurrentLanguage()), languageFolder+getCurrentLanguage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	 
	/**
	 * Metodo per settare la lingua corrente a quella passata per argomento
	 * @param lingua
	 * Nuova lingua corrente
	 */
	public static void setCurrentLanguege(String lingua) {
		 FileWriter write=null;
		 try {
			 	write=new FileWriter(FilesLanguageManager.currentLanguageFile);
				write.append(lingua);
			} catch (Exception e1) {
			}finally {
				try {
					write.flush();
					write.close();
				} catch (Exception e1) {
					System.err.println("\t Error while try to close  "+FilesLanguageManager.currentLanguageFile);				
				}
			}
	 }
	 
	/**
	 * Metodo per conoscere il percorso (relativo) del file che 
	 * contene le coppe chiave-valore della lingua passata 
	 * @param language
	 * Lingua di cui si vuole conoscere il percorso
	 * @return
	 * Percorso relativo del file 
	 */
	public static String  getLanguageFilePath(String language) {
		String filePath=null;
		File fls=new File(FilesLanguageManager.languageFolder+language);
		if(fls.exists()) {
			filePath=FilesLanguageManager.languageFolder+language;
		}
		return filePath;
	}
	
	/**
	 * Metodo per recuperare le coppie chiave valore contenute nel file 
	 * che è associato alla lingua passata per argiomento
	 * @param language
	 * lingua di cui si vogliono 
	 * @return
	 * Coppie chiave valore  contenute nel file che conosce la lingua argomento
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
		try {
			result=man.getAviableLanguage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getLanguageKayByValue(String value, String lingua) {
		return this.man.getLanguageKayByValue(value, lingua);
	}
	
	@Override
	public String getLanguageValueByKay(String kay, String lingua) {
		String ris="";
		try {
			ris=PropertiesFile.getPropertieFromFile(kay, languageFolder+lingua);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ris;
	}
	
	@Override
	public boolean insertLanguegeList(Properties list, String lingua) {
		boolean result=true;
		if(list.isEmpty()) {
			return result;
		}else {
			try {
				man.insertLanguegeList(list, lingua);
				PropertiesFile.savePropertyInFile(list, languageFolder+lingua);
			} catch (Exception e) {
				e.printStackTrace();
				result=false;
			}
		}
		return result;
	}
	
	@Override
	public Properties getLanguegeList (String lingua) {
		Properties result=new Properties();
		try {
			result=PropertiesFile.loadPropertiesFromFile(languageFolder+lingua);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result.isEmpty()) {
			result=man.getLanguegeList(lingua);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(FilesLanguageManager.getCurrentLanguage());
	}

}

