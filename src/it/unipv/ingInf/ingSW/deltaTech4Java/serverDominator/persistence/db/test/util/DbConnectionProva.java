package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.util;

import java.sql.Connection;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.DbConnection;

public class DbConnectionProva {

	public static void main(String[] args) {
		try {
			System.out.println("1");
			Connection con=null;
			System.out.println("2");
			con=DbConnection.startConnection(con,"resorces/config.txt");
			System.out.println("3");
			if(DbConnection.isOpen(con)) {
				System.out.println("4")
				;}
			System.out.println("5");
			DbConnection.closeConnection(con);
			System.out.println("6");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
