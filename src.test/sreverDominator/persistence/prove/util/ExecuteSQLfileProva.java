package sreverDominator.persistence.prove.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.scriptRunner.ExecuteSQLfiel;

public class ExecuteSQLfileProva {
	public static void main(String[] args) 
    {
		try {
		
		System.out.println("crea sch:ema...");
		if(ExecuteSQLfiel.executeSqlFile("resources/databaseDefineSchema/dataBaseSchema.sql", "resources/config/persistence/dataBase/connWith_root"))
			System.out.println("schema done");
		else
			System.out.println("problems");
		System.out.println("popola schema...");
		if(ExecuteSQLfiel.executeSqlFile("resources/databaseDefineSchema/popolaSchema.sql", "resources/config/persistence/dataBase/connWith_root"))
			System.out.println("popola done");
		else
			System.out.println("problem in popola");
		} catch (Exception e) {
			// TODO: handle exception
		}
			
    }
}
