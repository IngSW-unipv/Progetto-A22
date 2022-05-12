package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.ConnectionFactory;

/**
 * ObPunteggio query
 * @author TawaHabib
 * @version 1.0
 * @see ObiettiviUserDAO
 * @see Obiettivo
 * @see IObiettiviDAO
 * @see DbConnection
 */
public class ObPunteggioDAO implements IObiettiviDAO {
	private Connection conn;
	private String propConn;
	
	
	public ObPunteggioDAO(String propConn) {
		super();
				this.propConn=propConn;
	}
	
	@Override
	public ArrayList<Obiettivo> selectAll() {
		ArrayList<Obiettivo> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);
		int column =man.exists(ILanguageManager.getCurrentLanguage())? man.getLanguegePosition(ILanguageManager.getCurrentLanguage())+1:0;
		
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		Statement st1;
		ResultSet rs1;
		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from ob_punteggio AS T1 JOIN OBIETTIVI AS T2 ON OBIETTIVI_idObiettivo=idObiettivo join lingua on CHIAVE=DESCRIZIONE";
			rs1=st1.executeQuery(query);
			
			while(rs1.next())
			{
				Obiettivo ob=new Obiettivo(rs1.getInt(3), rs1.getString(4+column),rs1.getInt(5));
				ObPunteggio a=new ObPunteggio(ob, rs1.getInt(2));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Obiettivo> selectByRicompensa(Obiettivo obRi) {
		ArrayList<Obiettivo> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);
		int column =man.exists(ILanguageManager.getCurrentLanguage())? man.getLanguegePosition(ILanguageManager.getCurrentLanguage())+1:0;
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * from ob_punteggio AS T1 JOIN OBIETTIVI AS T2  ON OBIETTIVI_idObiettivo=idObiettivo join lingua on CHIAVE=DESCRIZIONE where RICOMPENSA=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, obRi.getRicompensa());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Obiettivo ob=new Obiettivo(rs1.getInt(3), rs1.getString(4+column),rs1.getInt(5));
				ObPunteggio a=new ObPunteggio(ob, rs1.getInt(2));
				//System.out.println(a.toString());

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	
	}

	@Override
	public boolean insertObiettivo(Obiettivo a) {
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st2;
		ObPunteggio o=(ObPunteggio) a;
		boolean esito=true;
		
		ObiettiviDAO f=new ObiettiviDAO(propConn);
		 
		if (!f.insertObiettivo(o.getObiettivi())||o.getPunteggioObiettivo()<0) {
			return false;
		}else {
		
			try
			{
				String query2="INSERT INTO ob_punteggio (OBIETTIVI_idOiettivo,PUNTEGGIO_OBIETTIVO) VALUES(?,?)";
				st2 = conn.prepareStatement(query2);
				st2.setInt(1, o.getObiettivi().getIdObiettivo());
				st2.setInt(2,o.getPunteggioObiettivo());
	
				st2.executeUpdate();
	
			}catch (Exception e){
				
				e.printStackTrace();
				esito=false;
				
			}
			//DbConnection.closeConnection(conn);
			return esito;
		}
	}

	@Override
	public boolean updateObiettiviById(Obiettivo newO) {
		conn=ConnectionFactory.getIstance().getConnection(propConn);
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
	
				st2.executeUpdate();
	
			}catch (Exception e){
				
				e.printStackTrace();
				esito=false;
				
			}
			//DbConnection.closeConnection(conn);
			return esito;
		}
	}

	@Override
	public boolean updateRicompensaObiettivoByRicompensa(Obiettivo oldR, Obiettivo newR) {
		
		ObiettiviDAO f=new ObiettiviDAO(propConn);
		return f.updateRicompensaObiettivoByRicompensa(oldR,newR);
	}

	@Override
	public Obiettivo selectObiettiviById(Obiettivo Id) {
		ObPunteggio risult=null;
		conn=ConnectionFactory.getIstance().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query= "SELECT * FROM  OBIETTIVI join ob_punteggio on (OBIETTIVI_idObiettivo=idObiettivo) WHERE idObiettivo=?";
			st1=conn.prepareStatement(query);
			st1.setInt(1, Id.getIdObiettivo());
			rs1=st1.executeQuery();
			int i=0;
			DBLinguaManager lingua=new DBLinguaManager(propConn);
			while(rs1.next())
			{
				
				Obiettivo a=new Obiettivo(rs1.getInt(1),lingua.getLanguageValueByKay(rs1.getString(2), ILanguageManager.getCurrentLanguage()) ,rs1.getInt(3));
				risult=new ObPunteggio(a, rs1.getInt(5));
				if(++i>0) {
					break;
				}
				
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		Obiettivo a=(Obiettivo)risult;
		return a;
	}

}
