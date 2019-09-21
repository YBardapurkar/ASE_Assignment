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
import registration.model.UserSearch;
import registration.model.ChangeRole;
import registration.data.UserDAO;
import registration.model.UserError;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();   //defining the session parameter
		User currentUser = (User) session.getAttribute("user");
		
		session.removeAttribute("error");
		session.removeAttribute("USERS");
		session.removeAttribute("change_role");
		session.removeAttribute("user_search");
		
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
				request.getRequestDispatcher("/users_list.jsp").include(request, response);
				
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
		session.removeAttribute("user_search");
		
//		user not logged in
		if (currentUser == null) {
			response.sendRedirect("login");
		}
//		logged in
		else {
			
//			search user
			if(action.equals("search_user")) {
				UserSearch userSearch = getSearchParam(request); 
				UserError UserErrors = new UserError();
				ArrayList<User> listUsers = new ArrayList<User>();
				
				userSearch.validateSearchUser(action, userSearch, UserErrors);
				
				if (!UserErrors.getSearchError().equals("")) {
//					set error messages
					session.setAttribute("error", UserErrors);
	
//					set jsp pages
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
				}
				
				else {
//					if no error messages
					
//					search by username
					if(userSearch.getUserSearchFilter().equals("1")) {
						listUsers.addAll(UserDAO.searchUsersByUsername(userSearch.getUserSearchText()));
						session.setAttribute("user_search", userSearch);
					}
//					search by role
					else if(userSearch.getUserSearchFilter().equals("2")) {
						listUsers.addAll(UserDAO.searchUsersByRole(userSearch.getUserSearchText()));
						session.setAttribute("user_search", userSearch);
					}
//					show all
					else {
						listUsers.addAll(UserDAO.listUsers());
					}
						
					session.setAttribute("USERS", listUsers);
					
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/user_search_form.jsp").include(request, response);
					if(listUsers.isEmpty()) {
						request.getRequestDispatcher("/users_list_empty.jsp").include(request, response);
					} else {
						request.getRequestDispatcher("/users_list.jsp").include(request, response);
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
					if(newRole.equals("Facility Manager") & !UserDAO.getUsersByRole("Facility Manager").isEmpty()) {
						changeRole.setMessage("A Facility Manager already exists in the system.");
						session.setAttribute("USERS", user);
						session.setAttribute("change_role", changeRole);
						
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
	
	public UserSearch getSearchParam(HttpServletRequest request){
		UserSearch userSearch = new UserSearch();
		
		userSearch.setUserSearchText(request.getParameter("search_text"));
		userSearch.setUserSearchFilter(request.getParameter("search_filter"));

		return userSearch; 
	}
	
	private ChangeRole getChangeRoleParam (HttpServletRequest request) {
		ChangeRole changerole = new ChangeRole();
		
		changerole.setUsername(request.getParameter("username"));
		changerole.setRole(request.getParameter("role"));
				
		return changerole;
	}
}
