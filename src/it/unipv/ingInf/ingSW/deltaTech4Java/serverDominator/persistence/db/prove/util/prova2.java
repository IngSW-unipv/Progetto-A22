package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.prove.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class prova2 {

	public static void main(String[] args) {
		try {
		BufferedReader csvReader = new BufferedReader(new FileReader("asd"));
		String row;
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split("\n");
		    // do something with the data
		   System.out.println(data);
		}
		csvReader.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
