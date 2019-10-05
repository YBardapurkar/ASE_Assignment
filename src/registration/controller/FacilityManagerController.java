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
import registration.data.FacilityDAO;
import registration.data.MARDAO;
import registration.data.UserDAO;
import registration.model.AddFacility;
import registration.model.Assignment;
import registration.model.AssignmentMessage;
import registration.model.MAR;
import registration.model.MARSearch;
import registration.model.MARSearchMessage;
import registration.model.User;

@WebServlet("/facility_manager")
public class FacilityManagerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("mar");					// single MAR object
		session.removeAttribute("list_mar");			// list of MAR objects
		session.removeAttribute("message");	// single MAR message
		session.removeAttribute("list_repairers");		// list of repairer users
		session.removeAttribute("mar_search");			// single mar search object
		
		User currentUser = (User) session.getAttribute("current_user");
		session.setAttribute("current_role", "facility_manager");
		session.setAttribute("list_repairers", UserDAO.getUsersByRole("Repairer"));
		String action = request.getParameter("action");

		AddFacility newFacility = new AddFacility();

		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {
			session.setAttribute("current_user", currentUser);

//			SHow MAR details
			if (request.getParameter("mar_id") != null) {
				int id = Integer.parseInt(request.getParameter("mar_id"));
				MAR mar = MARDAO.getMARByID(id);
				session.setAttribute("mar", mar);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				if (mar.getAssignedTo() == null) {
					request.getRequestDispatcher("/mar_details.jsp").include(request, response);
					request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				}
			}
//			Show All MAR
			else if (request.getParameter("mar_list") != null) {
				List<MAR> marList = new ArrayList<MAR>();
				marList.addAll(MARDAO.getAllMAR());
				session.setAttribute("list_mar", marList);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}
//			Show search MAR page
			else if (request.getParameter("search_mar") != null) {
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_search_form.jsp").include(request, response);
			}
//			Show unassigned MAR
			else if (request.getParameter("unassigned_mar") != null) {
				List<MAR> marList = new ArrayList<MAR>();
				marList.addAll(MARDAO.getUnassignedMAR());
				session.setAttribute("list_mar", marList);
				
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}
			
			else if (request.getParameter("addFacility") != null) {
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/add_facility.jsp").include(request, response);
				
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
		session.removeAttribute("mar");					// single MAR object
		session.removeAttribute("list_mar");			// list of MAR objects
		session.removeAttribute("message");	// single MAR assign message
		session.removeAttribute("list_repairers");		// list of repairer users
		session.removeAttribute("mar_search");			// single mar search object
		session.removeAttribute("newFacility");			

		
		String action = request.getParameter("action");
		
		User currentUser = (User) session.getAttribute("current_user");
		session.setAttribute("current_role", "facility_manager");
		AssignmentMessage assignmentMessage = new AssignmentMessage();

		int count1;
		
		AddFacility newFacility = new AddFacility();
		session.setAttribute("newFacility",newFacility);

		
//		user not logged in
		if (currentUser == null) {
			
			response.sendRedirect("login");
		}
//		logged in
		else {
			session.setAttribute("current_user", currentUser);
			
//			Assign a repairer to a mar
			if (action.equals("assign_repairer")) {
				Assignment assignment = getAssignmentParam(request);
				assignment.validateAssignment("assign_mar", assignmentMessage);
				
				if (!assignmentMessage.getErrorMessage().equals("")) {
//					error messages
					session.setAttribute("message", assignmentMessage);
					
					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details.jsp").include(request, response);
					request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
				} else {
//					no error
					if (AssignmentDAO.getAssignmentCountByDay(assignment.getAssignedTo(), new Date(System.currentTimeMillis())) >= 5) { 
//						More than 5 in a day, cannot assign
						assignmentMessage.setErrorMessage("Cannot assign more than 5 MARs to this repairer today.");
					} else {
//						can assign
						AssignmentDAO.assignRepairer(assignment);
						assignmentMessage.setSuccessMessage("MAR Assigned");
					}
					
//					save in session
					MAR mar = MARDAO.getMARByID(assignment.getMarId());
					session.setAttribute("mar", mar);
					session.setAttribute("message", assignmentMessage);
					
//					redirect
					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					
					if (mar.getAssignedTo() == null) {
						request.getRequestDispatcher("/mar_details.jsp").include(request, response);
						request.getRequestDispatcher("/mar_assign_form.jsp").include(request, response);
					} else {
						request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
					}
					
					session.removeAttribute("message");
				}
			}
			
//			Search MAR
			else if(action.equals("search_mar")) {
				
				MARSearch marSearch = getSearchParam(request);
				MARSearchMessage marSearchMessage = new MARSearchMessage();
				ArrayList<MAR> listMAR = new ArrayList<MAR>();
				
				marSearch.validateMARSearch(action, marSearch, marSearchMessage);
				
				if (!marSearchMessage.getSearchErrorMessage().equals("")) {
//							set error messages
					session.setAttribute("message", marSearchMessage);
	
//					set jsp pages
					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					request.getRequestDispatcher("/mar_search_form.jsp").include(request, response);
				}
				
				else {
//					if no error messages
					
//					by facility name
					if(marSearch.getSearchFilter().equals("1")) {
						listMAR.addAll(MARDAO.searchMARByFacilityName(marSearch.getSearchText()));
					}
//					by assigned repairer
					else if(marSearch.getSearchFilter().equals("2")) {
						listMAR.addAll(MARDAO.searchMARByAssignedRepairer(marSearch.getSearchText()));
					}
//					unassigned
					else if(marSearch.getSearchFilter().equals("3")) {
						listMAR.addAll(MARDAO.getUnassignedMAR());
						marSearch.setSearchText("");
					}
//					all
					else {
						listMAR.addAll(MARDAO.getAllMAR());
						marSearch.setSearchText("");
					}
						
					session.setAttribute("list_mar", listMAR);
					session.setAttribute("mar_search", marSearch);
					
					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					request.getRequestDispatcher("/mar_search_form.jsp").include(request, response);
					if(listMAR.isEmpty()) {
						request.getRequestDispatcher("/mar_list_empty.jsp").include(request, response);
					} else {
						request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
					}
				}
			}
					
		}		
		
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
	
	public Assignment getAssignmentParam(HttpServletRequest request){
		Assignment assignment = new Assignment();
		
		assignment.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		assignment.setEstimate(Integer.parseInt(request.getParameter("estimate")));
		assignment.setAssignedTo(request.getParameter("repairer"));
		assignment.setUrgency(request.getParameter("urgency"));

		return assignment; 
	}
	
	public MARSearch getSearchParam(HttpServletRequest request){
		MARSearch marSearch = new MARSearch();
		
		marSearch.setSearchText(request.getParameter("search_text"));
		marSearch.setSearchFilter(request.getParameter("search_filter"));

		return marSearch; 
	}
}
