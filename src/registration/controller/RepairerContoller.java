package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.TODO;

import registration.data.MARDAO;
import registration.data.ReservationDAO;
import registration.model.*;
import registration.util.DateUtils;

@WebServlet("/repairer")
public class RepairerContoller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.removeAttribute("list_mar");		// list of MAR objects
    	session.removeAttribute("mar");				// list of MAR objects
    	
    	User currentUser = (User) session.getAttribute("current_user");
    	session.setAttribute("current_role", "repairer");
    	
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {
    	
//	    	Display list of assigned MAR to repairer
			if (request.getParameter("my_mars") != null) {
				String username = currentUser.getUsername();
				
				ArrayList<MAR> listMAR = new ArrayList<MAR>();
				listMAR = MARDAO.getMARByAssignedRepairer(username);
				session.setAttribute("list_mar", listMAR);
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}
			
//			SHow MAR details
			else if (request.getParameter("mar_id") != null) {
				int id = Integer.parseInt(request.getParameter("mar_id"));
				MAR mar = MARDAO.getMARByID(id);
				session.setAttribute("mar", mar);
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				if (mar.getAssignedTo().equals(currentUser.getUsername())) {
					request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
				}
			}
			
//			Show Repairer Homepage
			else {
		    	request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/home_repairer.jsp").include(request, response);
			}
		}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.removeAttribute("list_mar");		// list of MAR objects
    	session.removeAttribute("mar");
    	session.removeAttribute("reservation");

    	User currentUser = (User) session.getAttribute("current_user");
    	session.setAttribute("current_role", "repairer");

    	String action = request.getParameter("action");

    	Reservation newReservation = new Reservation();
    	ReservationMessage reservationMessage = new ReservationMessage();

    	newReservation = getReservationParam(request);
    	newReservation.validateReservation(action,reservationMessage);

    
    	//		user not logged in
    	if (currentUser == null) {

    		response.sendRedirect("login");
    	}
    	//		logged in
    	else {

    		//			reserve facility
    		if (action.equals("reserve_facility")) {
    			//				TODO this method
    			
    			if (!reservationMessage.getErrorMessage().equals("")) {
    	    		//			if error messages
    	    		session.setAttribute("newReservation", newReservation);	
    	    		session.setAttribute("errorMsgs", reservationMessage);
    	    		request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
    	    		//request.getRequestDispatcher("/menu_login.jsp").include(request, response);
    	    		//request.getRequestDispatcher("/register.jsp").include(request, response);
    	    	}
    	    	else {
    	    		//TODO : Create Dao Layer Ajinkya
    	    		ReservationDAO.insertReservation(newReservation);
    	    		session.setAttribute("reservation", newReservation);	
    	    		session.setAttribute("errorMsgs", reservationMessage);

    	    		if (request.getParameter("my_mars") != null) {
    	    			String username = currentUser.getUsername();

    	    			ArrayList<MAR> listMAR = new ArrayList<MAR>();
    	    			listMAR = MARDAO.getMARByAssignedRepairer(username);
    	    			session.setAttribute("list_mar", listMAR);

    	    			int id = Integer.parseInt(request.getParameter("mar_id"));
    	    			MAR mar = MARDAO.getMARByID(id);
    	    			session.setAttribute("mar", mar);

    	    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    	    			request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
    	    			if (mar.getAssignedTo().equals(currentUser.getUsername())) {
    	    				request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
    	    			}

    	    			//    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    	    			//    			request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
    	    		}

    	    	}


    		}
    	}
    }
    
    public Reservation getReservationParam(HttpServletRequest request) {
    	Reservation reservation = new Reservation();
		reservation.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		//reservation.setFacilityName(request.getParameter("facility_name"));
		reservation.setStartTime(DateUtils.getSqlDate(request.getParameter("start_time")));
		reservation.setEndTime(DateUtils.getSqlDate(request.getParameter("end_time")));

		return reservation; 
	}
}
