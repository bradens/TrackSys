package tracksys;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHttpContext;


public class TrackSysApplication {
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
        server.addListener(":1234");        
		
        // JAVA SERVLET
        ServletHttpContext context = (ServletHttpContext) server.getContext("/servlet");
		context.addServlet("/", "tracksys.servletHandler.ServletHandler");		
		
		// STATIC RESOURCES
		ServletHttpContext context1 = (ServletHttpContext) server.getContext("/");
		
		String dir = System.getProperty("user.dir");
		System.out.println(
		 "================================================================================\n" + 
		 "TrackSys System Running serving static files from directory\n" + dir +
		 "\n================================================================================"
		 );
		context1.setResourceBase(dir);
		context1.addServlet("/", "org.mortbay.jetty.servlet.Default");
        server.start();
        server.join();
  	}
}
