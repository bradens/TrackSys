package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import tracksys.Resources;
import tracksys.entity.Club;
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
	public List<Transaction> getTransactions(int clubID)
	{
		List<Transaction> transaction = new ArrayList<Transaction>();
		String query = "SELECT * FROM tracksys.transactions WHERE clubid='" + clubID + "' ORDER BY date DESC LIMIT 100;";
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
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
