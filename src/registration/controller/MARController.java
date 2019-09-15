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
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");

		//		List companies
		if (action.equalsIgnoreCase("getAllMAR")) {
			List<MAR> marList = MARDAO.getAllMAR();
			session.setAttribute("MAR", marList);				
			getServletContext().getRequestDispatcher("/mar_table.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);

	}

	//Ajinkya

	private MAR getMarParam (HttpServletRequest request) {
		MAR mar = new MAR();
		mar.setFacilityName(request.getParameter("facilityname"));
		mar.setFacilityType(request.getParameter("facilitytype"));
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

		session.removeAttribute("mar");
		session.removeAttribute("errorMsgs");

		newMar = getMarParam(request);
		newMar.validateMar(action, newMar, userErrorMsgs);
		session.setAttribute("mar", newMar);	

		if (!userErrorMsgs.getErrorMsg().equals("")) {
			//			if error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/mar_form.jsp").include(request, response);
		}
		else {
			//			if no error messages
			MARDAO.insertmar(newMar);//Insert into database			
			newMar.setMessage("mar is created");
			request.getRequestDispatcher("/mar_form.jsp").include(request, response);

		}
	}


}
