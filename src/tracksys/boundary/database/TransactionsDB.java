package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Transaction;


public class TransactionsDB {
	private Connection conn = null;
	public TransactionsDB() {
		try
		{
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection(Resources.database, Resources.username, Resources.password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Could not close transactions connection");
		}
	}
	
	/**
	 * Get all past transactions.
	 * @return
	 */
	public List<Transaction> getTransactions(int clubID)
	{
		List<Transaction> transaction = new ArrayList<Transaction>();
		String query = "SELECT * FROM tracksys.transactions WHERE clubid='" + clubID + "' ORDER BY paymenttime DESC LIMIT 100";
		
		try
		{
			Transaction tempTrans;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				tempTrans = new Transaction(Integer.parseInt(rs.getString("id")),Integer.parseInt(rs.getString("clubid")),Float.parseFloat(rs.getString("paymentfee")),Resources.DATE_FORMAT.parse(rs.getString("paymenttime")), rs.getString("comment"));
				transaction.add(tempTrans);
			}
			return transaction;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
