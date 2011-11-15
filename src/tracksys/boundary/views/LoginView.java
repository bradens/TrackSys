package tracksys.boundary.views;

import tracksys.controller.ArenaManager;

public class LoginView {
	private static String password;
	public static String username;
	public ArenaManager manager;
	
	public LoginView(ArenaManager manager)
	{
		this.manager = manager;
	}
}