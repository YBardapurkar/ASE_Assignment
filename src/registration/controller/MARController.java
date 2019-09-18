package registration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.MARDAO;
import registration.data.UserDAO;
import registration.model.MAR;
import registration.model.User;
import registration.model.UserError;


//Ajinkya

import java.time.LocalDateTime;
import java.sql.Timestamp;

/* Author : Ajinkya Vadane 
 * Controller : MAR
 * Description : MAR controller is responsible for Controlling MAR Events
 * Date : 09-15-19
 * 
 * */



@WebServlet("/mar")
public class MARController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	LocalDateTime now = LocalDateTime.now();
	Timestamp sqlNow = Timestamp.valueOf(now); //For getting the current system datestamp
	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("MAR");
		session.removeAttribute("listMAR");
		session.removeAttribute("errorMsgs");

		
//		MAR details
		if (request.getParameter("mar_id") != null) {
			int id = Integer.parseInt(request.getParameter("mar_id"));
			MAR mar = MARDAO.getMARByID(id);
			
			session.setAttribute("MAR", mar);
			request.getRequestDispatcher("/menu_mar.jsp").include(request, response);
			request.getRequestDispatcher("/mar_details.jsp").include(request, response);
		}
//		List by assigned status
		else if (request.getParameter("assigned") != null) {
			boolean assigned = ("true".equals(request.getParameter("assigned")));
			List<MAR> marList = new ArrayList<MAR>();
			if (assigned) {
				marList.addAll(MARDAO.getAssignedMAR());
			} else {
				marList.addAll(MARDAO.getUnassignedMAR());
			}
			session.setAttribute("listMAR", marList);
			request.getRequestDispatcher("/menu_mar.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table.jsp").include(request, response);
		}
//		List by assigned Repairer
		else if (request.getParameter("assignedTo") != null) {
			String assignedTo = request.getParameter("assigned");
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getMARByAssignedRepairer(assignedTo));
			session.setAttribute("listMAR", marList);
			request.getRequestDispatcher("/menu_mar.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table.jsp").include(request, response);
		}
//		List MAR
		else {
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getAllMAR());
			session.setAttribute("listMAR", marList);
			request.getRequestDispatcher("/menu_mar.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table.jsp").include(request, response);
		}
	}


	//Ajinkya

	private MAR getMarParam (HttpServletRequest request) {
		MAR mar = new MAR();
		mar.setFacilityName(request.getParameter("facilityname"));
		mar.setDescription(request.getParameter("description"));
		mar.setReportedBy(request.getParameter("reportedby"));
		System.out.println(sqlNow);
		mar.setDate(sqlNow.toString()); // I am keeping date as string only
		mar.setUrgency(request.getParameter("urgency"));		
		return mar;
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		MAR newMar = new MAR();

		UserError userErrorMsgs = new UserError();

		session.removeAttribute("MAR");
		session.removeAttribute("allMAR");
		session.removeAttribute("errorMsgs");

		newMar = getMarParam(request);
		newMar.validateMar(action, newMar, userErrorMsgs);
		//session.setAttribute("MAR", newMar);	

		if (!userErrorMsgs.getErrorMsg().equals("")) {
			//			if error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/mar_form.jsp").include(request, response);
		}
		else {
			//			if no error messages
			MARDAO.insertmar(newMar);//Insert into database			
			newMar.setMessage("mar is created");
			session.setAttribute("MAR", newMar);	
			
			request.getRequestDispatcher("/menu_mar.jsp").include(request, response);
			request.getRequestDispatcher("/mar_form.jsp").include(request, response);

		}
	}


}