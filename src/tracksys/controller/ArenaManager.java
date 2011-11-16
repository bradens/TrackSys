package tracksys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.boundary.views.*;

public class ArenaManager {
	public enum Views {
		Root, LoginView
	}
	
	private static ArenaManager ref;
	
	public static LoginView loginView;
	
	public static void getUserCookie(String cookieName, HttpServletRequest req)
	{
		
	}
	
	/**
	 * Performs some logic on the request and calls the specific
	 * operation in the login view.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public static void doLogin(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		loginView.Login(req, resp, target);
	}
	
	public static void doRoot(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		
	}
	
	private ArenaManager()
	{
		loginView = new LoginView(this);
	}
	
	public ArenaManager getInstance() 
	{
		if (ref == null)
			ref = new ArenaManager();
		return ref;
	}
}