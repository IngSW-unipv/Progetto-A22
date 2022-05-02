package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.ConnectionFactory;

/**
 * Obiettivi query
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivi
 * @see IObiettiviDAO
 * @see DbConnection
 */
public class ObiettiviDAO implements IObiettiviDAO {
	private Connection conn;
	private String propConn;
		
	public ObiettiviDAO(String propConn) {
		super();
		this.propConn=propConn;
	}
	
	@Override
	public ArrayList<Obiettivi> selectAll() {
		ArrayList<Obiettivi> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);
		int posizioneColonna =man.exists(FilesLanguageManager.getCurrentLanguage())? man.getLanguegePosition(FilesLanguageManager.getCurrentLanguage())+1:0;
		
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		Statement st1;
		ResultSet rs1;
		
		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from OBIETTIVI JOIN LINGUA ON CHIAVE=DESCRIZIONE";
			rs1=st1.executeQuery(query);
			
			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(1), rs1.getString(2+posizioneColonna),rs1.getInt(3));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Obiettivi> selectByRicompensa(Obiettivi obRi) {
		ArrayList<Obiettivi> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);
		int position =man.exists(FilesLanguageManager.getCurrentLanguage())? man.getLanguegePosition(FilesLanguageManager.getCurrentLanguage())+1:0;
		
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * from OBIETTIVI JOIN LINGUA ON CHIAVE=DESCRIZIONE WHERE RICOMPENSA=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, obRi.getRicompensa());
			rs1=st1.executeQuery();
			
			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(1), rs1.getString(2+position),rs1.getInt(3));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}
	/*
	 * attenzione a come si usa 'a' deve contenere il valore della descrizione e non una chiave 
	 * il valore però deve esistere nel dataBase e deve essere  lingua corrente 
	 */
	@Override
	public boolean insertObiettivo(Obiettivi a) {
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="INSERT INTO OBIETTIVI (idObiettivo,DESCRIZIONE,RICOMPENSA) VALUES(?,?,?)";
			st1 = conn.prepareStatement(query);
			DBLinguaManager man=new DBLinguaManager(propConn);
			st1.setInt(1, a.getIdObiettivo());
			st1.setString(2,man.getLanguageKayByValue(a.getDescrizione(), FilesLanguageManager.getCurrentLanguage()));
			st1.setInt(3,a.getRicompensa());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}
	/*
	 * attenzione a come si usa 'a' deve contenere il valore della descrizione e non una chiave 
	 * il valore però deve esistere nel dataBase e deve essere  lingua corrente 
	 */
	@Override
	public boolean updateObiettiviById(Obiettivi newO) {
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;
		
		boolean esito=true;
		
		DBLinguaManager man=new DBLinguaManager(propConn);
		
		try
		{
			String query="UPDATE OBIETTIVI SET RICOMPENSA=?, DESCRIZIONE=? WHERE idObiettivo=?";
			
			st1 = conn.prepareStatement(query);
			st1.setInt(1,newO.getRicompensa());
			st1.setString(2,man.getLanguageKayByValue(newO.getDescrizione(), FilesLanguageManager.getCurrentLanguage()));
			st1.setInt(3,newO.getIdObiettivo());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateRicompensaObiettivoByRicompensa(Obiettivi oldR, Obiettivi newR) {
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="UPDATE OBIETTIVI SET RICOMPENSA=? WHERE RICOMPENSA=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(2, oldR.getRicompensa());
			st1.setInt(1,newR.getRicompensa());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public Obiettivi selectObiettiviById(Obiettivi Id) {
		Obiettivi risult=new Obiettivi();
		DBLinguaManager man=new DBLinguaManager(propConn);
		int posizioneLingua =man.exists(FilesLanguageManager.getCurrentLanguage())? man.getLanguegePosition(FilesLanguageManager.getCurrentLanguage())+1:0;
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query= "SELECT * FROM OBIETTIVI JOIN LINGUA ON CHIAVE=DESCRIZIONE WHERE idObiettivo=? ";
			st1=conn.prepareStatement(query);
			st1.setInt(1, Id.getIdObiettivo());
			rs1=st1.executeQuery();
			int i=0;
			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(1), rs1.getString(2+posizioneLingua),rs1.getInt(3));
				risult.setDescrizione(a.getDescrizione());
				risult.setIdObiettivo(a.getIdObiettivo());
				risult.setRicompensa(a.getRicompensa());
				if(++i>0) {
					break;
				}
				
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return risult;
	
	}

}
