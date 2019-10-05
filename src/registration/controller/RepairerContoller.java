package registration.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sun.xml.internal.bind.v2.TODO;

import registration.data.MARDAO;
import registration.data.ReservationDAO;
import registration.model.*;
import registration.util.DateUtils;

@WebServlet("/repairer")
public class RepairerContoller extends HttpServlet implements HttpSessionListener {

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
    	session.removeAttribute("list_mar");		// list of MAR objects
    	session.removeAttribute("mar");				// list of MAR objects
    	
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
			
			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	    		session.setAttribute("reservation", newReservation);	
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
    	    				request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response); //Ajinkya check this
    	    			}

    	    			//    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    	    			//    			request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
    	    		}

    	    	}

    		}
    	}

    }
    
    @Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		currentUser = null;
		HttpSessionListener.super.sessionDestroyed(se);
	}
    
    public Reservation getReservationParam(HttpServletRequest request) {
    	Reservation reservation = new Reservation();
    	int interval = 0;
    	String datetimeLocal = "";
    	String time_substring = "";
    	String end_time_String = "";
    	int start_time = 0;
    	int end_time = 0;
    	
		reservation.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		//reservation.setFacilityName(request.getParameter("facility_name"));
		//reservation.setStartTime(DateUtils.getSqlDate(request.getParameter("start_time1")));
		datetimeLocal = request.getParameter("start_time1");
		datetimeLocal = datetimeLocal.concat(":00");
		System.out.println(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
		time_substring = datetimeLocal.substring(11, 13);
		start_time = Integer.parseInt(time_substring);
		
		System.out.println("Time_substring "+time_substring);
		reservation.setStartTime(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
		
		//Logic for multiple day reservation if <=10 then single day otherwise multiple day directly start from next day for those
		interval= Integer.parseInt(request.getParameter("interval"));
		System.out.println("interval"+interval);
		
		if(interval <= 10) {
			//Single day repair
			end_time = start_time + interval;
			System.out.println("endtime is "+end_time);
			
			end_time_String = datetimeLocal.substring(0,11) + end_time + datetimeLocal.substring(13);
			System.out.println("end_time_String" + end_time_String);
			//reservation.setStartTime(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
				
		}
		
		//reservation.setEndTime(Timestamp.valueOf(datetimeLocal_udpated.replace("T"," "))); //need to change this

		return reservation; 
	}
}
