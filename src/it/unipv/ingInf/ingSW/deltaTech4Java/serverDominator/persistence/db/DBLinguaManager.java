package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DbConnection;

/**
 * classe che si ocupa della gestione delle lingue nel db
 * @author TawaHabib
 * @version 1.0
 */

public class DBLinguaManager implements ILanguageManager{
	
	private Connection conn;
	private String propConn;
	
	/**
	 * Crea DBLinguaManager con il connection file path quello passato come argomento
	 * @param propConn
	 */
	public DBLinguaManager(String propConn) {
		this.propConn=propConn;
	}
	@Override
	public ArrayList<String> getAviableLanguage(){
		ArrayList<String> result=new ArrayList<String>();
		PreparedStatement st1;
		ResultSet rs1=null;
		try
		{
			String query="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'serverdomdb' AND TABLE_NAME = 'LINGUA' And COLUMN_NAME NOT IN('CHIAVE', 'TIPO')";
			st1 = conn.prepareStatement(query);
			rs1=st1.executeQuery();
			while(rs1.next()) {
				result.add(rs1.getString(1));
			}
		}catch (Exception e){
			e.printStackTrace();
			}
		return result;
	}
	@Override
	public String getLanguageKayByValue(String value, String lingua) {
		String result="";
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		String s1="SELECT * from LINGUA WHERE "+lingua+"=?";
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1=null;
		
		try
		{
			String query=new String(s1);
			st1 = conn.prepareStatement(query);
			st1.setString(1, value);
			rs1=st1.executeQuery();
			while(rs1.next()){
				result=rs1.getString(1);
				break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		DbConnection.closeConnection(conn);
		return result;
	}
	@Override
	public String getLanguageValueByKay(String kay, String lingua) {
		String result="";
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		String s1="SELECT * from LINGUA WHERE CHIAVE=?";
		int position=this.getLanguegePosition(lingua);
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1=null;
		
		try
		{
			String query=new String(s1);
			st1 = conn.prepareStatement(query);
			st1.setString(1, kay);
			rs1=st1.executeQuery();
			while(rs1.next())
			{
				result= rs1.getString(position);
				break;
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertLanguegeList(Properties list, String lingua) {
		boolean result=false;
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		String insert="INSERT INTO LINGUA (CHIAVE,"+lingua+",Tipo) VALUES(?,?,'esterno')";
		String update="UPDATE LINGUA SET "+lingua+"=? WHERE CHIAVE=?";
		if(!this.exists(lingua)) {
			this.createNewLingua(lingua);
		}
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		for (Entry<Object,Object> s:list.entrySet()) {
			try {
				st1=conn.prepareStatement(insert);
				st1.setString(1, (String)s.getKey());
				st1.setString(2, (String)s.getValue());
				st1.executeUpdate();
				result=true;
			}catch (Exception e) {
				try {
					st1=conn.prepareStatement(update);
					st1.setString(1, (String)s.getValue());
					st1.setString(2, (String)s.getKey());
					st1.executeUpdate();
					result=true;
				}catch (Exception e1) {
					e1.printStackTrace();
					continue;
				}
			}
		}
		DbConnection.closeConnection(conn);
		return result;
	}
	@Override
	public Properties getLanguegeList (String lingua) {
		Properties result=new Properties();
		String s1="SELECT * from LINGUA WHERE";
		int position=this.getLanguegePosition(lingua);
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1=null;
		
		try
		{
			String query=new String(s1);
			st1 = conn.prepareStatement(query);
			rs1=st1.executeQuery();
			while(rs1.next())
			{
				result.setProperty(rs1.getNString(1), rs1.getNString(position));
				break;
			}
		}catch (Exception e){
			if(!exists(lingua))
				System.err.println("Lingua Non esistente");
			e.printStackTrace();
		}

		DbConnection.closeConnection(conn);
		return result;
	}

	public int getLanguegePosition(String lingua) {
		int result =1;
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		conn=DbConnection.startConnection(conn,propConn);
		int a=DataBase.getColumnPosition("serverdomdb", "LINGUA", lingua, conn);
		DbConnection.closeConnection(conn);
		result=a>0?a:1;
		return result;
	}

	public boolean createNewLingua(String lingua) {
		boolean result=false;
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		if(exists(lingua)) {
			System.err.println(lingua+" already  exists");
			return true;
		}
		lingua=lingua.replaceAll(ILanguageManager.invalidLinguaChar, "");
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		try
		{
			String query="ALTER TABLE `LINGUA` ADD COLUMN "+lingua+ " varchar(200) unique";
			st1 = conn.prepareStatement(query);
			st1.executeUpdate(query);
			result=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		DbConnection.closeConnection(conn);
		return result;
	}

	public boolean exists(String lingua) {
		boolean result=false;
		lingua=lingua.replaceAll(invalidLinguaChar, "");
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1=null;
		try
		{
			String query="SELECT count(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'serverdomdb' AND TABLE_NAME = 'LINGUA' AND  COLUMN_NAME=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, lingua);
			rs1=st1.executeQuery();
			while(rs1.next())
			{
				if(rs1.getInt(1)>0) {
					result=true;
					break;
				}	
				result=false;
				break;
			}
		}catch (Exception e){e.printStackTrace();}
		DbConnection.closeConnection(conn);
		return result;
	}
	
	/*prova*/
	public static void main(String[] args) {
		DBLinguaManager man= new DBLinguaManager("resources/config/persistence/dataBase/connWith_sd_sys");
		System.out.println(man.getLanguageKayByValue("EN LEGA 1",FilesLanguageManager.getCurrentLanguage())); 
		System.out.println(man.getLanguegePosition("itALIano"));
		System.out.println(man.getLanguageValueByKay("nome1", "Italiano"));
		man.createNewLingua("HF\"GDF");
	}
	
}
