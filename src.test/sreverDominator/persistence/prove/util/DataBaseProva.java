package sreverDominator.persistence.prove.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DataBase;

public class DataBaseProva {
	public static void main(String[] args) {
		if(DataBase.createDataBase("localhost", "3306", "root", "12345678"))
			System.out.println("DataBase Creato Con Sucesso");
		else {
			System.out.println("problems");
		}
	}
}
