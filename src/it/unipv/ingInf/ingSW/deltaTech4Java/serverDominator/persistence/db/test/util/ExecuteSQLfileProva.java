package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.ExecuteSQLfiel;

public class ExecuteSQLfileProva {
	public static void main(String[] args) 
    {
		
		System.out.println("crea schema...");
		if(ExecuteSQLfiel.executeSqlFile("resources/databaseDefineSchema/dataBaseSchema.sql", "resources/config/dbConfig/connWith_root"))
			System.out.println("schema done");
		else
			System.out.println("problems");
		System.out.println("popola schema...");
		if(ExecuteSQLfiel.executeSqlFile("resources/databaseDefineSchema/popolaSchema.sql", "resources/config/dbConfig/connWith_root"))
			System.out.println("popola done");
		else
			System.out.println("problem in popola");
			
    }
}
