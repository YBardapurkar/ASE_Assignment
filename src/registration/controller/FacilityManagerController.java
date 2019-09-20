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
		session.removeAttribute("MAR");
		session.removeAttribute("listMAR");
		session.removeAttribute("errorMsgs");
		
		User currentUser = (User) session.getAttribute("user");
		
		session.setAttribute("listRepairers", UserDAO.getUserByRole("Repairer"));

//		SHow MAR details
		if (request.getParameter("mar_id") != null) {
			int id = Integer.parseInt(request.getParameter("mar_id"));
			MAR mar = MARDAO.getMARByID(id);
			
			session.setAttribute("MAR", mar);
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_details.jsp").include(request, response);
			System.out.println(mar.getAssignedTo());
			if (mar.getAssignedTo() == null)
				request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
		}
//		Show All MAR
		else if (request.getParameter("mar_list") != null) {
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getAllMAR());
			session.setAttribute("listMAR", marList);
			
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table_fm.jsp").include(request, response);
		}
//		Show unassigned MAR
		else if (request.getParameter("unassigned_mar") != null) {
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getUnassignedMAR());
			session.setAttribute("listMAR", marList);
			
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table_fm.jsp").include(request, response);
		}
//		Show Facility Manager Homepage
		else {
			session.removeAttribute("MAR");
			session.removeAttribute("listMAR");
			
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/home_fm.jsp").include(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		AssignmentMessage assignmentMessage = new AssignmentMessage();
		
		if (action.equals("assignRepairer")) {
			int estimate = Integer.parseInt(request.getParameter("estimate"));
			int marId = Integer.parseInt(request.getParameter("mar_id"));
			String repairer = (String) request.getParameter("repairer");

			if (AssignmentDAO.getAssignmentCountByDay(repairer, new Date(System.currentTimeMillis())) < 5) { 
//				Less than 5, can assign
				AssignmentDAO.assignRepairer(repairer, estimate, marId);
				
			} else {
//				Cannot assign
				assignmentMessage.setErrorMessage("Cannot assign more than 5 MARs to this repairer.");
			}
			
//			save in session
			MAR mar = MARDAO.getMARByID(marId);
			session.setAttribute("MAR", mar);
			session.setAttribute("message", assignmentMessage);
			
//			redirect
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_details_user.jsp").include(request, response);
			if (mar.getAssignedTo() == null)
				request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
			
			session.removeAttribute("message");
		}
	}
}
