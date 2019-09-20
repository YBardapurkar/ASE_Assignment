package registration.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.model.User;
import registration.model.Admin;
import registration.model.ChangeRole;
import registration.data.UserDAO;
import registration.model.UserError;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void getSearchParam(HttpServletRequest request, Admin admin)
	{
		admin.setSearchParam(request.getParameter("searchUser"),request.getParameter("usersearchFilter"));  //setting search parameters
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();   //defining the session parameter
		User currentUser = (User) session.getAttribute("user");
		
		session.removeAttribute("error");
		session.removeAttribute("USERS");
		session.removeAttribute("change_role");
		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {
			
//			Show List of All Users
			if (request.getParameter("list_users") != null) {
				ArrayList<User> listUsers = UserDAO.listUsers();
				
				session.setAttribute("USERS", listUsers);
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/list_users.jsp").include(request, response);
				
			}
			
//			Show Selected User Details
			else if (request.getParameter("user_details") != null) {
				
				User user = UserDAO.getUserByUsername(request.getParameter("user_details"));
				if (user != null) {
					session.setAttribute("USERS", user);
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_details.jsp").include(request, response);
					request.getRequestDispatcher("/user_role_form.jsp").include(request, response);
				}
				else { // determine if Submit button was clicked without selecting a user
					String error =  "User not found";
					user = new User();
					session.setAttribute("error",error);
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_details.jsp").include(request, response);
					
				}
			}
			
//			Open Search page
			else if (request.getParameter("search") != null) {
				
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
			}
			
//			Open Admin Home
			else {
				
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/home_admin.jsp").include(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");  //defining the action parameter
		HttpSession session = request.getSession();   //defining the session parameter
		User currentUser = (User) session.getAttribute("user");
		
		session.removeAttribute("error");
		session.removeAttribute("USERS");
		session.removeAttribute("change_role");
		
//		user not logged in
		if (currentUser == null) {
			response.sendRedirect("login");
		}
//		logged in
		else {
			
//			search user
			if(action.equals("search_user")) {
				Admin admin = new Admin();
				UserError UserErrors = new UserError();
				
				getSearchParam(request,admin); 
				admin.validateSearchUser(action,admin, UserErrors);
				
				session.setAttribute("Admin", admin);
				
				ArrayList<User> usersListInDB = new ArrayList<User>();
				
				if (!UserErrors.getSearchError().equals("")) {
//					set error messages
					session.setAttribute("userErrors", UserErrors);
	
//					set jsp pages
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
				}
				
				else {
//					if no error messages
//					TODO replace the dao method
					usersListInDB = UserDAO.searchUsersByAdmin(admin.getUser(),admin.getFilter());	
					session.setAttribute("USERS", usersListInDB);
					
//					if no results
					if(usersListInDB.size() == 0) {
						
//						set session
						UserErrors.setSearchError("No Users found");
						session.setAttribute("userErrors", UserErrors);
	
//						set jsp pages
						request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
						request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
						
					} 
//					if results
					else {
						
//						set jsp pages
						request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
						request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
						request.getRequestDispatcher("/list_users.jsp").include(request, response);
					}
				}
			}
			
//			Change role
			else if (action.equals("change_role")){
				ChangeRole changeRole = new ChangeRole();
				UserError userErrorMsgs = new UserError();
				
				String username, newRole;
				User user;
				
				session.removeAttribute("errorMsgs");
				
				changeRole = getChangeRoleParam(request);
				changeRole.validateChangeRole(action, changeRole, userErrorMsgs);
				
				if (!userErrorMsgs.getErrorMsg().equals("")) {
//					if error messages
					session.setAttribute("errorMsgs", userErrorMsgs);

					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_details.jsp").include(request, response);
					request.getRequestDispatcher("/user_role_form.jsp").include(request, response);
				} else {
//					if no error messages
					
					username = changeRole.getUsername();
					newRole = changeRole.getRole();
					user = UserDAO.getUserByUsername(username);
					
//					check if facility manager exists
					if(newRole.equals("Facility Manager") & !UserDAO.getUserByRole("Facility Manager").isEmpty()) {
						changeRole.setMessage("A Facility Manager already exists in the system.");
						session.setAttribute("USERS", user);
						session.setAttribute("changerole", changeRole);
						
						request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
						request.getRequestDispatcher("/user_details.jsp").include(request, response);
						request.getRequestDispatcher("/user_role_form.jsp").include(request, response);
					} 
//					update role
					else {
						UserDAO.updateDetails(username, newRole); //update database
						changeRole.setMessage("role is updated");
						user.setRole(newRole);
						
						session.setAttribute("USERS", user);
						session.setAttribute("change_role", changeRole);
						
						request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
						request.getRequestDispatcher("/user_details.jsp").include(request, response);
						request.getRequestDispatcher("/user_role_form.jsp").include(request, response);
					}
				}
			}
		}
	}
	
	private ChangeRole getChangeRoleParam (HttpServletRequest request) {
		ChangeRole changerole = new ChangeRole();
		
		changerole.setUsername(request.getParameter("username"));
		changerole.setRole(request.getParameter("role"));
				
		return changerole;
	}
}
