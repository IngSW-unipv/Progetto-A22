package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.userOwn.obiettivi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.linguaManager.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.linguaManager.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.obiettivi.DBObiettiviDOAFactory;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.obiettivi.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;

/**
 * ObiettiviUser query
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivo
 * @see ObiettiviUser
 * @see UserAccount
 * @see IObiettiviUserDAO
 */
public class ObiettiviUserDAO implements IObiettiviUserDAO {
	private Connection conn;
	private String propConn;
	public ObiettiviUserDAO(String propConn) {
		super();
		this.propConn=propConn;
	}
	@Override
	public ArrayList<ObiettiviUser> selectAll() {
		ArrayList<ObiettiviUser> result = new ArrayList<>();

		conn=SessionFactory.getSession().getConnection(propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM obiettivi JOIN obiettivi_user on idObiettivo=OBIETTIVI_idObiettivo";
			st1=conn.createStatement();
			rs1=st1.executeQuery(query);
			DBLinguaManager man=new DBLinguaManager(propConn);
			while(rs1.next())
			{
				Obiettivo a=new Obiettivo(rs1.getInt(1),man.getLanguageValueByKay(rs1.getString(2), ILanguageManager.getCurrentLanguage()) ,rs1.getInt(3));
				UserAccount b =new UserAccount(rs1.getString(5));
				ObiettiviUser c=new ObiettiviUser(a,b,rs1.getString(4));
				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<ObiettiviUser> selectByUserId(UserAccount accInput) {
		ArrayList<ObiettiviUser> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);

		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM obiettivi_user JOIN obiettivi on idObiettivo=OBIETTIVI_idObiettivo JOIN  USER_ACCOUNT ON USER_ACCOUNT_USERNAME=USERNAME WHERE USERNAME=? and Passw=?";
			st1=conn.prepareStatement(query);
			st1.setString(1, accInput.getUsername());
			st1.setString(2, accInput.getPassw());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Obiettivo a=new Obiettivo(rs1.getInt(4),man.getLanguageValueByKay(rs1.getString(5),ILanguageManager.getCurrentLanguage()),rs1.getInt(6));
				UserAccount b =new UserAccount(rs1.getString(7), rs1.getInt(8));
				ObiettiviUser c=new ObiettiviUser(a,b,rs1.getString(1));
				result.add(c);
			}
		}catch (Exception e){e.printStackTrace();}
		
		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<ObiettiviUser> selectByObiettiviId(Obiettivo obInput) {
		ArrayList<ObiettiviUser> result = new ArrayList<>();

		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM obiettivi_user WHERE OBIETTIVI_idObiettivo=?";
			st1=conn.prepareStatement(query);
			st1.setInt(1, obInput.getIdObiettivo());
			rs1=st1.executeQuery();
			IObiettiviDAO ob=DBObiettiviDOAFactory.getIObiettiviDAO(obInput, propConn);
			Obiettivo obiettivo;
			while(rs1.next())
			{
				UserAccount a=new UserAccount(rs1.getString(2));
				obiettivo=ob.selectObiettiviById(obInput);
				ObiettiviUser b=new ObiettiviUser(obiettivo,a,rs1.getString(1));
				result.add(b);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertObiettiviUser(ObiettiviUser o) {
		conn=SessionFactory.getSession().getConnection(propConn);
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

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateStatoObiettiviUserbyId(ObiettiviUser newOU) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;
		if(this.existObiettiviUser(newOU)) {
			conn=SessionFactory.getSession().getConnection(propConn);
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
		//DbConnection.closeConnection(conn);
		return esito;
	}
	
	public boolean existObiettiviUser(ObiettiviUser newOU) {
		conn=SessionFactory.getSession().getConnection(propConn);
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
			while(rs1.next()) {
				if(rs1.getInt(1)<1) {
					esito=false;
				}
				break;
			}
		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}
}
