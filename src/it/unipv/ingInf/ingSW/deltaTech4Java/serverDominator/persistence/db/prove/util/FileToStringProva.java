package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.prove.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.FileToString;

public class FileToStringProva {
	public static void main(String[] args) 
    {
        String filePath = "resources/databaseDefineSchema/dataBaseSchema.sql";
        try {

            System.out.println( FileToString.transformFileToString( filePath ) );
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
