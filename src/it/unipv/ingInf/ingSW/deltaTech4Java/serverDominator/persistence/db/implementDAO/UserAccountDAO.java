package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;
/**
* UserAccount query
* @author TawaHabib
* @version 1.0
* @see AssetOwn
* @see ObiettiviUserDAO
* @see UserAccount
* @see IUserAccountDAO
*/
public class UserAccountDAO implements IUserAccountDAO {
	private Connection conn;
	private String propConn;
	
	public UserAccountDAO(String propConn) {
		
		this.propConn=propConn;
	}
	
	@Override
	public boolean insertUserAccount(UserAccount us) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="INSERT USER_ACCOUNT (MNY, PUNTEGGIO, EMAIL,USERNAME,PASSW) VALUES(?,?,?,?,?)";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, us.getMny());
			st1.setInt(2, us.getPunteggio());
			st1.setString(3,us.getEmail());
			st1.setString(4,us.getUsername());
			st1.setString(5, us.getPassw());

			st1.executeUpdate();
			
			ObiettiviUserDAO ob=new ObiettiviUserDAO(propConn);
			
			for(ObiettiviUser o : us.getObiettiviUsers()) {
				ob.updateStatoObiettiviUserbyId(o);
			}
			
			AssetOwnDAO as=new AssetOwnDAO(propConn);
			for(AssetOwn a: us.getAsetOwns()) {
				as.updateQuantityAssetOwnById(a);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateUserAccount(UserAccount us) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="UPDATE USER_ACCOUNT SET MNY=?, PUNTEGGIO=?, EMAIL=? WHERE USERNAME=? AND PASSW=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, us.getMny());
			st1.setInt(2, us.getPunteggio());
			st1.setString(3,us.getEmail());
			st1.setString(4,us.getUsername());
			st1.setString(5, us.getPassw());

			st1.executeUpdate();
			
			AssetOwnDAO as=new AssetOwnDAO(propConn);
			for(AssetOwn a: us.getAsetOwns()) {
				as.updateQuantityAssetOwnById(a);
			}
			
		}catch (Exception e){
			return insertUserAccount(us);
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateUserAccountUsername(UserAccount us, String newUsername) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="UPDATE USER_ACCOUNT SET USERNAME=? WHERE USERNAME=? AND PASSW=?";
			st1 = conn.prepareStatement(query1);
			st1.setString(1, newUsername);
			st1.setString(2,us.getUsername());
			st1.setString(3, us.getPassw());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public UserAccount getUserAccountById(UserAccount us) {
		UserAccount result=null;

		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM serverDomDB.USER_ACCOUNT WHERE USERNAME=? and PASSW=?";
			st1=conn.prepareStatement(query);
			st1.setString(1, us.getUsername());
			st1.setString(2, us.getPassw());
			rs1=st1.executeQuery();
			while(rs1.next())
			{
				result=new UserAccount(rs1.getString(1));
				result.setMny(rs1.getInt(2));
				result.setPunteggio(rs1.getInt(3));
				result.setEmail(rs1.getString(4));
				result.setPassw(rs1.getString(5));
				result.setObiettiviUsers(this.getObiettiviUserByUserAccount(us));
				result.setAsetOwns(this.getAssetOwndByUserAccount(us));
				break;
				
			}
		}catch (Exception e){
			
			e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="UPDATE USER_ACCOUNT SET PASSW=? WHERE USERNAME=? AND PASSW=?";
			st1 = conn.prepareStatement(query1);
			st1.setString(1, newPassword);
			st1.setString(2,us.getUsername());
			st1.setString(3, us.getPassw());

			st1.executeUpdate();

			
		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public ArrayList<AssetOwn> getAssetOwndByUserAccount(UserAccount us) {
		AssetOwnDAO ass=new AssetOwnDAO(propConn);
		return ass.selectByUserOwner(us);
	}

	@Override
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us) {
		ObiettiviUserDAO obb=new ObiettiviUserDAO(propConn);
		return obb.selectByUserId(us);
	}

	@Override
	public boolean updateUserAccountPunteggio(UserAccount us, int newPunteggio) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="UPDATE USER_ACCOUNT SET PUNTEGGIO=? WHERE USERNAME=? AND PASSW=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, newPunteggio);
			st1.setString(2,us.getUsername());
			st1.setString(3, us.getPassw());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="UPDATE USER_ACCOUNT SET MNY=? WHERE USERNAME=? AND PASSW=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, newmny);
			st1.setString(2,us.getUsername());
			st1.setString(3, us.getPassw());

			st1.executeUpdate();
			
			us.setMny(newmny);

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}
}
