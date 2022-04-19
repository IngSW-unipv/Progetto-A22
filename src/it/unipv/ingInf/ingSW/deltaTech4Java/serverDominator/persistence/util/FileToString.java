package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;;

/**
 * Classe con utilità per trasformare i file in stringhe
 * @author TawaHabib
 * 
 * @version 1.0
 */
public class FileToString {
	
    /**
     * Metodo che trasforma un file in una stringa
     * @param filePath
     * path del file da trasformare 
     * @return
     * stringa che contiene il contenuto del file
     * @throws IOException
     */
    public static String transformFileToString(String filePath) throws IOException{
    	StringBuilder contentBuilder = new StringBuilder();
        BufferedReader br=null;
        try {
        	br= new BufferedReader(new FileReader(filePath));
        	String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null){
            	contentBuilder.append(sCurrentLine+"\n");
            }
        } 
        catch (IOException e){
        	e.printStackTrace();
        }
        finally {
        	br.close();
        }
        return contentBuilder.toString();
    }
}
