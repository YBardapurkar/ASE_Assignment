package registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.UserDAO;
import registration.model.User;
import registration.model.UserError;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role;
		String action=request.getParameter("action"), url="";
		System.out.println(action);
		HttpSession session = request.getSession();

		User user = new User();
		UserError userError = new UserError();
		session.setAttribute("errorMsgs",userError);
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		user = UserDAO.login(request.getParameter("username"), request.getParameter("password"));
		
		session.setAttribute("login", user);
		
		if (userError.getErrorMsg().equals("")) {
			role = user.getRole(); 
			session.removeAttribute("errorMsgs");
			
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
			if (role.equals("admin"))
			{
//				getServletContext().getRequestDispatcher("/home").forward(request, response);
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			}
			else if(role.equals("student") || role.equals("faculty") || role.equals("staff"))
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			}
			else if(role.equals("FacilityManager"))
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			}
			else if (role.equals("repairer"))
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			}
			
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			session.removeAttribute("login");
			session.removeAttribute("errorMsgs");
		}
	}

}
