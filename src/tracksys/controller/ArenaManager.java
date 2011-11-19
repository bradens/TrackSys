package tracksys.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.Resources;
import tracksys.boundary.views.*;

public class ArenaManager {
	public enum Views {
		ROOT, LOGIN, HOME
	}
	private static ArenaManager ref;
	
	public LoginView loginView;
	public HomeView homeView;
	
	public boolean isAdmin(HttpServletRequest req)
	{
		// Once db is implemented, do a search on the cookie_uID to find out
		// the type of user. Then reimplement these two methods, use dummy vals for now
		
		Cookie c = ArenaManager.getCookie(Resources.COOKIE_USERNAME, req);
		if (c != null && c.getValue().equals("admin"))
		{
			return true;
		}
		return false;
	}
	public boolean isClub(HttpServletRequest req)
	{
		Cookie c = ArenaManager.getCookie(Resources.COOKIE_USERNAME, req);
		if (c != null && c.getValue().equals("babyseals"))
			return true;
		return false;
	}

	/**
	 * Retrieve the cookie object with a specified name.
	 * @param cookieName
	 * @param req
	 */
	public static Cookie getCookie(String cookieName, HttpServletRequest req)
	{
		Cookie[] cookies = req.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length;i++)
		{
			if (cookies[i].getName().equals(cookieName))
			{
				return cookies[i];
			}
		}
		return null;
	}
	
	/**
	 * Performs a request on the core view, used for redirects mainly.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public void doRoot(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (target.endsWith("/isLoggedIn"))
		{
			Cookie usr = getCookie(Resources.COOKIE_USERNAME, req);
			if (usr != null)
				writeResponse("true", resp);
			else
				writeResponse("false", resp);
		}
	}
	
	/**
	 * Singleton methods
	 */
	private ArenaManager()
	{
		loginView = new LoginView(this);
		homeView = new HomeView(this);
	}
	
	public static ArenaManager getInstance() 
	{
		if (ref == null)
			ref = new ArenaManager();
		return ref;
	}
	
	public static void writeResponse(String resp, HttpServletResponse r)
	{
		try {
			r.setStatus(HttpServletResponse.SC_OK);
			r.getWriter().write(resp);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}