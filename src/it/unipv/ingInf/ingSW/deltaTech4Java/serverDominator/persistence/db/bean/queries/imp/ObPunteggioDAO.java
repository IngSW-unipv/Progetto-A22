package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.DbConnection;

public class ObPunteggioDAO implements IObiettiviDAO {
	private Connection conn;
	private String propConn;
	public ObPunteggioDAO(String propConn) {
		super();
		this.propConn=propConn;
	}
	
	@Override
	public ArrayList<Obiettivi> selectAll() {
		ArrayList<Obiettivi> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from ob_punteggio AS T1 JOIN OBIETTIVI AS T2 ON ";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Obiettivi ob=new Obiettivi(rs1.getInt(3), rs1.getString(4),rs1.getInt(5));
				ObPunteggio a=new ObPunteggio(ob, rs1.getInt(2));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Obiettivi> selectByRicompensa(Obiettivi obRi) {
		ArrayList<Obiettivi> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * from ob_punteggio AS T1 JOIN OBIETTIVI AS T2 ON where RICOMPENSA=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, obRi.getRicompensa());
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Obiettivi ob=new Obiettivi(rs1.getInt(3), rs1.getString(4),rs1.getInt(5));
				ObPunteggio a=new ObPunteggio(ob, rs1.getInt(2));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	
	}

	@Override
	public boolean insertObiettivo(Obiettivi a) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st2;
		ObPunteggio o=(ObPunteggio) a;
		boolean esito=true;
		
		ObiettiviDAO f=new ObiettiviDAO(propConn);
		 
		if (!f.insertObiettivo(a)||o.getPunteggioObiettivo()<0) {
			return false;
		}else {
		
			try
			{
				String query2="INSERT INTO ASSET (OBIETTIVI_idOiettivo,PUNTEGGIO_OBIETTIVO) VALUES(?,?)";
				st2 = conn.prepareStatement(query2);
				st2.setInt(1, o.getObiettivi().getIdObiettivo());
				st2.setInt(2,o.getPunteggioObiettivo());
	
				st2.executeUpdate(query2);
	
			}catch (Exception e){
				
				e.printStackTrace();
				esito=false;
				
			}
			DbConnection.closeConnection(conn);
			return esito;
		}
	}

	@Override
	public boolean updateObiettiviById(Obiettivi newO) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st2;
		ObPunteggio o=(ObPunteggio) newO;
		boolean esito=true;
		
		ObiettiviDAO f=new ObiettiviDAO(propConn);
		 
		if (!f.updateObiettiviById(newO)||o.getPunteggioObiettivo()<0) {
			return false;
		}else {
		
			try
			{
				String query2="UPDATE OB_PUNTEGGIO SET PUNTEGGIO_OBIETTIVO=? WHERE OBIETTIVI_idObiettivo=?";
				st2 = conn.prepareStatement(query2);
				st2.setInt(2, o.getObiettivi().getIdObiettivo());
				st2.setInt(1,o.getPunteggioObiettivo());
	
				st2.executeUpdate(query2);
	
			}catch (Exception e){
				
				e.printStackTrace();
				esito=false;
				
			}
			DbConnection.closeConnection(conn);
			return esito;
		}
	}

	@Override
	public boolean updateRicompensaObiettivoByRicompensa(Obiettivi oldR, Obiettivi newR) {
		
		ObiettiviDAO f=new ObiettiviDAO(propConn);
		return f.updateRicompensaObiettivoByRicompensa(oldR,newR);
	}

	@Override
	public Obiettivi selectObiettiviById(Obiettivi Id) {
		ObPunteggio risult=null;
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query= "SELECT * FROM OBIETTIVI join ob_punteggio on (OBIETTIVI_idPunteggio=idPunteggio) WHERE idObiettivo=? ";
			st1=conn.prepareStatement(query);
			st1.setInt(1, Id.getIdObiettivo());
			rs1=st1.executeQuery();
			int i=0;
			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(1), rs1.getString(2),rs1.getInt(3));
				risult=new ObPunteggio(a, rs1.getInt(5));
				if(++i>0) {
					break;
				}
				
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		Obiettivi a=(Obiettivi)risult;
		return a;
	}

}
