package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.FileToString;

public class FileToStringProva {
	public static void main(String[] args) 
    {
        String filePath = "resources/databaseDefineSchema/dataBaseSchema.sql";
 
        System.out.println( FileToString.transformFileToString( filePath ) );
    }
}
