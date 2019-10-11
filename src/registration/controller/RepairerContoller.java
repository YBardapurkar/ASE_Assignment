package registration.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import registration.data.MARDAO;
import registration.data.ReservationDAO;
import registration.data.FacilityDAO;
import registration.data.UserDAO;
import registration.model.*;
import registration.util.DateUtils;
import registration.util.DropdownUtils;

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
    	session.removeAttribute("searchFacility");  //search facility object	
    	session.removeAttribute("UPDATEUSER");
    	session.removeAttribute("errorMsgs");
    	
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
					boolean reserved = ReservationDAO.reserveCheck(mar.getId());
					if(!reserved) {
						request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
					}
					else
					{
						request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response);
					}
				}
			}
			
//			SHOW search facilities
			else if(request.getParameter("search_facility") != null) {
				session.setAttribute("list_facility_types", DropdownUtils.getFacilityTypeDropdown());
				
				Facility searchFacility = DropdownUtils.getFacilityTypeDropdown().get(0);
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/search_facilities.jsp").include(request, response);
			}	
//			Open profile
			else if (request.getParameter("profile") != null) {
				User user = UserDAO.getUserByUsername(currentUser.getUsername());
				session.setAttribute("UPDATEUSER", user);
				session.setAttribute("state_dropdown", DropdownUtils.getStateDropdown());
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
			}
			
//			List of facilities
			else if (request.getParameter("facility_list") != null) {
				List<Facility> facilityList = new ArrayList<Facility>();
				facilityList.addAll(FacilityDAO.getAllFacilities());
				session.setAttribute("list_facilities", facilityList);
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/facility_list.jsp").include(request, response);
			}
			
//			List of reservations for the repairer
			else if (request.getParameter("my_reservations") != null) {
				ArrayList<Reservation> reservationList = ReservationDAO.getReservationsOfRepairer(currentUser.getUsername());
				session.setAttribute("list_reservations", reservationList);
				
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/reservation_list.jsp").include(request, response);
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
    	session.removeAttribute("UPDATEUSER");
    	session.removeAttribute("errorMsgs");

    	User currentUser = (User) session.getAttribute("current_user");

    	session.setAttribute("current_role", "repairer");

    	String action = request.getParameter("action");

    	Reservation newReservation = new Reservation();
//    	ReservationMessage reservationMessage = new ReservationMessage();
    	
//		user not logged in
    	if (currentUser == null) {

    		response.sendRedirect("login");
    	}
//		logged in
    	else {

    		//search for facilities

//    		reserve facility
    		if (action.equals("reserve_facility")) {
    			ReservationMessage reservationMessage= new ReservationMessage();
    			String validateStartTime = request.getParameter("start_time1");
    			if(validateStartTime.length()==16) {
    			newReservation = getReservationParam(request); //if it is a valid time stamp
    			}
    			newReservation.validateReservation(action,reservationMessage,validateStartTime);
    			   	    	
    			//				TODO this method
    		
    			
    			if (!reservationMessage.getErrorMessage().equals("")) {
    	    		//			if error messages
    	    		session.setAttribute("reservation", newReservation);	
    	    		session.setAttribute("errorMsgs", reservationMessage);
    	    		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
    	    		int id = Integer.parseInt(request.getParameter("mar_id"));
	    			MAR mar = MARDAO.getMARByID(id);
	    			newReservation.setMarId(id);
	    			session.setAttribute("mar", mar);
    	    		
	    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
	    			request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
    	    		request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);    	    		
    	    	}
    						
    	    	else {
    	    		//TODO : Create Dao Layer Ajinkya
    	    			 	    		
    	    			String username = currentUser.getUsername();

    	    			ArrayList<MAR> listMAR = new ArrayList<MAR>();
    	    			listMAR = MARDAO.getMARByAssignedRepairer(username);
    	    			session.setAttribute("list_mar", listMAR);

    	    			int id = Integer.parseInt(request.getParameter("mar_id"));
    	    			MAR mar = MARDAO.getMARByID(id);
    	    			newReservation.setMarId(id);
    	    			ReservationDAO.insertReservation(newReservation);
        	    		session.setAttribute("reservation", newReservation);
        	    		reservationMessage.setMessage("Facility Reserved Sucessfully");
        	    		session.setAttribute("errorMsgs", reservationMessage);
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
    		
    		
    		else if(action.equals("search_facility")) {
    			
    			session.removeAttribute("searchFacility1");
    			
    			int facilityIndex = Integer.parseInt(request.getParameter("facilityType"));
    			
    			Facility searchFacility1 = DropdownUtils.getFacilityTypeDropdown().get(facilityIndex);		// dummy object
    	    	
    			session.setAttribute("searchFacility1", searchFacility1);
    			
    			
    			String incrementDate[] = DateUtils.getSevenDays();
    			//String incrementDate1[] = {incrementDate[0]};   
    			//incrementDate1[] = {"incrementDate[0]"};
    			searchFacility1.setIncrementDate(incrementDate);
    			
    			
    			searchFacility1.setSearchTime(request.getParameter("searchTime"));
    			searchFacility1.setSearchDate(request.getParameter("searchDate"));
    			
    			//System.out.println(searchFacility.getFacilityDuration());
    			
//    			check if same day or 7 day
    			if(searchFacility1.getFacilityDuration().equals("Same Day")) {
    				System.out.println("inside same day");
    				String incrementDate1[] = {incrementDate[0]};
    				searchFacility1.setIncrementDate1(incrementDate1);
    			} else {
    				String incrementDate1[] = incrementDate;
    				searchFacility1.setIncrementDate1(incrementDate1);
    			}
    			
    			if(searchFacility1.getFacilityInterval().equals("1")) {
    				String incrementTime[] = DateUtils.listTimes(18,"1");
    				searchFacility1.setIncrementTime(incrementTime);
    			}
    			
    			else if(searchFacility1.getFacilityInterval().equals("2")) {
    				String incrementTime[] = DateUtils.listTimes(9,"2");
    				searchFacility1.setIncrementTime(incrementTime);
    			}
    			
    			else {
    				String incrementTime[] = DateUtils.listTimes1(36);
    				searchFacility1.setIncrementTime(incrementTime);
    			}
    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    			request.getRequestDispatcher("/search_facilities2.jsp").include(request, response);
    			
    		}
    		
    		
    		//For showing search facilities

    		//SHWOWING AVAILABLE FACILITIES
    		if(action.equals("search_facility1")) {
    		
    			session.removeAttribute("showAllFacilities");
    			ArrayList<Facility> facilityList = new ArrayList<Facility>();
    			
    			ArrayList<Facility> showAllFacilities = new ArrayList<Facility>();
    			DateUtils DateUtils = new DateUtils();
    			Facility searchFacility = new Facility();
    			session.setAttribute("searchFacility", searchFacility);
    			
    			searchFacility.setSearchTime(request.getParameter("searchTime"));
    			searchFacility.setSearchDate(request.getParameter("searchDate"));
    			searchFacility.setFacilityType(request.getParameter("facilityType"));
    			
    			String prepareTimeStamp = searchFacility.getSearchDate() + " " + searchFacility.getSearchTime();
    			showAllFacilities = FacilityDAO.getFacilitiesByFacilityType(searchFacility.getFacilityType());
    			
    			facilityList = FacilityDAO.showFacilitiesCalling(searchFacility.getFacilityType());
    			int size = facilityList.size();
    			
    			 
    			System.out.println(showAllFacilities.size()+"size");
    			
    			
    			boolean compare = DateUtils.compareTimes(prepareTimeStamp);
    			
    			//System.out.println("comparing nowtime with selected time"+compare);
    		if(compare == true)
    		{
    			session.setAttribute("searchFacility",searchFacility);
    			searchFacility.setShowFacilityMessage("Selected time is less than the current time");
    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/search_facilities2.jsp").include(request, response);
    		}
    		
    		else
    		{	
    			if(showAllFacilities.size() == 0)
    			{
    				
    				searchFacility.setShowFacilityMessage("No facilities available");
    				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    				request.getRequestDispatcher("/facility_list.jsp").include(request, response);
    			}
    			
    			else
    			{
    				
    				for(int i=0;i<size;i++)
    				{
    					boolean check = DateUtils.checkReservedFacilities(prepareTimeStamp, facilityList.get(i).getStartTimestamp(), facilityList.get(i).getEndTimestamp());
    					if(check == true)
    					{
    						String name = facilityList.get(i).getFacilityName();
    						
    						 //Iterator iter = showAllFacilities.iterator(); 
    						 Iterator<Facility> iter = showAllFacilities.iterator();  
    						   while (iter.hasNext())
    						   {
    							   Facility f = iter.next();
    							   if(f.getFacilityName().equals(name))
    							   {
    								   iter.remove();
    							   }
    						   }
    						  		          			
    					}
    				}	
    				
    				
        			
        			
        			
    					if(showAllFacilities.size() == 0)
    					{
    						searchFacility.setShowFacilityMessage("No facilities available");
    						request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    						request.getRequestDispatcher("/facility_list.jsp").include(request, response);
    					}
    					
    					else
    					{
    						session.setAttribute("list_facilities", showAllFacilities);
    						request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    						request.getRequestDispatcher("/facility_list.jsp").include(request, response);
    					}
    					
    				}
    				
    		}
    				
    			}
    		

    		
    		
    		
    		
    		//For udpating reservation  facility
    		
    		//TODO For udpating reservation  facility
    		
    		else if(action.equals("reserved_selected_facility")) {
    			String validateStartTime = request.getParameter("start_time1");
    			ReservationMessage reservationMessage= new ReservationMessage();
    			if(validateStartTime.length()==16) {
    			newReservation = getReservationParam(request); //if it is a valid time stamp
    			}
    			newReservation.validateReservation(action,reservationMessage,validateStartTime);
    				System.out.println("I am in reserved_selected_facility");
    				
    				
    				//Need to verify this 
    				
    				String username = currentUser.getUsername();
	    			ArrayList<MAR> listMAR = new ArrayList<MAR>();
	    			listMAR = MARDAO.getMARByAssignedRepairer(username);
	    			session.setAttribute("list_mar", listMAR);

	    			int id = Integer.parseInt(request.getParameter("mar_id"));
	    			MAR mar = MARDAO.getMARByID(id);
	    			newReservation.setMarId(id);
	    			ReservationDAO.updateReservation(newReservation);
    	    		session.setAttribute("reservation", newReservation);
    	    		
	    			session.setAttribute("mar", mar);

	    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
	    			request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
	    			if (mar.getAssignedTo().equals(currentUser.getUsername())) {
	    				reservationMessage.setMessage("Reservation modified Successfully");
	    	    		session.setAttribute("errorMsgs", reservationMessage);
	    				request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response); //Ajinkya check this
	    			}

	    			//    			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
	    			//    			request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
	    		}
    	
    			
    			else if(action.equals("update_profile")) {
    				
    				User updateuser = new User();
    				UserError userErrorMsgs = new UserError();

    				updateuser = getUpdateProfileParam(request);
    				updateuser.validateUser(action, updateuser, userErrorMsgs);
    				
    				if (!userErrorMsgs.getErrorMsg().equals("")) {
     //					if error messages
    					session.setAttribute("errorMsgs", userErrorMsgs);
    					session.setAttribute("UPDATEUSER", updateuser);

    					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
    				}
    				else {
//    					if no error messages

    					//update database except role
    					UserDAO.updateProfile(updateuser); 
    					updateuser.setMessage("Profile has been updated!!!!!!!!");
    					session.setAttribute("UPDATEUSER", updateuser);
    					
    					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
    					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
    				}
    				
    			}
    			
    			if (session.getAttribute("current_user") == null)
    				session.setAttribute("current_user", currentUser);

    		}
    	}

    
    @Override
	public void sessionDestroyed(HttpSessionEvent se) {
		currentUser = null;
		HttpSessionListener.super.sessionDestroyed(se);
	}
    
    public Reservation getReservationParam(HttpServletRequest request) {
    	Reservation reservation = new Reservation();
    	//singe day repair
    	int interval = 0;
    	String datetimeLocal = "";
    	String time_substring = "";
    	String end_time_String = "";
    	int start_time = 0;
    	int end_time = 0;
    	String startTime = "";
    	
    	//multiday repair
    	String day_substring = "";
    	int start_date = 0;
    	int end_date = 0;
    	String start_day_String = "";
    	String end_day_String = "";
    	
    	
		reservation.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		//reservation.setFacilityName(request.getParameter("facility_name"));
		//reservation.setStartTime(DateUtils.getSqlDate(request.getParameter("start_time1")));
		datetimeLocal = request.getParameter("start_time1");
		datetimeLocal = datetimeLocal.concat(":00");
		System.out.println(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
		time_substring = datetimeLocal.substring(11, 13);
		start_time = Integer.parseInt(time_substring);
		if(start_time < 10) {
			startTime = "0" +start_time; //Append 0
			start_time = Integer.parseInt(startTime);
		}
		//start_time = Integer.parseInt(startTime);
		System.out.println("Time_substring "+time_substring);
		reservation.setStartTime(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
		
		//Logic for multiple day reservation if <=10 then single day otherwise multiple day directly start from next day for those
		interval= Integer.parseInt(request.getParameter("interval"));
		System.out.println("interval"+interval);
		
		if(interval <= 10) {
			//Single day repair
			end_time = start_time + interval;
			System.out.println("endtime is "+end_time);			
			end_time_String = datetimeLocal.substring(0,11) + end_time + datetimeLocal.substring(13); //create a new end time string
			System.out.println("end_time_String" + end_time_String);
			//reservation.setStartTime(Timestamp.valueOf(datetimeLocal.replace("T"," ")));
			reservation.setEndTime(Timestamp.valueOf(end_time_String.replace("T", " ")));
		}
		else{
			//Mulitday repair
			day_substring = datetimeLocal.substring(8, 10);
			start_date = Integer.parseInt(day_substring);
			String startDate = "";
			start_date = start_date +1; //Start from next day
			if(start_date < 10) {
				startDate = "0" +start_date; //Append 0
				//start_date = Integer.parseInt(startDate);
			}
			start_day_String = datetimeLocal.substring(0,8) + startDate + datetimeLocal.substring(10); //create a new start time string
			System.out.println("start_day_String"+start_day_String);
			reservation.setStartTime(Timestamp.valueOf(start_day_String.replace("T"," ")));
			
			//end date logic
			String endDate = "";
			int addDay = 0;
			
			if(interval == 18) {
				addDay = 1;
			}
			else {
				addDay = 2;
			}
			
			end_date = start_date + addDay;
			if(end_date < 10) {
				endDate = "0" +end_date; //Append 0
				//start_date = Integer.parseInt(startDate);
			}
			else {
				endDate = Integer.toString(end_date);
				
			}
			end_day_String = datetimeLocal.substring(0,8) + endDate + datetimeLocal.substring(10); //create a new start time string
			System.out.println("end_day_String"+end_day_String);
			reservation.setEndTime(Timestamp.valueOf(end_day_String.replace("T", " ")));
			
		}
		//reservation.setEndTime(Timestamp.valueOf(datetimeLocal_udpated.replace("T"," "))); //need to change this
		return reservation; 
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
