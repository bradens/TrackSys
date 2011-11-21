package tracksys.boundary.views;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.Resources;
import tracksys.controller.ArenaManager;
import tracksys.entity.Booking;
import tracksys.entity.Notification;
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
	 * operation in the login view.
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
		else if (target.endsWith("getTransactions"))
		{
			return this.getTransactions(req, resp);
		}
		else if (target.endsWith("getRecentBookings"))
		{
			if (manager.isAdmin(req))
				return this.getRecentBookings(req, resp);
			else
				ServletHandler.writeErr("Not an admin", req, resp);
		}
		else if (target.endsWith("submitBooking"))
		{
			return this.addBooking(req, resp);
		}
		return false;
	}
	
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
	 * Adds a booking.
	 * @param req
	 * @param resp
	 */
	public boolean addBooking(HttpServletRequest req, HttpServletResponse resp)
	{
		String date = req.getParameter("date");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String comment = req.getParameter("comment");
		
		try 
		{
			Date startDate = Resources.DATE_FORMAT.parse(date + " " + start + ":00");
			Date endDate = Resources.DATE_FORMAT.parse(date + " " + end + ":00");
			Date stamp = new Date();
			Resources.DATE_FORMAT.format(stamp);
			
			Random generator = new Random();
			int track = generator.nextInt(8) + 1;
			int clubid = manager.getClubIDFromCookie(req);
			
			Booking booking = new Booking(clubid, "", track, startDate, endDate, stamp, comment);
			manager.addBooking(booking);
			ServletHandler.writeResponse("true", resp);
		}
		catch (Exception e)
		{
			System.out.println("Error creating booking date");
			ServletHandler.writeResponse("false", resp);
		}
		
		return true;
	}
	
	/* Returns a JSON encoded response of the all past transactions.
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
	
	public void directToHome(HttpServletRequest req, HttpServletResponse resp)
	{	
		if (manager.isAdmin(req))
		{
			ServletHandler.writeResponse("/home/admin.html", resp);
		}
		else if (manager.isClub(req))
			ServletHandler.writeResponse("/home/club.html", resp);
		else
			ServletHandler.writeResponse("/login/", resp);
	}
}
