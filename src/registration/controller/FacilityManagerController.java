package registration.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.AssignmentDAO;
import registration.data.MARDAO;
import registration.data.UserDAO;
import registration.model.AssignmentMessage;
import registration.model.MAR;
import registration.model.User;

@WebServlet("/facility_manager")
public class FacilityManagerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("mar");				// single MAR object
		session.removeAttribute("list_mar");		// list of MAR objects
		session.removeAttribute("errorMsgs");		// single MAR message
		session.removeAttribute("list_repairers");	// list of repairer users
		
		User currentUser = (User) session.getAttribute("current_user");
		session.setAttribute("current_role", "facility_manager");
		session.setAttribute("list_repairers", UserDAO.getUsersByRole("Repairer"));
		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {

//			SHow MAR details
			if (request.getParameter("mar_id") != null) {
				int id = Integer.parseInt(request.getParameter("mar_id"));
				MAR mar = MARDAO.getMARByID(id);
				session.setAttribute("mar", mar);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				if (mar.getAssignedTo() == null)
					request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
			}
//			Show All MAR
			else if (request.getParameter("mar_list") != null) {
				List<MAR> marList = new ArrayList<MAR>();
				marList.addAll(MARDAO.getAllMAR());
				session.setAttribute("list_mar", marList);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}
//			Show unassigned MAR
			else if (request.getParameter("unassigned_mar") != null) {
				List<MAR> marList = new ArrayList<MAR>();
				marList.addAll(MARDAO.getUnassignedMAR());
				session.setAttribute("list_mar", marList);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}
//			Show Facility Manager Homepage
			else {
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/home_fm.jsp").include(request, response);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("mar");				// single MAR object
		session.removeAttribute("list_mar");		// list of MAR objects
		session.removeAttribute("errorMsgs");		// single MAR message
		session.removeAttribute("list_repairers");	// list of repairer users
		
		String action = request.getParameter("action");
		
		User currentUser = (User) session.getAttribute("current_user");
		session.setAttribute("current_role", "facility_manager");
		AssignmentMessage assignmentMessage = new AssignmentMessage();
		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {
			
//			Assign a repairer to a mar
			if (action.equals("assign_repairer")) {
				int estimate = Integer.parseInt(request.getParameter("estimate"));
				int marId = Integer.parseInt(request.getParameter("mar_id"));
				String repairer = (String) request.getParameter("repairer");
	
				if (AssignmentDAO.getAssignmentCountByDay(repairer, new Date(System.currentTimeMillis())) >= 5) { 
//					More than 5 in a day, cannot assign
					assignmentMessage.setErrorMessage("Cannot assign more than 5 MARs to this repairer.");
				} else {
//					can assign
					AssignmentDAO.assignRepairer(repairer, estimate, marId);
				}
				
//				save in session
				MAR mar = MARDAO.getMARByID(marId);
				session.setAttribute("mar", mar);
				session.setAttribute("message", assignmentMessage);
				
//				redirect
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				if (mar.getAssignedTo() == null)
					request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
				
				session.removeAttribute("message");
			}
		}
	}
}
