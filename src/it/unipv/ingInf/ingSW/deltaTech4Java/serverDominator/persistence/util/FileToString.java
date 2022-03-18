package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;;

public class FileToString {
	
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
