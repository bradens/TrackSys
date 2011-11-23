package tracksys.boundary.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Notification;

public class NotificationsDB {
	private Connection conn = null;
	public NotificationsDB() {
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
			System.out.println("Could not close notifications connection");
		}
	}
	
	public boolean addNotification(String title, String message, String timestamp)
	{
		String insert = "INSERT INTO tracksys.notifications VALUES(default, ?, ?, ?)";
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(insert);
			ps.setString(1, title);
			ps.setString(2, message);
			ps.setString(3, timestamp);
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get the first 100 notifications.
	 * @return
	 */
	public List<Notification> getNotifications()
	{
		List<Notification> notifications = new ArrayList<Notification>();
		String query = "SELECT * FROM tracksys.notifications ORDER BY date DESC LIMIT 100;";
		
		try
		{
			Notification tempNot;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				tempNot = new Notification(Resources.DATE_FORMAT.parse(rs.getString("date")), rs.getString("title"), rs.getString("message"), rs.getInt("id"));
				notifications.add(tempNot);
			}
			return notifications;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean removeNotification(int notID)
	{
		String remove = "DELETE FROM tracksys.notifications WHERE id=?";
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(remove);
			ps.setInt(1, notID);
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
