package tracksys;


import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpListener;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.ServletHttpContext;
import org.mortbay.xml.XmlConfiguration;

import tracksys.servletHandler.ServletHandler;


public class TrackSysApplication {
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
        server.addListener(":1234");        
		ServletHttpContext context = (ServletHttpContext) server.getContext("/");
		context.addServlet("/", "tracksys.servletHandler.ServletHandler");
        server.start();
	}
}
