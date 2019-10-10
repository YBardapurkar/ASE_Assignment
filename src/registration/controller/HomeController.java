package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import registration.data.MARDAO;
import registration.data.UserDAO;
import registration.data.FacilityDAO;
import registration.model.MAR;
import registration.model.MARError;
import registration.model.User;
import registration.model.UserError;
import registration.util.DateUtils;
import registration.util.DropdownUtils;

@WebServlet("/home")
public class HomeController extends HttpServlet implements HttpSessionListener {

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
		session.removeAttribute("mar");				// single MAR object
		session.removeAttribute("list_mar");		// list of MAR objects
		session.removeAttribute("errorMsgs");		// single MAR message object
		session.removeAttribute("UPDATEUSER");     // profile update object
		
		session.setAttribute("current_role", "home");
    	
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {

//			list submitted mars
			if (request.getParameter("list_mar") != null) {
				ArrayList<MAR> marList = MARDAO.getMARSubmittedByUser(currentUser.getUsername());
				session.setAttribute("list_mar", marList);
				
				request.getRequestDispatcher("menu_student.jsp").include(request, response);
				request.getRequestDispatcher("mar_list.jsp").include(request, response);
			}
//			Show MAR details
			else if (request.getParameter("mar_id") != null) {
				int id = Integer.parseInt(request.getParameter("mar_id"));
				MAR mar = MARDAO.getMARByID(id);
				session.setAttribute("mar", mar);
				request.getRequestDispatcher("/menu_student.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details.jsp").include(request, response);
			}
//			add mar page
			else if (request.getParameter("add_mar") != null) {
				ArrayList<String> MARList = new ArrayList<>();
				MARList = FacilityDAO.getFacilityName();
				session.setAttribute("marList",MARList);
				
				request.getRequestDispatcher("menu_student.jsp").include(request, response);
				request.getRequestDispatcher("mar_create_form.jsp").include(request, response);
			}
			
//			Open profile
			else if (request.getParameter("profile") != null) {
				User user = UserDAO.getUserByUsername(currentUser.getUsername());
				session.setAttribute("UPDATEUSER", user);
				session.setAttribute("state_dropdown", DropdownUtils.getStateDropdown());
				
				request.getRequestDispatcher("/menu_student.jsp").include(request, response);
				request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
			}
			
//			homepage for student/faculty/staff
			else {
				request.getRequestDispatcher("menu_student.jsp").include(request, response);
				request.getRequestDispatcher("home_student.jsp").include(request, response);
			}
			
			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute("mar");				// single MAR object
		session.removeAttribute("list_mar");		// list of MAR objects
		session.removeAttribute("errorMsgs");		// single MAR message object
		session.removeAttribute("UPDATEUSER");      // profile update object
		
		String action = request.getParameter("action");
		session.setAttribute("current_role", "home");
		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {

			if (action.equals("save_mar")) {
				MAR newMar = new MAR();
	
				MARError marErrorMsgs = new MARError();
	
				session.removeAttribute("mar");
				session.removeAttribute("list_mar");
				session.removeAttribute("errorMsgs");
	
				newMar = getMarParam(request);
				newMar.setReportedBy(currentUser.getUsername());
				newMar.validateMar(action, newMar, marErrorMsgs);
	
				if (!marErrorMsgs.getDescriptionError().equals("")) {
					//			if error messages
					session.setAttribute("mar", newMar);
					session.setAttribute("errorMsgs", marErrorMsgs);
					
					request.getRequestDispatcher("/menu_student.jsp").include(request, response);
					request.getRequestDispatcher("/mar_create_form.jsp").include(request, response);
				}
				else {
					//			if no error messages
					MARDAO.insertmar(newMar);//Insert into database		
					session.setAttribute("mar", newMar);
					MARError errorMsgs = new MARError();
					errorMsgs.setMessage("MAR has been created!!");
					session.setAttribute("errorMsgs", errorMsgs);
					request.getRequestDispatcher("/menu_student.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details.jsp").include(request, response);
				}
			}
			
			else if(action.equals("update_profile")) {
				
				User updateuser = new User();
				UserError userErrorMsgs = new UserError();

				updateuser = getUpdateProfileParam(request);
				updateuser.validateUser(action, updateuser, userErrorMsgs);
				
				if (!userErrorMsgs.getErrorMsg().equals("")) {
 //					if error messages
					session.setAttribute("error", userErrorMsgs);

					request.getRequestDispatcher("/menu_student.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
				}
				else {
//					if no error messages

					//update database except role
					UserDAO.updateProfile(updateuser);
					updateuser.setMessage("Profile has been updated!!!!!!!!");
					session.setAttribute("UPDATEUSER", updateuser);
					request.getRequestDispatcher("/menu_student.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
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
	
	//Ajinkya
	private MAR getMarParam (HttpServletRequest request) {
		MAR mar = new MAR();
		mar.setFacilityName(request.getParameter("facility_name"));
		mar.setDescription(request.getParameter("description"));
		mar.setDate(DateUtils.nowTimeStamp());
		mar.setUrgency(request.getParameter("urgency"));		
		return mar;
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
		user.setState(request.getParameter("state"));
		user.setZipcode(request.getParameter("zipcode"));
		user.setRole(request.getParameter("role"));
		return user;
	}

}
