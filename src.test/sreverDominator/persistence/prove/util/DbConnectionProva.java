package sreverDominator.persistence.prove.util;

import java.sql.Connection;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.DbConnection;

public class DbConnectionProva {

	public static void main(String[] args) {
		
			System.out.println("1");
			Connection con=null;
			System.out.println("2");
			con=DbConnection.startConnection(con,"resources/config/dbConfig/connWith_sd_sys");
			System.out.println("3");
			if(DbConnection.isOpen(con)) {
				System.out.println("4")
				;}
			System.out.println("5");
			DbConnection.closeConnection(con);
			System.out.println("6");
		
		
			System.out.println("1");
			Connection conn=null;
			System.out.println("2");
			con=DbConnection.startConnection(conn,"resources/config/dbConfig/connWith_root");
			System.out.println("3");
			if(DbConnection.isOpen(conn)) {
				System.out.println("4")
				;}
			System.out.println("5");
			DbConnection.closeConnection(conn);
			System.out.println("6");
	
	}

}
