package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.FacilityDAO;

import registration.model.AddFacility;
import registration.model.AddFacilityErrorMsgs;
import registration.model.User;




@WebServlet("/facility")
public class AddFacilityController extends HttpServlet{
	

	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("newFacility");			// single User object
		
		request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
		request.getRequestDispatcher("/add_facility.jsp").include(request, response);

		//session.removeAttribute("newf");			// single User object
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getRequestDispatcher("/add_facility.jsp").include(request, response);
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("newFacility");			
	
		int count1;
	
		AddFacility newFacility = new AddFacility();
		session.setAttribute("newFacility",newFacility);
		
		//session.removeAttribute("facilityNew");			
	
		if(action.equals("addFacility"))
		{
			
			newFacility.setFacilityType(request.getParameter("facilityType"));
			System.out.println(request);
			int count = FacilityDAO.settingFacilityCount(newFacility.getFacilityType());
			ArrayList<AddFacility> addFacility = new ArrayList<AddFacility>();
			
			//int count = addFacility.size() ;
			
			count1 = count - 1 ;
			
			//System.out.println(count);
			addFacility = FacilityDAO.settingFacilityAttributes(newFacility.getFacilityType());
			System.out.println(addFacility);
			String newFacilityName = addFacility.get(count1).getFacilityName();
		
			//System.out.println(newFacilityName);
			
			String incrementedFacilityName = newFacility.incrementFacilityName(newFacilityName, count);
			
			FacilityDAO.insertNewFacility(incrementedFacilityName,addFacility.get(count1).getFacilityType(),
					addFacility.get(count1).getFacilityInterval(),addFacility.get(count1).getFacilityDuration(),
					addFacility.get(count1).getFacilityVenue());
			
			
			String message = "New facility added successfully";
			
			newFacility.setFacilityName(incrementedFacilityName);
			newFacility.setinterval_hours(addFacility.get(count1).getFacilityInterval());
			newFacility.setFacilityDuration(addFacility.get(count1).getFacilityDuration());
			newFacility.setFacilityVenue(addFacility.get(count1).getFacilityVenue());
			
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/facility_details.jsp").include(request, response);
			
		}
		
		
		
		
		
}
	
}