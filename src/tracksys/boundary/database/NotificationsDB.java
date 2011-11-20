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
import tracksys.entity.Date;
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
				
				tempNot = new Notification(new Date(dateParts[2], dateParts[1], dateParts[0],
						timeParts[1], timeParts[0]), rs.getString("title"), rs.getString("message"));
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
}
