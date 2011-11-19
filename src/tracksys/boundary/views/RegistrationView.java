package tracksys.boundary.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.controller.ArenaManager;
import tracksys.entity.*;

public class RegistrationView {
	public int activeClub; 
	public ArenaManager manager;
	
	public RegistrationView(ArenaManager manager)
	{
		this.manager = manager;
	}
	
	/**
	 * Performs some logic on the request and calls the specific
	 * operation in the registration view.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public boolean handle(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (target.endsWith("submitregistration"))
		{
			boolean success = this.submitRegistration(req, resp);
			return success;
		}
		return false;
	}
	
	public boolean submitRegistration(HttpServletRequest req, HttpServletResponse resp)
	{
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String province = req.getParameter("province");
		String postal = req.getParameter("postal");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		if (name == null || passwd == null || street == null || city == null || province == null ||
				postal == null || email == null || phone == null)
			return false;
		
		else
		{
			Address address = new Address(street, city, province, "", postal);
			Club club = new Club(name, passwd, address, email, phone, false, false);
			manager.AddNewClub(club);
			ArenaManager.writeResponse("true", resp);
			manager.activeClub = -1; // temporary manager id.
			return true;
		}
	}
}