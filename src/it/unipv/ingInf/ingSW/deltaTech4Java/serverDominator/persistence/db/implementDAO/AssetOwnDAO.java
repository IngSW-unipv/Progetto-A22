package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IAssetOwnDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwnId;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DbConnection;

/**
 * AssetOwm query
 * @author ME
 * @version 1.0
 * @see AsetOwn
 * @see UserAcount
 * @see IAssetOwnDAO
 * @see Dbconnection 
 */
public class AssetOwnDAO implements IAssetOwnDAO {
	private Connection conn;
	private String propConn;
	public AssetOwnDAO(String propConn) {
		
		this.propConn=propConn;
	}

	@Override
	public ArrayList<AsetOwn> selectAll() {
		ArrayList<AsetOwn> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "
						+ "FROM ASET_OWN JOIN ASSET on idAsset=Asset_idAsset";
			st1=conn.createStatement();
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(4), rs1.getInt(5),rs1.getString(6),rs1.getString(7),rs1.getInt(8));
				UserAccount b =new UserAccount(rs1.getString(2),0);
				AsetOwnId c= new AsetOwnId(a,b);
				AsetOwn d=new AsetOwn(c, rs1.getInt(3));
				result.add(d);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<AsetOwn> selectByUserOwner(UserAccount assInput) {
		ArrayList<AsetOwn> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "
						+ "FROM ASET_OWN JOIN ASSET on idAsset=Asset_idAsset"
						+ "JOIN  USER_ACCOUNT ON USER_ACCOUNT_USERNAME=USERNAME"
						+ "WHERE USER_ACCOUNT_USERNAME=? and Passw=?";
			st1=conn.prepareStatement(query);
			st1.setString(1, assInput.getUsername());
			st1.setString(2, assInput.getPassw());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(4), rs1.getInt(5),rs1.getString(6),rs1.getString(7),rs1.getInt(8));
				UserAccount b =new UserAccount(rs1.getString(9), rs1.getInt(10));
				AsetOwnId c= new AsetOwnId(a,b);
				AsetOwn d=new AsetOwn(c, rs1.getInt(3));
				result.add(d);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<UserAccount> selectByAssetOwnd(Asset assInput) {
		ArrayList<UserAccount> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * "
						+ "FROM ASET_OWN WHERE ASSET_idAsset=?";
			st1=conn.prepareStatement(query);
			st1.setInt(1, assInput.getIdAsset());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				UserAccount a=new UserAccount(rs1.getString(2));
				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertAssetOwn(AsetOwn a) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="insert  into ASET_OWN (ASSET_idAsset,USER_ACCOUNT_USERNAME,QUANTITA)values (?,?,?)"
					//+ "VALUES (?,?,?,?,?)"
					;
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, a.getPrimaryKey().getAsset().getIdAsset());
			st1.setString(2,a.getPrimaryKey().getUserAccount().getUsername());
			st1.setInt(3, a.getQuantita());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateQuantityAssetOwnById(AsetOwn newA) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;
		if(this.existObiettiviUser(newA)) {
			try
			{
				String query1="UPDATE ASET_OWN SET QUANTITA=? WHERE ASSET_idAsset=? AND USER_ACCOUNT_USERNAME=?"
						//+ "VALUES (?,?,?,?,?)"
						;
				st1 = conn.prepareStatement(query1);
				st1.setInt(1, newA.getQuantita());
				st1.setInt(2,newA.getPrimaryKey().getAsset().getIdAsset());
				st1.setString(3, newA.getPrimaryKey().getUserAccount().getUsername());
	
				st1.executeUpdate();
	
			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}
		}
		else {
			esito=this.insertAssetOwn(newA);
		}

		DbConnection.closeConnection(conn);
		return esito;
		
	}
	
	public boolean existObiettiviUser(AsetOwn newOU) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;
		boolean esito=true;

		try
		{
			String query1="select count(*) FROM AsetOwn WHERE ASSET_idAsset=? AND USER_ACCOUNT_USERNAME=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1,newOU.getPrimaryKey().getAsset().getIdAsset());
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
