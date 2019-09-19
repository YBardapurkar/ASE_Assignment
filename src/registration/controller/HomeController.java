package registration.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.MARDAO;
import registration.model.MAR;
import registration.model.User;
import registration.model.UserError;

@WebServlet("/home")
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		
//		redirect to login if user not found
		if (currentUser == null) {
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "login");
			return;
		}
		
//		list submitted mars
		if (request.getParameter("list_mar") != null) {
			ArrayList<MAR> marList = MARDAO.getMARSubmittedByUser(currentUser.getUsername());
			session.setAttribute("listMAR", marList);
			
			request.getRequestDispatcher("menu_student.jsp").include(request, response);
			request.getRequestDispatcher("mar_table.jsp").include(request, response);
		}
//		add mar page
		else if (request.getParameter("add_mar") != null) {
			request.getRequestDispatcher("menu_student.jsp").include(request, response);
			request.getRequestDispatcher("mar_form.jsp").include(request, response);
		}
		
//		homepage for student/faculty/staff
		else {
			request.getRequestDispatcher("menu_student.jsp").include(request, response);
			request.getRequestDispatcher("home_student.jsp").include(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");

		if (action.equals("save_mar")) {
			MAR newMar = new MAR();

			UserError userErrorMsgs = new UserError();

			session.removeAttribute("MAR");
			session.removeAttribute("listMAR");
			session.removeAttribute("errorMsgs");

			newMar = getMarParam(request);
			newMar.setReportedBy(currentUser.getUsername());
			newMar.validateMar(action, newMar, userErrorMsgs);
			//session.setAttribute("MAR", newMar);	

			if (!userErrorMsgs.getErrorMsg().equals("")) {
				//			if error messages
				session.setAttribute("errorMsgs", userErrorMsgs);
				
				request.getRequestDispatcher("/menu_student.jsp").include(request, response);
				request.getRequestDispatcher("/mar_form.jsp").include(request, response);
			}
			else {
				//			if no error messages
				MARDAO.insertmar(newMar);//Insert into database			
				newMar.setMessage("mar is created");
				session.setAttribute("MAR", newMar);	
				
				request.getRequestDispatcher("/menu_student.jsp").include(request, response);
				request.getRequestDispatcher("/mar_form.jsp").include(request, response);

			}
		}
	}
	
	//Ajinkya
	private MAR getMarParam (HttpServletRequest request) {
		MAR mar = new MAR();
		mar.setFacilityName(request.getParameter("facilityname"));
		mar.setDescription(request.getParameter("description"));
		mar.setReportedBy(request.getParameter("reportedby"));
		LocalDateTime now = LocalDateTime.now();
		Timestamp sqlNow = Timestamp.valueOf(now);
		System.out.println(sqlNow);
		mar.setDate(sqlNow.toString());
		mar.setUrgency(request.getParameter("urgency"));		
		return mar;
	}
}
