package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

/**
 * @author TawaHabib
 * @version 1.0
 * @see ObiettiviUser
 *
 */
public class ObiettiviUserFileSistemDAO {
	private static final String COMMA_DELIMITER = ",";
	
	private static final String LINE_SEPARATOR = "\n";

	private static final String FILE_NAME = "resources/config/persistence/obiettiviPlayer";

	private static final String FILE_HEADER = "ID, DESCRIZIONE, STATO, RICOMPENSA";
	
    private static final int ID = 0;
    private static final int DESCRIZIONE = 1;
    private static final int STATO = 2;
    private static final int RICOMPENSA = 3; 
    
    /**
     * slva Obiettivi in file semistrutturato
     * @param ObiettiviUsers
     * lista Obiettivi che si vogliono salvare
     * @param fileName
     * percorso del file in cui si vogliono salvare
     */
    public static boolean saveObUserInCsvFile(ArrayList<ObiettiviUser> obUser,String fileName) {
    	File fls=new File(fileName);
    	if (!fls.exists()) {
    		return saveFile(getObiettiviListWithoutDuplicate(obUser),fileName);
    	}
    	else {
    		ArrayList<ObiettiviUser> ob=readObUserFromCsvFile(fileName);
    		ob.addAll(obUser);
    		return saveFile(getObiettiviListWithoutDuplicate(ob), fileName);
    	}
    }

    
    /**
     * Salva Obiettivi user nel file predefinito 
     * @param obUser
     * Lista obiettivi user da salvate 
     */
    public static boolean saveObUserInCsvFile(ArrayList<ObiettiviUser> obUser) {
       	File fls=new File(FILE_NAME);
    	if (!fls.exists()) {
    		return saveFile(getObiettiviListWithoutDuplicate(obUser),FILE_NAME);
    	}
    	else {
    		ArrayList<ObiettiviUser> ass=readObUserFromCsvFile(FILE_NAME);
    		ass.addAll(obUser);
    		return saveFile(getObiettiviListWithoutDuplicate(ass), FILE_NAME);
    	}
    }
    
    /**
     * prendi obiettivi user da file csv predefinito
     * @return
     */
    public static ArrayList<ObiettiviUser> readObUserFromCsvFile(){
    	return readObUserFromCsvFile(FILE_NAME);
    }
    
	/**
	 * @param obUser
	 * @param fileName
	 */
	private static boolean saveFile(ArrayList<ObiettiviUser> obUser,String fileName) {
		boolean ris=true;
		FileWriter fileWriter = null;
		obUser=getObiettiviListWithoutDuplicate(obUser);
		try {
			
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(LINE_SEPARATOR);
			
			for (ObiettiviUser o : obUser) {
				
				fileWriter.append(String.valueOf(o.getPrimaryKey().getObiettivo().getIdObiettivo()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(o.getPrimaryKey().getObiettivo().getDescrizione());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(o.getStato());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(o.getObiettivi().getRicompensa()));
				fileWriter.append(LINE_SEPARATOR);
			}
	
		} catch (Exception e) {
			ris=false;
			System.err.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			
		} 
		finally {
			
			try {
				
				fileWriter.flush();
				fileWriter.close();
				
			} catch (final IOException e) {
				System.err.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}  
		}
		return ris;
	}
	
	/**
	 * prendi obiettiviUser dal file
	 * @param fileName
	 * file path
	 * @return Obiettivi user nel filepath
	 */
	public static ArrayList<ObiettiviUser> readObUserFromCsvFile(String fileName) {
		
		BufferedReader fileReader = null;
		
		ArrayList<ObiettiviUser> ObiettiviUser = new ArrayList<ObiettiviUser> ();
		
		try {
			
			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			
			while ((line = fileReader.readLine()) != null) {
				
				String[] tokens = line.split(COMMA_DELIMITER);
				
				if (tokens.length == 4) {
					try {
					Obiettivi o =new Obiettivi(Integer.valueOf(tokens[ID]),tokens[DESCRIZIONE],Integer.valueOf(tokens[RICOMPENSA]));
					ObiettiviUser ou = new ObiettiviUser(o,null, tokens[STATO]);
					ObiettiviUser.add(ou);
					ObiettiviUser=getObiettiviListWithoutDuplicate(ObiettiviUser);
					}catch (Exception e) {
						continue;
					}
				}
			}
		}catch (Exception e) {
			
			System.err.println("Error in CsvFileReader !!!");
			e.printStackTrace();
			
		} 
		finally {
			
			try{
				
				fileReader.close();
				
			} catch (IOException e) {
				
				System.err.println("Error while closing fileReader !!!");
				e.printStackTrace();
				
			}
		}
		
		return ObiettiviUser;
	}
	
	
	private static ArrayList<ObiettiviUser>  getObiettiviListWithoutDuplicate(ArrayList<ObiettiviUser> obb){
		ArrayList<ObiettiviUser> newList=new ArrayList<ObiettiviUser>();
		for(ObiettiviUser o : obb) {
			if(!newList.contains(o)) {
				newList.add(o);
			}
		}
		
		for(int i=0; i<newList.size();i++) {
			for(int j=i+1;j<newList.size();j++)
				if(
					newList.get(i).getObiettivi().getIdObiettivo()==newList.get(j).getObiettivi().getIdObiettivo()
					&&
					newList.get(i).getObiettivi().getDescrizione().equals(newList.get(j).getObiettivi().getDescrizione())
					&&
					newList.get(i).getStato().equals(newList.get(j).getStato())
				  ) {
						newList.remove(i);
				}else {
					if(
							newList.get(i).getObiettivi().getIdObiettivo()==newList.get(j).getObiettivi().getIdObiettivo()
							&&
							newList.get(i).getObiettivi().getDescrizione().equals(newList.get(j).getObiettivi().getDescrizione())
							&&
							!newList.get(i).getStato().equals(newList.get(j).getStato())
						) {
							newList.remove(i);
					}
				}
		}
		
		return newList;
		
	}
	
	public static void main(String[] args) {
		ArrayList<ObiettiviUser> obu=readObUserFromCsvFile();
		obu.add(new ObiettiviUser());
		saveObUserInCsvFile(obu);
		for(ObiettiviUser o:obu)
			o.setUserAccount(new UserAccount());
		for(ObiettiviUser o:obu)
			System.out.println(o.toString());
	}
}
