package tracksys.entity;

public class Track {
	public int trackID;
	private boolean isBookedForMaintenance;
	
	public Track(int trackID, boolean isBookedForMaintenance)
	{
		this.trackID = trackID;
		this.isBookedForMaintenance = isBookedForMaintenance;
	}
	
	public Track(int trackID)
	{
		this.trackID = trackID;
		isBookedForMaintenance = false;
	}
	
	public boolean isBookedForMaintenance()
	{
		return isBookedForMaintenance;
	}
	
	public void bookForMaintenance()
	{
		isBookedForMaintenance = true;
	}
	
	public void maintenanceComplete()
	{
		isBookedForMaintenance = false;
	}
}