package tracksys.controller;

import tracksys.boundary.views.*;

public class ArenaManager {
	LoginView loginView;
	
	private ArenaManager()
	{
		this.loginView = new LoginView(this);
	}
}