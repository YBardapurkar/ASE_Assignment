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
		
//		check logged in user role
		User currentUser = (User) session.getAttribute("user");
		System.out.println(currentUser.getRole());

				
//		Show List of All Users
		if (request.getParameter("listUsers") != null) {
			ArrayList<User> listUsers = UserDAO.listUsers();
			session.removeAttribute("USERS");
			
			session.setAttribute("USERS", listUsers);
			request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
			request.getRequestDispatcher("/list_users.jsp").include(request, response);
			
		}
//		Show Selected User Details
		else if (request.getParameter("listSpecificUser") != null) {
			session.removeAttribute("error");
			session.removeAttribute("USERS");
			
			User user = UserDAO.getUserByUsername(request.getParameter("listSpecificUser"));
			if (user != null) {
				session.setAttribute("USERS", user);
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/specific_user.jsp").include(request, response);
			}
			else { // determine if Submit button was clicked without selecting a user
				String error =  "User not found";
				user = new User();
				session.setAttribute("error",error);
				request.getRequestDispatcher("/menu_admin.jsp").include(request, response);
				request.getRequestDispatcher("/specific_user.jsp").include(request, response);
			}
		}
//		Open Search page
		else if (request.getParameter("search") != null) {
			session.removeAttribute("error");
			session.removeAttribute("USERS");
			
			request.getRequestDispatcher("menu_admin.jsp").include(request, response);
			request.getRequestDispatcher("search_user.jsp").include(request, response);
		}
//		Open Admin Home
		else {
			session.removeAttribute("error");
			session.removeAttribute("USERS");
			
			request.getRequestDispatcher("menu_admin.jsp").include(request, response);
			request.getRequestDispatcher("/home_admin.jsp").include(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");  //defining the action parameter
		HttpSession session = request.getSession();   //defining the session parameter
		int selectedUserIndex;
		//session.removeAttribute("admin");
		//session.removeAttribute("cerrorMsgs");
		session.removeAttribute("error");
		
		Admin admin = new Admin(); //creating object for class admin
		UserError UserErrors = new UserError();
		session.setAttribute("Admin", admin);
		//session.removeAttribute("errorMsgs");
		
		ArrayList<User> usersListInDB = new ArrayList<User>();
		
		if(action.equals("Submit"))  
		{
			getSearchParam(request,admin); 
			admin.validateSearchUser(action,admin, UserErrors);
			
			
			if (!UserErrors.getSearchError().equals("")) {
//				if error messages
				session.setAttribute("userErrors", UserErrors);
				request.getRequestDispatcher("/search_user.jsp").include(request, response);
			}
			
			else {
//				if no error messages
				usersListInDB = UserDAO.searchUsersByAdmin(admin.getUser(),admin.getFilter());	
				session.setAttribute("USERS", usersListInDB);
				if(usersListInDB.size() == 0)
				{
					session.setAttribute("userErrors", UserErrors);
					UserErrors.setSearchError("User does not exist");
					request.getRequestDispatcher("/search_user.jsp").include(request, response);
					
				}
				else
				{
					request.getRequestDispatcher("menu_admin.jsp").include(request, response);
					request.getRequestDispatcher("search_user.jsp").include(request, response);
					request.getRequestDispatcher("/list_users.jsp").include(request, response);
				}
				
			}
			
		}
		

		
		if(action.equals("listSpecificUser"))
		{
			User User = new User();
			if (request.getParameter("radioUser")!=null) {
				selectedUserIndex = Integer.parseInt(request.getParameter("radioUser")) - 1;
				usersListInDB = UserDAO.listUsers();
				User.setuser(usersListInDB.get(selectedUserIndex).getUsername(),
						usersListInDB.get(selectedUserIndex).getPassword(),
						usersListInDB.get(selectedUserIndex).getFirstname(),
						usersListInDB.get(selectedUserIndex).getLastname(),
						usersListInDB.get(selectedUserIndex).getRole(),
						usersListInDB.get(selectedUserIndex).getUtaId(),
						usersListInDB.get(selectedUserIndex).getPhone(),
						usersListInDB.get(selectedUserIndex).getEmail(),
						usersListInDB.get(selectedUserIndex).getStreet(),
						usersListInDB.get(selectedUserIndex).getCity(),
						usersListInDB.get(selectedUserIndex).getState(),
						usersListInDB.get(selectedUserIndex).getZipcode()
						);
				session.setAttribute("USERS", User);
				request.getRequestDispatcher("/specific_user.jsp").include(request, response);
			}
			else { // determine if Submit button was clicked without selecting a user
				if (request.getParameter("ListSelectedUserButton")!=null) {
					String error =  "Please select a User";
					
					session.setAttribute("error",error);
					request.getRequestDispatcher("/list_users.jsp").include(request, response);
					
					}
				
				}

			}
		
		
	}
}
