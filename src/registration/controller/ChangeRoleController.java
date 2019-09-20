package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import registration.data.UserDAO;
import registration.model.ChangeRole;
import registration.model.User;
import registration.model.UserError;


/**
 * Servlet implementation class ChangeRoleController
 */
@WebServlet("/changeRole")
public class ChangeRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
		request.getRequestDispatcher("/change_role.jsp").include(request, response);
	}
	
	private ChangeRole getChangeRoleParam (HttpServletRequest request) {
		
		ChangeRole changerole = new ChangeRole();
		
		changerole.setUsername(request.getParameter("username"));
		changerole.setRole(request.getParameter("role"));
				
		return changerole;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		User user =  (User) session.getAttribute("USERS");
		
		ChangeRole changerole = new ChangeRole();
		
		UserError userErrorMsgs = new UserError();
		
		String username = request.getParameter("username");
		String role = request.getParameter("role");
		
		session.removeAttribute("changerole");
		session.removeAttribute("errorMsgs");
		
		changerole = getChangeRoleParam(request);
		
		changerole.validateChangeRole(action, changerole, userErrorMsgs);
		
		if (!userErrorMsgs.getErrorMsg().equals("")) {
			//			if error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/change_role.jsp").include(request, response);
		}
		else {
			
			ArrayList<User> roleCheck = new ArrayList<User>();
			roleCheck = UserDAO.getUserByRole(role);
			System.out.println(role);
			System.out.println(roleCheck.size());
			
			if(role.equals("Facility Manager") && roleCheck.size() > 0)
			{
				changerole.setMessage("A Facility Manager alrerady exists in the system. Cannot update role for this user");
				session.setAttribute("USERS", user);
				session.setAttribute("changerole", changerole);
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/specific_role.jsp").include(request, response);
				
			}
			
			
			else
			{
			//			if no error messages
			UserDAO.updateDetails(username, role); //update database
			//MARDAO.insertmar(newMar);//Insert into database	
			changerole.setMessage("role is updated");
			
			//newMar.setMessage("mar is created");
			//session.setAttribute("MAR", newMar);	
			user.setRole(role);
			session.setAttribute("USERS", user);
			session.setAttribute("changerole", changerole);
			
			request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
			request.getRequestDispatcher("/specific_role.jsp").include(request, response);
			}
		}
		
	}
		
		
	}


