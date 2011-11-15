package tracksys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.boundary.views.*;

public class ArenaManager {
	private static ArenaManager ref;
	
	public static LoginView loginView;
	
	public static void Login(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		loginView.Login(req, resp, target);
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