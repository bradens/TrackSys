package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import tracksys.Resources;
import tracksys.entity.Notification;
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
	/**
	 * Get all past transactions.
	 * @return
	 */
	public List<Transaction> getTransactions()
	{
		List<Transaction> transaction = new ArrayList<Transaction>();
		//make a real query to do stuff and stuff
		String query = "";
		
		try
		{
			Transaction tempTrans;
			String resDate, resTime;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				resDate = rs.getString("date").split(" ")[0];
				resTime = rs.getString("date").split(" ")[1];
				String[] dateParts = resDate.split("-");
				String[] timeParts = resTime.split(":");
				
				//make this do stuff and stuff
				//tempTrans = new Transaction();
				//transaction.add(tempTrans);
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
