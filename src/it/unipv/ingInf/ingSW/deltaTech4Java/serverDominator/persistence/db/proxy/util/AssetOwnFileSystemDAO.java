package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwnId;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

/**
 * @author ME
 * @version 1.0
 * @see AsetOwn
 */
public class AssetOwnFileSystemDAO {

	private static final String COMMA_DELIMITER = ",";
	
	private static final String LINE_SEPARATOR = "\n";

	private static final String FILE_NAME = "resources/config/persistence/playerAssetOwn";

	private static final String FILE_HEADER = "ID, LIVELLO , COSTO, QUANTITA, DESCRIZIONE, NOME";
	
    private static final int ID = 0;
    private static final int LIVELLO = 1;
    private static final int COSTO = 2;
    private static final int QUANTITA = 3; 
    private static final int DESCRIZIONE = 4;
    private static final int NOME = 5;
    
    
    /**
     * slva asset in file semistrutturato
     * @param asetOwns
     * lista asset che si vogliono salvare
     * @param fileName
     * percorso del file in cui si vogliono salvare
     */
    public static void saveInCsvFile(ArrayList<AsetOwn> asetOwns,String fileName) {
    	File fls=new File(fileName);
    	if (!fls.exists()) {
    		saveFile(getAssetListWithoutDuplicate(asetOwns),fileName);
    	}
    	else {
    		ArrayList<AsetOwn> ass=readAssetOwnFromCsvFile(fileName);
    		ass.addAll(asetOwns);
    		saveFile(getAssetListWithoutDuplicate(ass), fileName);
    	}
    }

    
    /**
     * salva assetOwn n un percorso redefinito
     * @param asetOwns
     */
    public static void saveInCsvFile(ArrayList<AsetOwn> asetOwns) {
       	File fls=new File(FILE_NAME);
    	if (!fls.exists()) {
    		saveFile(getAssetListWithoutDuplicate(asetOwns),FILE_NAME);
    	}
    	else {
    		ArrayList<AsetOwn> ass=readAssetOwnFromCsvFile(FILE_NAME);
    		ass.addAll(asetOwns);
    		saveFile(getAssetListWithoutDuplicate(ass), FILE_NAME);
    	}
    }
    /**
     * prendi assetOwn nel file predefinito
     * @return lista AsetOwn
     */
    public static ArrayList<AsetOwn> readAssetOwnFromCsvFile() {
    	return readAssetOwnFromCsvFile(FILE_NAME);
    }
	private static void saveFile(ArrayList<AsetOwn> asetOwns,String fileName) {
		
		FileWriter fileWriter = null;
		asetOwns=getAssetListWithoutDuplicate(asetOwns);
		
		try {
			
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(LINE_SEPARATOR);
			
			for (AsetOwn a : asetOwns) {
				
				fileWriter.append(String.valueOf(a.getAsset().getIdAsset()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(a.getAsset().getLivello()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(a.getAsset().getCosto()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(a.getQuantita()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(a.getAsset().getDescrizione());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(a.getAsset().getNome());
				fileWriter.append(LINE_SEPARATOR);
			}
	
		} catch (Exception e) {
			
			System.err.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			
		} 
		finally {
			
			try {
				
				fileWriter.flush();
				fileWriter.close();
				
			} catch (IOException e) {
				System.err.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}     
		}
	}
	
	/**
	 * legge da file semistruttorato gli asset posseduti
	 * valori duplicari--> vale la con numero più alto
	 * @param fileName
	 * percorso del file da cui si vuole leggere
	 * @return lista asset presenti nei file
	 */
	public static ArrayList<AsetOwn> readAssetOwnFromCsvFile(String fileName) {
		
		BufferedReader fileReader = null;
		
		ArrayList<AsetOwn> AsetOwn = new ArrayList<AsetOwn> ();
		
		try {
			
			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			
			while ((line = fileReader.readLine()) != null) {
				
				String[] tokens = line.split(COMMA_DELIMITER);
				
				if (tokens.length == 6) {
					
					Asset a =new Asset(Integer.valueOf(tokens[ID]),Integer.valueOf(tokens[COSTO]),tokens[NOME],tokens[DESCRIZIONE],Integer.valueOf(tokens[LIVELLO]));
					AsetOwn ao = new AsetOwn(new AsetOwnId(a, null), Integer.valueOf(tokens[QUANTITA]));
					AsetOwn.add(ao);
					AsetOwn=getAssetListWithoutDuplicate(AsetOwn);
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
		
		return AsetOwn;
	}

	private static ArrayList<AsetOwn>  getAssetListWithoutDuplicate(ArrayList<AsetOwn> ass){
		ArrayList<AsetOwn> newList=new ArrayList<AsetOwn>();
		for(AsetOwn a : ass) {
			if(!newList.contains(a)) {
				newList.add(a);
			}
		}
		
		for(int i=0; i<newList.size();i++) {
			for(int j=i+1;j<newList.size();j++)
				if(
					newList.get(i).getAsset().getIdAsset()==newList.get(j).getAsset().getIdAsset()
					&&
					newList.get(i).getAsset().getLivello()==newList.get(j).getAsset().getLivello()
					&&
					newList.get(i).getQuantita()==newList.get(j).getQuantita()
				  ) {
						newList.remove(i);
				}else {
					if(
						newList.get(i).getAsset().getIdAsset()==newList.get(j).getAsset().getIdAsset()
						&&
						newList.get(i).getAsset().getLivello()==newList.get(j).getAsset().getLivello()
						&&
						newList.get(i).getQuantita()!=newList.get(j).getQuantita()
						) {
							newList.get(i).setQuantita(newList.get(j).getQuantita());
							newList.remove(j);
					}
				}
		}
		
		return newList;
		
	}
	
/*
 * prova
 */
	    public static void main(String[] args) {
			ArrayList<AsetOwn> ab=readAssetOwnFromCsvFile(FILE_NAME);
			
			UserAccount us=new UserAccount();
			for(AsetOwn ass: ab){
				ass.setUserAccount(us);
				System.out.println(ass.toString());
			}
			saveInCsvFile(ab);
		}
}
