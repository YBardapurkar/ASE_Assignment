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
		//session.removeAttribute("newf");			// single User object
		
		
		request.getRequestDispatcher("/facility_details.jsp").include(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		//session.removeAttribute("facilityNew");			
		int count1;
	
		AddFacility newfacility = new AddFacility();
		session.setAttribute("facilityNew",newfacility);
		
		if(action.equals("addFacility"))
		{
			
			newfacility.setFacilityType(request.getParameter("facility_type"));
			int count = FacilityDAO.settingFacilityCount(newfacility.getFacilityType());
			ArrayList<AddFacility> addFacility = new ArrayList<AddFacility>();
			
			//int count = addFacility.size() ;
			
			count1 = count - 1 ;
			
			//System.out.println(count);
			addFacility = FacilityDAO.settingFacilityAttributes(newfacility.getFacilityType());
			System.out.println(addFacility);
			String newFacilityName = addFacility.get(count1).getFacilityName();
		
			//System.out.println(newFacilityName);
			
			String incrementedFacilityName = newfacility.incrementFacilityName(newFacilityName, count);
			
			FacilityDAO.insertNewFacility(incrementedFacilityName,addFacility.get(count1).getFacilityType(),
					addFacility.get(count1).getFacilityInterval(),addFacility.get(count1).getFacilityDuration(),
					addFacility.get(count1).getFacilityVenue());
			
			
			String message = "New facility added successfully";
			
			newfacility.setFacilityName(incrementedFacilityName);
			newfacility.setinterval_hours(addFacility.get(count1).getFacilityInterval());
			newfacility.setFacilityDuration(addFacility.get(count1).getFacilityDuration());
			newfacility.setFacilityVenue(addFacility.get(count1).getFacilityVenue());
			
			//request.getRequestDispatcher("/facility_details.jsp").include(request, response);

			
		}
		
		
		
		
		
}
	
}