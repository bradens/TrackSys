/**
 * TODO remove all DB References..Everything needs to go through the manager first.
 */

package tracksys.boundary.views;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.Resources;
import tracksys.boundary.database.BookingsDB;
import tracksys.boundary.database.TracksDB;
import tracksys.controller.ArenaManager;
import tracksys.entity.Address;
import tracksys.entity.Booking;
import tracksys.entity.Club;
import tracksys.entity.Notification;
import tracksys.entity.Track;
import tracksys.entity.Transaction;
import tracksys.servletHandler.ServletHandler;

import com.google.gson.Gson;

public class HomeView {
	public ArenaManager manager;
	public HomeView() { }
	public HomeView(ArenaManager manager)
	{
		this.manager = manager;
	}
	
	/**
	 * Performs some logic on the request and calls the specific
	 * operation in the view.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public boolean handle(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (target.endsWith("homeLocation"))
		{
			this.directToHome(req, resp);
			return true;
		}
		else if (target.endsWith("getNotifications"))
		{
			return this.getNotifications(req, resp);
		}
		else if (target.endsWith("addNotification"))
		{
			if (manager.isAdmin(req))
				return this.addNotification(req, resp);
			else
				ServletHandler.writeErr("Not an admin", req, resp);
		}
		else if (target.endsWith("removeNotification"))
		{
			return this.removeNotification(req,resp);
		}
		else if (target.endsWith("getTransactions"))
		{
			return this.getTransactions(req, resp);
		}
		else if (target.endsWith("getRecentBookings"))
		{
			return this.getRecentBookings(req, resp);
		}
		else if (target.endsWith("submitBooking"))
		{
			return this.addBooking(req, resp);
		}
		else if (target.endsWith("cancelBooking"))
		{
			return this.cancelBooking(req, resp);
		}
		else if (target.endsWith("getFutureBookings"))
		{
			return this.getFutureBookings(req, resp);
		}
		else if (target.endsWith("getHistoricBookings"))
		{
			return this.getHistoricBookings(req, resp);
		}
		else if (target.endsWith("getDayBookings"))
		{
			return this.getDayBookings(req, resp);
		}
		else if (target.endsWith("getAllClubs"))
		{
			return this.getAllClubs(req, resp);
		}
		else if (target.endsWith("submitMaintenance"))
		{
			return this.addMaintenance(req, resp);
		}
		else if (target.endsWith("getAllTracks"))
		{
			return this.getTracks(req, resp);
		}
		else if (target.endsWith("flipMaint"))
		{
			return this.flipMaint(req, resp);
		}
		else if (target.endsWith("getCurrentClubProfile"))
		{
			return this.getCurrentClubProfile(req, resp);
		}
		else if (target.endsWith("submitPayment"))
		{
			return this.addPayment(req, resp);
		}
		else if (target.endsWith("billClub"))
		{
			return this.billClub(req, resp);
		}
		else if (target.endsWith("getTrackHistory"))
		{
			return this.getTrackHistory(req, resp);
		}
		else if (target.endsWith("getClubsByName"))
		{
			return this.getClubsByName(req, resp);
		}
		else if (target.endsWith("editClub"))
		{
			return this.editClub(req, resp);
		}
		return false;
	}
	
	
	/**
	 * Applies the current club's updates to their profile data.
	 * Calls the manager to access DB.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean editClub(HttpServletRequest req, HttpServletResponse resp)
	{
		Club currClub = manager.getCurrentLoginClub(req);		
		currClub.setName(req.getParameter("name"));
		currClub.setAddress(new Address(req.getParameter("street"), req.getParameter("city"), req.getParameter("province"), req.getParameter("phone"), req.getParameter("postal")));
		currClub.setEmail(req.getParameter("email"));
		String billing = req.getParameter("billing");
		
		int boolBilling;
		
		if(billing.equalsIgnoreCase("Electronic"))
			boolBilling = 1;
		else 
			boolBilling = 0;
		
		currClub.setElectronicBilling(boolBilling);
		
		if (currClub.getName().equalsIgnoreCase("") || currClub.getAddress().street.equalsIgnoreCase("") || currClub.getAddress().city.equalsIgnoreCase("") ||
				currClub.getAddress().postal.equalsIgnoreCase("") || currClub.getEmail().equalsIgnoreCase("") || currClub.getPhoneNumber().equalsIgnoreCase(""))
		{
			ServletHandler.writeResponse("false", resp);
			return false;
		}
		else
		{
			manager.editClub(currClub);
			ServletHandler.writeResponse("true", resp);
			return true;
		}
	}
	
	/**
	 * Adds a new transaction.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean addPayment(HttpServletRequest req, HttpServletResponse resp)
	{
		Date payDate    = new Date();
		String comment = req.getParameter("comment");
		String amount  = req.getParameter("amount");
		
		try 
		{
			// get information
			int clubID = manager.getClubIDFromCookie(req);
			Float payamount = new Float(amount);
			
			Transaction payment = new Transaction(clubID,payamount.floatValue(),payDate,comment);
			manager.addTransactions(payment);
			ServletHandler.writeResponse("true", resp);
		}
		catch (Exception e)
		{
			System.out.println("Error adding payment");
			ServletHandler.writeResponse("false", resp);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Get the last 50 bookings based on bookedDate.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean getRecentBookings(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Booking> bookings = manager.getRecentBookings();
		Gson g = new Gson();
		String s = g.toJson(bookings);
		resp.setContentType("application/json");
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Get the latest bookings for a given TrackID.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean getTrackHistory(HttpServletRequest req, HttpServletResponse resp)
	{
		String trackID = req.getParameter("track");
		Integer track = new Integer(trackID);
		
		List<Booking> bookings = manager.getTrackHistory(track.intValue());
		Gson g = new Gson();
		String s = g.toJson(bookings);
		resp.setContentType("application/json");
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Get the bookings for a certain day.  Writes back to be used for the calendar view.
	 * @param req
	 * @param resp
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean getDayBookings(HttpServletRequest req, HttpServletResponse resp)
	{
		String day = req.getParameter("date"); 
		try
		{
			Date dateStart = Resources.DATE_FORMAT.parse(day + " 00:00:00");
			Date dateEnd = Resources.DATE_FORMAT.parse(day + " 23:59:59");
			List<Booking> bookings = manager.getDayBookings(dateStart,dateEnd);
			Gson g = new Gson();
			String[][] dayBookings = new String[17][8];
			for(int i = 0, n = bookings.size(); i < n; i++) {
				Booking tempBook = bookings.get(i);
				int startHour = tempBook.getStartTime().getHours();
				int hoursBooked = tempBook.getEndTime().getHours()-startHour;
				for(int j = 0; j < hoursBooked; j++)
				{
					dayBookings[startHour+j-6][tempBook.getTrackID()-1] = tempBook.getClubName();
				}
				
		    }
			String s = g.toJson(dayBookings);
			resp.setContentType("application/json");
			ServletHandler.writeResponse(s, resp);
		}
		catch (Exception e)
		{
			System.out.println("Error creating booking date");
			ServletHandler.writeResponse("false", resp);
		}	
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the first 100 notifications.
	 * @param req
	 * @param resp
	 */
	public boolean getNotifications(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Notification> notifications = manager.getNotifications();
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(notifications);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the 8 tracks.
	 * @param req
	 * @param resp
	 */
	public boolean getTracks(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Track> tracks = manager.getTracks();
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(tracks);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the current club profile.
	 * @param req
	 * @param resp
	 */
	public boolean getCurrentClubProfile(HttpServletRequest req, HttpServletResponse resp)
	{
		Club currentClub = manager.getCurrentLoginClub(req);
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(currentClub);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the future bookings.
	 * @param req
	 * @param resp
	 */
	public boolean getFutureBookings(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Booking> notifications = manager.getFutureBookings(manager.getClubIDFromCookie(req));
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(notifications);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the future bookings.
	 * @param req
	 * @param resp
	 */
	public boolean getHistoricBookings(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Booking> notifications = manager.getHistoricBookings(manager.getClubIDFromCookie(req));
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(notifications);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Adds a notification.
	 * @param req
	 * @param resp
	 */
	public boolean addNotification(HttpServletRequest req, HttpServletResponse resp)
	{
		manager.addNotification(req.getParameter(Resources.NOTIFICATION_TITLE_PARAM), 
				req.getParameter(Resources.NOTIFICATION_MESSAGE_PARAM));
		return true;
	}
	
	/**
	 * Removes a notification
	 * @param req
	 * @param resp
	 */
	public boolean removeNotification(HttpServletRequest req, HttpServletResponse resp)
	{
		manager.removeNotification(Integer.parseInt(req.getParameter("id")));
		return true;
	}
	
	/**
	 * Adds a booking.
	 * @param req
	 * @param resp
	 */
	public boolean addBooking(HttpServletRequest req, HttpServletResponse resp)
	{
		String trackId = req.getParameter("track");
		String date = req.getParameter("date");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String comment = req.getParameter("comment");
		String recurring = req.getParameter("recurring");
		
		try 
		{
			Date startDate = Resources.DATE_FORMAT.parse(date + " " + start + ":00");
			Date endDate = Resources.DATE_FORMAT.parse(date + " " + end + ":00");
			Date stamp = new Date();
			Resources.DATE_FORMAT.format(stamp);
			
			//Random generator = new Random();
			//int track = generator.nextInt(8) + 1;
			
			Integer track = new Integer(trackId);
			int clubid = manager.getClubIDFromCookie(req);
			
			boolean recure = true;
			
			if(recurring.equalsIgnoreCase("true"))
			{
				int i = 4;
				Calendar c = Calendar.getInstance();
				while(i > 0)
				{
					Booking booking = new Booking(clubid, "", track, startDate, endDate, stamp, comment);
					if(!manager.addBooking(booking))
						recure = false;
					
					// Add to start date
					c.setTime(startDate);
					c.add(Calendar.DATE, 7);
					startDate = c.getTime();
					
					// Add to end date
					c.setTime(endDate);
					c.add(Calendar.DATE, 7);
					endDate = c.getTime();
					
					i--;
				}
				
				if(recure)
					ServletHandler.writeResponse("true", resp);
				else
					ServletHandler.writeResponse("false", resp);
			}
			else
			{
				Booking booking = new Booking(clubid, "", track, startDate, endDate, stamp, comment);
				if(manager.addBooking(booking))
					ServletHandler.writeResponse("true", resp);
				else
					ServletHandler.writeResponse("false", resp);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error creating booking date");
			ServletHandler.writeResponse("false", resp);
		}
		
		return true;
	}
	
	/**
	 * Cancels a booking.
	 * @param req
	 * @param resp
	 */
	public boolean cancelBooking(HttpServletRequest req, HttpServletResponse resp)
	{
		String ID = req.getParameter("id");
		
		BookingsDB db = BookingsDB.getInstance();
		db.cancelBooking(Integer.parseInt(ID));
		return true;
	}
	
	/**
	 * Flips a track maintenance setting.
	 * @param req
	 * @param resp
	 */
	public boolean flipMaint(HttpServletRequest req, HttpServletResponse resp)
	{
		String ID = req.getParameter("id");
		
		TracksDB db = TracksDB.getInstance();
		boolean flip = db.getTrackMaintenance(Integer.parseInt(ID));
		db.editTrack(Integer.parseInt(ID), flip);
		return true;
	}
	
	/** 
	 * Returns a JSON encoded response of the all past transactions.
	 * @param req
	 * @param resp
	 */
	public boolean getTransactions(HttpServletRequest req, HttpServletResponse resp)
	{
		int clubID = manager.getClubIDFromCookie(req);
		List<Transaction> transactions = manager.getTransactions(clubID);
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(transactions);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	/**
	 * Gets a JSON encoded response of all the clubs.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean getAllClubs(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Club> clubs = manager.getAllClubs();
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(clubs);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Get all the clubs that contain a certain substring, specified by <i>searchStr</i>
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean getClubsByName(HttpServletRequest req, HttpServletResponse resp)
	{
		List<Club> clubs = manager.getClubsByName(req.getParameter("searchStr"));
		Gson g = new Gson();
		resp.setContentType("application/json");
		String s = g.toJson(clubs);
		ServletHandler.writeResponse(s, resp);
		return true;
	}
	
	/**
	 * Redirects the request to the appropriate page, admin or club.
	 * @param req
	 * @param resp
	 */
	public void directToHome(HttpServletRequest req, HttpServletResponse resp)
	{	
		if (manager.isAdmin(req))
		{
			ServletHandler.writeResponse("/public/home/admin.html", resp);
		}
		else if (manager.isClub(req))
			ServletHandler.writeResponse("/public/home/club.html", resp);
		else
			ServletHandler.writeResponse("/public/login/", resp);
	}
	
	/**
	 * Add a maintenance booking.
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean addMaintenance(HttpServletRequest req, HttpServletResponse resp)
	{
		String track = req.getParameter("track");
		String date  = req.getParameter("date");
		String start = req.getParameter("start");
		String end   = req.getParameter("end");
		String comment = req.getParameter("comment");
		comment += "***MAINTENANCE***";
		
		try 
		{
			Date startDate 	= Resources.DATE_FORMAT.parse(date + " " + start + ":00");
			Date endDate 	= Resources.DATE_FORMAT.parse(date + " " + end + ":00");
			Date stamp 		= new Date();
			Resources.DATE_FORMAT.format(stamp);
			
			// get track number
			Integer trackID = new Integer(track);
			int clubID = manager.getClubIDFromCookie(req);
			
			Booking booking = new Booking(clubID, "", trackID, startDate, endDate, stamp, comment);
			
			List<Booking> conflictBookings = manager.addMaintenance(booking);
			
			// Return the list of conflict bookings
			Gson g = new Gson();
			resp.setContentType("application/json");
			String s = g.toJson(conflictBookings);
			ServletHandler.writeResponse(s, resp);
		}
		catch (Exception e)
		{
			System.out.println("Error creating booking Maintenance");
			ServletHandler.writeResponse("false", resp);
		}
		
		return true;
	}
	
	/**
	 * Bills a club specified by <i>club</i>
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean billClub(HttpServletRequest req, HttpServletResponse resp)
	{
		String name = req.getParameter("club");
		String value  = req.getParameter("fee");
		
		if(manager.billClub(name, Float.parseFloat(value)))
		{
			ServletHandler.writeResponse("true", resp);
			return true;
		}
		
		ServletHandler.writeResponse("false", resp);
		return true;
	}
}
