package tracksys;

import tracksys.controller.*;
import org.eclipse.jetty.server.Server;

public class TrackSysApplication {
	public static void main(String[] args)
	{
		System.out.println("Starting Program");
		Server server = new Server(8080);
        server.setHandler(new ArenaManager());
 
        try {
	        server.start();
	        server.join();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
}
