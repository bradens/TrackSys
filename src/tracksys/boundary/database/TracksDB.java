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
import tracksys.entity.Track;

public class TracksDB {
	private Connection conn = null;
	public TracksDB() {
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
	
	public boolean editTrack(int trackID, boolean isMaintained)
	{
		String insert = "INSERT INTO tracksys.notifications VALUES(default, ?, ?, ?)";
		PreparedStatement ps = null;
		try{
			/*ps = conn.prepareStatement(insert);
			ps.setString(1, title);
			ps.setString(2, message);
			ps.setString(3, timestamp);
			ps.executeUpdate();*/
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get all tracks.
	 * @return
	 */
	public List<Track> getTracks()
	{
		List<Track> tracks = new ArrayList<Track>();
		String query = "SELECT * FROM tracksys.tracks";
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				Track tempTrack = new Track(rs.getInt("trackid"), rs.getBoolean("isMaintenance"));
				tracks.add(tempTrack);
			}
			return tracks;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
