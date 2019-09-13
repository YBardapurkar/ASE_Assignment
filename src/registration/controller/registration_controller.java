package registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registration.data.UserDAO;
import registration.model.*;


@WebServlet("/registration_controller")
public class registration_controller extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private void getuserParam (HttpServletRequest request, User r1) {
		r1.setuser(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"), request.getParameter("lastname"),  request.getParameter("role"), request.getParameter("utaid"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("street"), request.getParameter("city"),  request.getParameter("state"), request.getParameter("zipcode"));
	
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		open register page
		if (request.getParameter("register") != null) {
			getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
		}
		
//		open login page
		if (request.getParameter("login") != null) {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}
	
	private void getlogin(HttpServletRequest request, User r1)
	{
		r1.setLoginUser(request.getParameter("username"), request.getParameter("password"));
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url ="";
		HttpSession session = request.getSession();
		User r1 = new User();
		UserError CerrorMsgs = new UserError();
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("saveuser") ) 
		{
			getuserParam(request,r1);
			r1.validateuser(action,r1,CerrorMsgs);
			session.setAttribute("r1",r1);	
			
			if (!CerrorMsgs.getErrorMsg().equals("")) 
			{// if error messages
				getuserParam(request,r1);
				session.setAttribute("errorMsgs", CerrorMsgs);
				getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
			}
			else 
			{// if no error messages
				registration.data.UserDAO.insertuser(r1);
				url="/Registration.jsp";				
				r1.setMessage("data is inserted");
				getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
			}
		}
			
			if (request.getParameter("login") != null) 
			{
				
				getlogin(request,r1);
				
				r1.login_validateuser(action,r1,CerrorMsgs);
				
				session.setAttribute("r1",r1);	
				
				if (!CerrorMsgs.getErrorMsg().equals("")) 
				{// if error messages
				
					session.setAttribute("errorMsgs", CerrorMsgs);
					
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				else 
				{// if no error messages
					System.out.println("inside else part");
//					getServletContext().getRequestDispatcher("/registered.jsp").forward(request, response);
					response.setHeader("Location", "home");
				}
			
			
			}
			
			if (action.equalsIgnoreCase("logout")) {
				session.invalidate();
				response.setHeader("Location", "/registration_controller?login");
			}
			
		

}
}
