package tracksys.boundary.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

import tracksys.Resources;
import tracksys.boundary.database.BookingsDB;
import tracksys.boundary.database.NotificationsDB;
import tracksys.boundary.database.TransactionsDB;
import tracksys.controller.ArenaManager;
import tracksys.entity.Booking;
import tracksys.entity.Notification;
import tracksys.entity.Transaction;
import tracksys.servletHandler.ServletHandler;

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
		return false;
	}
	
	public boolean getRecentBookings(HttpServletRequest req, HttpServletResponse resp)
	{
		BookingsDB bdb = new BookingsDB();
		List<Booking> bookings = bdb.getRecentBookings();
		Gson g = new Gson();
		String s = g.toJson(bookings);
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
		NotificationsDB ndb = new NotificationsDB();
		List<Notification> notifications = ndb.getNotifications();
		Gson g = new Gson();
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
		NotificationsDB ndb = new NotificationsDB();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		ndb.addNotification(req.getParameter(Resources.NOTIFICATION_TITLE_PARAM), 
				req.getParameter(Resources.NOTIFICATION_MESSAGE_PARAM), dateFormat.format(date));
		return true;
	}
	
	/**
	 * Returns a JSON encoded response of the all past transactions.
	 * @param req
	 * @param resp
	 */
	public boolean getTransactions(HttpServletRequest req, HttpServletResponse resp)
	{
		TransactionsDB tdb = new TransactionsDB();
		List<Transaction> transactions = tdb.getTransactions();
		Gson g = new Gson();
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
