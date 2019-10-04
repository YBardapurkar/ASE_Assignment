package registration.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import registration.model.User;
import registration.model.UserSearch;
import registration.model.ChangeRole;
import registration.data.UserDAO;
import registration.model.UserError;

@WebListener
@WebServlet("/admin")
public class AdminController extends HttpServlet implements HttpSessionListener {

	private static final long serialVersionUID = 1L;
	HttpSession session;
	User currentUser;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();   //defining the session parameter
		if (session.getAttribute("current_user") != null)
			currentUser = (User) session.getAttribute("current_user");
		
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session.setAttribute("current_role", "admin");
		
		session.removeAttribute("error");
		session.removeAttribute("USERS");
		session.removeAttribute("change_role");
		session.removeAttribute("user_search");
		session.removeAttribute("UPDATEUSER");
		
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
			
			else if (request.getParameter("admin_profile") != null) {
				
				User user = UserDAO.getUserByUsername(request.getParameter("admin_profile"));
				if (user != null) {
					session.setAttribute("UPDATEUSER", user);
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/studentprofile_updatedetails.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_student.jsp").include(request, response);
				}
				
				else { // determine if Submit button was clicked without selecting a user
					String error =  "User not found";
					user = new User();
					session.setAttribute("error",error);
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/studentprofile_updatedetails.jsp").include(request, response);
					
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
			
			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");  //defining the action parameter
		session.setAttribute("current_role", "admin");
		
		session.removeAttribute("error");
		session.removeAttribute("USERS");
		session.removeAttribute("change_role");
		session.removeAttribute("user_search");
		session.removeAttribute("UPDATEUSER");
		
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

			else if(action.equals("update_profile")) {
				
				User user = new User();
				User updateuser = new User();
				UserError userErrorMsgs = new UserError();
				
				String username, password, firstname, lastname, utaid, phone, email, address, city, state, zipcode;
				
				//System.out.println("I am here");
				
				
				updateuser = getUpdateProfileParam(request);
				updateuser.validateUser(action, updateuser, userErrorMsgs);
				
				if (!userErrorMsgs.getErrorMsg().equals("")) {
 //					if error messages
					session.setAttribute("errorMsgs", userErrorMsgs);

					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_student.jsp").include(request, response);
				}
				else {
//					if no error messages
					username = updateuser.getUsername();
					password = updateuser.getPassword();
					firstname = updateuser.getFirstname();
					lastname = updateuser.getLastname();
					utaid = updateuser.getUtaId();
					phone = updateuser.getPhone();
					email = updateuser.getEmail();
					address = updateuser.getStreet();
					city = updateuser.getCity();
					//state = updateuser.getState();
					zipcode = updateuser.getZipcode();
					
					//update database except role
					UserDAO.updateProfile(username, password, firstname, lastname, utaid, phone, email, address, city, zipcode); 
					updateuser.setMessage("Student Profile is updated");
					updateuser.setUsername(username);
					updateuser.setPassword(password);
					updateuser.setFirstname(firstname);
					updateuser.setLastname(lastname);
					updateuser.setUtaId(utaid);
					updateuser.setPhone(phone);
					updateuser.setEmail(email);
					updateuser.setStreet(address);
					updateuser.setCity(city);
					//updateuser.setState(state);
					updateuser.setZipcode(zipcode);
					
					//session.setAttribute("USERS", user);
					session.setAttribute("UPDATEUSER", updateuser);
					
					request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("/studentprofile_updatedetails.jsp").include(request, response);
					//request.getRequestDispatcher("/update_profile_student.jsp").include(request, response);
				}
				
			}
			
			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		currentUser = null;
		HttpSessionListener.super.sessionDestroyed(se);
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
	
	private User getUpdateProfileParam(HttpServletRequest request) {
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setUtaId(request.getParameter("utaid"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		//user.setState(request.getParameter("state"));
		user.setZipcode(request.getParameter("zipcode"));
		
		return user;
	}
}
