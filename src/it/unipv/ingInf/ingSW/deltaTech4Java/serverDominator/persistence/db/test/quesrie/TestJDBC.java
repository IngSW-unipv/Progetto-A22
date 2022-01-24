package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.quesrie;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/serverdomdb";
		String user="sd_sys";
		String pass="12345678";
	try {
		Connection con=DriverManager.getConnection(url,user,pass);
		System.out.println("tt' apposto");
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
