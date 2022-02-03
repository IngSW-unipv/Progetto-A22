package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.IAssetDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.DbConnection;

/**
 * Asset query
 * @author ME
 * @version 1.0
 * @see Asset
 * @see IAssetDAO
 * @see Dbconnection 
 */
public class AssetDAO implements IAssetDAO {
	private Connection conn;
	private String propConn;
	public AssetDAO(String propConn) {
		
		this.propConn=propConn;
	}
	
	@Override
	public ArrayList<Asset> selectAll() {
		ArrayList<Asset> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from ASSET as t1";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}
	
	@Override
	public ArrayList<Asset> selectByPrice(Asset assInput) {
		ArrayList<Asset> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM ASSET WHERE COSTO=?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, assInput.getCosto());

			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));

				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertAsset(Asset a) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="insert  into asset values (?,?,?,?,?)"
					//+ "VALUES (?,?,?,?,?)"
					;
			st1 = conn.prepareStatement(query1);
			st1.setInt		(1, a.getIdAsset());
			st1.setInt		(2,a.getCosto());
			st1.setString	(3,a.getNome());
			st1.setString	(4,a.getDescrizione());
			st1.setInt		(5,a.getLivello());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}
	
	@Override
	public boolean updateAssetById(Asset newA) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="UPDATE ASSET SET COSTO=?,NOME=?,DESCRIZIONE=?,LIVELLO=? WHERE idAsset=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(5, newA.getIdAsset());
			st1.setInt(1,newA.getCosto());
			st1.setString(2,newA.getNome());
			st1.setString(3,newA.getDescrizione());
			st1.setInt(4,newA.getLivello());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updatePriceAssetByPrice(Asset oldPrice, Asset newPrice) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="UPDATE ASSET SET COSTO=? WHERE COSTO=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(2, oldPrice.getCosto());
			st1.setInt(1,newPrice.getCosto());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public Asset selectAssetById(Asset id) {
		Asset result=null;

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM ASSET WHERE idAsset=?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, id.getCosto());

			rs1=st1.executeQuery();

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));

				result=a;
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

}
