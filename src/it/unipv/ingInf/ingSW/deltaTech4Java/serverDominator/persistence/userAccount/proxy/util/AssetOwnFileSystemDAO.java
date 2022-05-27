package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.proxy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwnId;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

/**
 * Salvataggio Asset Posseduto dal giocatore nel file system 
 * (file semistrutturato, tipo csv)
 * @author TawaHabib
 * @version 1.0
 * @see AssetOwn
 */
public class AssetOwnFileSystemDAO {

	public static final String COMMA_DELIMITER = ",";
	
	public static final String LINE_SEPARATOR = "\n";

	public static final String FILE_NAME = "resources/config/persistence/playerAssetOwn";

	public static final String FILE_HEADER = "ID, LIVELLO , COSTO, QUANTITA, DESCRIZIONE, NOME";
	
	public static final int ID = 0;
	public static final int LIVELLO = 1;
	public static final int COSTO = 2;
	public static final int QUANTITA = 3; 
	public static final int DESCRIZIONE = 4;
	public static final int NOME = 5;
    
    
    /**
     * slva asset in file semistrutturato
     * @param asetOwns
     * lista asset che si vogliono salvare
     * @param fileName
     * percorso del file in cui si vogliono salvare
     */
    public static boolean saveInCsvFile(ArrayList<AssetOwn> asetOwns,String fileName) {
    	File fls=new File(fileName);
    	if (!fls.exists()) {
    		return saveFile(getAssetListWithoutDuplicate(asetOwns),fileName);
    	}
    	else {
    		ArrayList<AssetOwn> ass=readAssetOwnFromCsvFile(fileName);
    		ass.addAll(asetOwns);
    		return saveFile(getAssetListWithoutDuplicate(ass), fileName);
    	}
    }

    
    /**
     * salva assetOwn n un percorso redefinito
     * @param asetOwns
     */
    public static boolean saveInCsvFile(ArrayList<AssetOwn> asetOwns) {
       	File fls=new File(FILE_NAME);
    	if (!fls.exists()) {
    		return saveFile(getAssetListWithoutDuplicate(asetOwns),FILE_NAME);
    	}
    	else {
    		ArrayList<AssetOwn> ass=readAssetOwnFromCsvFile(FILE_NAME);
    		ass.addAll(asetOwns);
    		return saveFile(getAssetListWithoutDuplicate(ass), FILE_NAME);
    	}
    }
    /**
     * prendi assetOwn nel file predefinito
     * @return lista AsetOwn
     */
    public static ArrayList<AssetOwn> readAssetOwnFromCsvFile() {
    	return readAssetOwnFromCsvFile(FILE_NAME);
    }
    
	private static boolean saveFile(ArrayList<AssetOwn> asetOwns,String fileName) {
		boolean ris=true;
		FileWriter fileWriter = null;
		asetOwns=getAssetListWithoutDuplicate(asetOwns);
		
		try {
			
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(LINE_SEPARATOR);
			
			for (AssetOwn a : asetOwns) {
				
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
			ris=false;
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
		return ris;
	}
	
	/**
	 * legge da file semistruttorato gli asset posseduti
	 * valori duplicari--> vale la con numero pi� alto
	 * @param fileName
	 * percorso del file da cui si vuole leggere
	 * @return lista asset presenti nei file
	 */
	public static ArrayList<AssetOwn> readAssetOwnFromCsvFile(String fileName) {
		
		BufferedReader fileReader = null;
		
		ArrayList<AssetOwn> asetOwn = new ArrayList<AssetOwn> ();
		
		try {
			
			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			
			while ((line = fileReader.readLine()) != null) {
				
				String[] tokens = line.split(COMMA_DELIMITER);
				
				if (tokens.length == 6) {
					try {
					Asset a =new Asset(Integer.valueOf(tokens[ID]),Integer.valueOf(tokens[COSTO]),tokens[NOME],tokens[DESCRIZIONE],Integer.valueOf(tokens[LIVELLO]));
					AssetOwn ao = new AssetOwn(new AssetOwnId(a, null), Integer.valueOf(tokens[QUANTITA]));
					asetOwn.add(ao);
					asetOwn=getAssetListWithoutDuplicate(asetOwn);
					}
					//ignora righe che generano ecezzioni
					catch (Exception e) {
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
		
		return asetOwn;
	}
	

	private static ArrayList<AssetOwn>  getAssetListWithoutDuplicate(ArrayList<AssetOwn> ass){
		ArrayList<AssetOwn> newList=new ArrayList<AssetOwn>();
		for(AssetOwn a : ass) {
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
							newList.remove(i);
					}
				}
		}
		
		return newList;
		
	}
	
/*
 * prova
 */
	    public static void main(String[] args) {
			ArrayList<AssetOwn> ab=readAssetOwnFromCsvFile(FILE_NAME);
			
			UserAccount us=new UserAccount();
			for(AssetOwn ass: ab){
				ass.setUserAccount(us);
				System.out.println(ass.toString());
			}
			saveInCsvFile(ab);
		}
}
