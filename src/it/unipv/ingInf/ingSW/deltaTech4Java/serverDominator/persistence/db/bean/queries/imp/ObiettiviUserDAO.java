package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.IObiettiviUserDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.DbConnection;

/**
 * ObiettiviUser query
 * @author ME
 * @version 1.0
 * @see Obiettivi
 * @see ObiettiviUser
 * @see UserAccount
 * @see IObiettiviUserDAO
 */
public class ObiettiviUserDAO implements IObiettiviUserDAO {
	private Connection conn;
	private String propConn;
	public ObiettiviUserDAO(String propConn) {
		
		this.propConn=propConn;
	}
	@Override
	public ArrayList<ObiettiviUser> selectAll() {
		ArrayList<ObiettiviUser> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "
					+ "FROM obiettivi JOIN obiettivi_user on idObiettivo=OBIETTIVI_idObiettivo";
			st1=conn.createStatement();
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(1), rs1.getString(2),rs1.getInt(3));
				UserAccount b =new UserAccount(rs1.getString(5),0);
				ObiettiviUser c=new ObiettiviUser(a,b,rs1.getString(4));
				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<ObiettiviUser> selectByUserId(UserAccount accInput) {
		ArrayList<ObiettiviUser> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "+
					"FROM obiettivi_user JOIN obiettivi on idObiettivo=OBIETTIVI_idObiettivo "+
					"JOIN  USER_ACCOUNT ON USER_ACCOUNT_USERNAME=USERNAME"+
					"WHERE USERNAME=? and Passw=?";
			st1=conn.prepareStatement(query);
			st1.setString(1, accInput.getUsername());
			st1.setString(2, accInput.getPassw());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Obiettivi a=new Obiettivi(rs1.getInt(4), rs1.getString(5),rs1.getInt(6));
				UserAccount b =new UserAccount(rs1.getString(7), rs1.getInt(8));
				ObiettiviUser c=new ObiettiviUser(a,b,rs1.getString(1));
				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}
		
		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<ObiettiviUser> selectByObiettiviId(Obiettivi obInput) {
		ArrayList<ObiettiviUser> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "
						+ "FROM obiettivi_user WHERE OBIETTIVI_idObiettivo=?";
			st1=conn.prepareStatement(query);
			st1.setInt(1, obInput.getIdObiettivo());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				UserAccount a=new UserAccount(rs1.getString(2));
				ObiettiviUser b=new ObiettiviUser(obInput,a,rs1.getString(1));
				result.add(b);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertObiettiviUser(ObiettiviUser o) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="insert  into OBIETTIVI_USER (STATO,USER_ACCOUNT_USERNAME,OBIETTIVI_idObiettivo)values (?,?,?)";
			st1 = conn.prepareStatement(query1);
			st1.setString(1, o.getStato());
			st1.setString(2,o.getPrimaryKey().getUserAccount().getUsername());
			st1.setInt(3, o.getPrimaryKey().getObiettivo().getIdObiettivo());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateStatoObiettiviUserbyId(ObiettiviUser newOU) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;
		if(this.existObiettiviUser(newOU)) {
			try
			{
				String query1="UPDATE obiettivi_user SET STATO=? WHERE OBIETTIVI_idObiettivo=? AND USER_ACCOUNT_USERNAME=?";
				st1 = conn.prepareStatement(query1);
				st1.setString(1, newOU.getStato());
				st1.setInt(2,newOU.getPrimaryKey().getObiettivo().getIdObiettivo());
				st1.setString(3, newOU.getPrimaryKey().getUserAccount().getUsername());
	
				st1.executeUpdate();
	
			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}
		}
		else {
			esito=this.insertObiettiviUser(newOU);
		}
		DbConnection.closeConnection(conn);
		return esito;
	}
	
	public boolean existObiettiviUser(ObiettiviUser newOU) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;
		boolean esito=true;

		try
		{
			String query1="select count(*) FROM obiettivi_user WHERE OBIETTIVI_idObiettivo=? AND USER_ACCOUNT_USERNAME=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1,newOU.getPrimaryKey().getObiettivo().getIdObiettivo());
			st1.setString(2, newOU.getPrimaryKey().getUserAccount().getUsername());

			rs1=st1.executeQuery();
			
			if(rs1.getInt(1)<1) {
				esito=false;
			}

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}
}
