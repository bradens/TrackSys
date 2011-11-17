package tracksys.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.boundary.views.*;

public class ArenaManager {
	public enum Views {
		Root, LoginView
	}
	
	private static ArenaManager ref;
	
	public LoginView loginView;
	
	/**
	 * Retrieve the cookie object with a specified name.
	 * @param cookieName
	 * @param req
	 */
	public static Cookie getUserCookie(String cookieName, HttpServletRequest req)
	{
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies)
		{
			if (c.getName() == cookieName)
			{
				return c;
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
		
	}
	
	/**
	 * Singleton methods
	 */
	private ArenaManager()
	{
		loginView = new LoginView(this);
	}
	
	public static ArenaManager getInstance() 
	{
		if (ref == null)
			ref = new ArenaManager();
		return ref;
	}
}