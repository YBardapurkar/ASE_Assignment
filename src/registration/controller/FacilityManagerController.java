package registration.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import registration.data.AssignmentDAO;
import registration.data.FacilityDAO;
import registration.data.MARDAO;
import registration.data.UserDAO;
import registration.model.Facility;

import registration.model.FacilityErrorMessages;

//import registration.model.FacilityMessage;

import registration.model.Assignment;
import registration.model.AssignmentMessage;
import registration.model.MAR;
import registration.model.Search;
import registration.model.SearchFacility;
import registration.model.SearchFacilityError;
import registration.model.SearchMessage;
import registration.model.User;
import registration.model.UserError;
import registration.util.DateUtils;
import registration.util.DropdownUtils;

@WebServlet("/facility_manager")
public class FacilityManagerController extends HttpServlet implements HttpSessionListener {

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


		session.removeAttribute("mar");					// single MAR object
		session.removeAttribute("list_mar");			// list of MAR objects
		session.removeAttribute("facility");			// single facility object
		session.removeAttribute("list_facilities");		// list of facility objects
		session.removeAttribute("message");				// single MAR message
		session.removeAttribute("list_repairers");		// list of repairer users
		session.removeAttribute("mar_search");			// single mar search object
		session.removeAttribute("UPDATEUSER");
		session.removeAttribute("success_message");

		session.setAttribute("current_role", "facility_manager");
		session.setAttribute("list_repairers", UserDAO.getUsersByRole("Repairer"));
		session.setAttribute("current_role", "facility_manager");

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

				Assignment assignment = AssignmentDAO.getAssignedToByMarId(mar.getId());

				Facility facility = FacilityDAO.getFacilityByFacilityName(mar.getFacilityName());
				ArrayList<String[]> repairTimes = DropdownUtils.getRepairTimeDropdown(facility.getFacilityDuration());

				session.setAttribute("repair_times", repairTimes);
				session.setAttribute("assign", assignment);
				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				if (assignment.getAssignedTo() == null) {
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
//			Show add facility form
			else if (request.getParameter("add_facility") != null) {
				session.setAttribute("list_facility_types", DropdownUtils.getFacilityTypeDropdown());

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/add_facility.jsp").include(request, response);

			}
//			Show All Facilities
			else if (request.getParameter("facility_list") != null) {
				List<Facility> facilityList = new ArrayList<Facility>();
				facilityList.addAll(FacilityDAO.getAllFacilities());
				session.setAttribute("list_facilities", facilityList);

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/facility_list.jsp").include(request, response);
			}
//			SHow Facility details
			else if (request.getParameter("facility_name") != null) {
				String facilityName = request.getParameter("facility_name");
				Facility facility = FacilityDAO.getFacilityByFacilityName(facilityName);
				session.setAttribute("facility", facility);

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/facility_details.jsp").include(request, response);
			}

//			SHOW search facilities
			else if(request.getParameter("search_facility") != null) {
				session.setAttribute("list_facility_types", DropdownUtils.getFacilityTypeDropdown());

				Facility searchFacility = DropdownUtils.getFacilityTypeDropdown().get(0);

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/search_facilities.jsp").include(request, response);
			}

//			Open profile
			else if (request.getParameter("profile") != null) {
				User user = UserDAO.getUserByUsername(currentUser.getUsername());
				session.setAttribute("UPDATEUSER", user);
				session.setAttribute("state_dropdown", DropdownUtils.getStateDropdown());

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
			}


//			Show Facility Manager Homepage
			else {

				request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
				request.getRequestDispatcher("/home_fm.jsp").include(request, response);
			}

			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session.removeAttribute("mar");					// single MAR object
		session.removeAttribute("list_mar");			// list of MAR objects
		session.removeAttribute("facility");			// single facility object
		session.removeAttribute("list_facilities");		// list of facility objects
		session.removeAttribute("message");				// single MAR assign message
		session.removeAttribute("list_repairers");		// list of repairer users
		session.removeAttribute("mar_search");			// single mar search object
		session.removeAttribute("UPDATEUSER");
		session.removeAttribute("success_message");

		String action = request.getParameter("action");
		session.setAttribute("current_role", "facility_manager");
		AssignmentMessage assignmentMessage = new AssignmentMessage();

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
					} else if (AssignmentDAO.getAssignmentCountByWeek(assignment.getAssignedTo()) >= 10) {
//						More than 10 in a week, cannot assign
						assignmentMessage.setErrorMessage("Cannot assign more than 10 MARs to this repairer in this week.");
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

					if (assignment.getAssignedTo() == null) {
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

				Search marSearch = getSearchParam(request);
				SearchMessage marSearchMessage = new SearchMessage();
				ArrayList<MAR> listMAR = new ArrayList<MAR>();

				marSearchMessage = marSearch.validateSearch(action);

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
//					by date
					else if (marSearch.getSearchFilter().equals("4")) {
						listMAR.addAll(MARDAO.getMARByDate(marSearch.getSearchText()));
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

				if (session.getAttribute("current_user") == null)
					session.setAttribute("current_user", currentUser);
			}

			else if (action.equals("search_facility")) {

				session.removeAttribute("searchFacility1");

				int facilityIndex = Integer.parseInt(request.getParameter("facilityType"));
				List<String> incrementDate1 ;
				List<String> incrementTime1 ;
				Facility facility = DropdownUtils.getFacilityTypeDropdown().get(facilityIndex); // dummy object

				session.setAttribute("facility", facility);

				String incrementDate[] = DateUtils.getSevenDays();
				SearchFacility searchFacility1 = new SearchFacility();
				searchFacility1.setFacilityType(facility.getFacilityType());
				searchFacility1.setSearchTime(request.getParameter("searchTime"));
				searchFacility1.setSearchDate(request.getParameter("searchDate"));

				session.setAttribute("searchFacility1", searchFacility1);

				// check if same day or 7 day
				if (facility.getFacilityDuration().equals("Same Day"))
				{
					 incrementDate1 =  Arrays.asList(incrementDate[0]) ;
				}

				else
				{
					incrementDate1 = Arrays.asList(incrementDate);
				}


				if (facility.getFacilityInterval().equals("1"))
				{
					incrementTime1 = Arrays.asList(DateUtils.listTimes(18, "1"));
				}

				else if (facility.getFacilityInterval().equals("2"))
				{
					incrementTime1 = Arrays.asList(DateUtils.listTimes(9, "2"));
				}

				else
				{
					incrementTime1 = Arrays.asList(DateUtils.listTimes1(36));
				}

				session.setAttribute("incrementDate", incrementDate1);
				session.setAttribute("incrementTime", incrementTime1);
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/search_facilities2.jsp").include(request, response);

			}

			// For showing search facilities


			if (action.equals("search_facility1")) {

				session.removeAttribute("showAllFacilities");
				ArrayList<Facility> facilityList = new ArrayList<Facility>();

//				ArrayList<Facility> showAllFacilities = new ArrayList<Facility>();
				DateUtils DateUtils = new DateUtils();
				Facility facility = new Facility(); //facility model
				SearchFacility searchFacility1 = new SearchFacility(); //search facility model
				SearchFacilityError facilityError = new SearchFacilityError(); // search facility error message

				searchFacility1.setSearchTime(request.getParameter("searchTime"));
				searchFacility1.setSearchDate(request.getParameter("searchDate"));
				searchFacility1.setFacilityType(request.getParameter("facilityType"));

				String prepareTimeStamp = searchFacility1.getSearchDate() + " " + searchFacility1.getSearchTime();

				facilityList = FacilityDAO.showFacilitiesCalling(searchFacility1.getFacilityType(), prepareTimeStamp);

				FacilityErrorMessages facilityErrorMsg = new FacilityErrorMessages(); //facility error messages
				SearchFacilityError searchErrorMsg = new SearchFacilityError();

				session.setAttribute("facilityErrorMsg", facilityErrorMsg);
				session.setAttribute("searchFacility1", searchFacility1);
				session.setAttribute("searchErrorMsg", searchErrorMsg);

				searchFacility1.validateSearchFacility(prepareTimeStamp);

				if(!searchErrorMsg.getErrorMsg().equals(""))
				{
					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/search_facilities2.jsp").include(request, response);
				}


				else if(facilityList.size() == 0)
				{
						searchErrorMsg.setShowFacilityMessage("No facilities available");
						request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
						request.getRequestDispatcher("/facility_list.jsp").include(request, response);
				}

				else
				{
						session.setAttribute("list_facilities", facilityList);
						request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
						request.getRequestDispatcher("/facility_list.jsp").include(request, response);
				}

		}


			// update profile
			else if(action.equals("update_profile")) {

				User updateuser = new User();
				UserError userErrorMsgs = new UserError();

				updateuser = getUpdateProfileParam(request);
				userErrorMsgs = updateuser.validateUser(action);

				if (!userErrorMsgs.getErrorMsg().equals("")) {
 //					if error messages
					session.setAttribute("errorMsgs", userErrorMsgs);
					session.setAttribute("UPDATEUSER", updateuser);

					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
				}
				else {
//					if no error messages

					//update database except role
					UserDAO.updateProfile(updateuser);
					session.setAttribute("success_message", "Profile has been updated!!!!!!!!");
					session.setAttribute("UPDATEUSER", updateuser);

					request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
				}

			}
//			add new facility
			else if(action.equals("add_facility")) {
				int facilityIndex = Integer.parseInt(request.getParameter("facilityType"));
				Facility newFacility = DropdownUtils.getFacilityTypeDropdown().get(facilityIndex);

				int facilityTypeCount = FacilityDAO.getFacilitiesByFacilityType(newFacility.getFacilityType()).size();

				String newFacilityName = newFacility.getFacilityName() + " " + (facilityTypeCount + 1);
				newFacility.setFacilityName(newFacilityName);

				FacilityErrorMessages facilityMessage = newFacility.validateFacility();
				session.setAttribute("errorMsg", facilityMessage);
				FacilityDAO.insertNewFacility(newFacility);

				response.sendRedirect("facility_manager?facility_name=" + newFacilityName);


			}


			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		currentUser = null;
		HttpSessionListener.super.sessionDestroyed(se);
	}

	public Assignment getAssignmentParam(HttpServletRequest request){
		Assignment assignment = new Assignment();

		assignment.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		assignment.setEstimate(Integer.parseInt(request.getParameter("estimate")));
		assignment.setAssignedTo(request.getParameter("repairer"));
		assignment.setUrgency(request.getParameter("urgency"));

		return assignment;
	}

	public Search getSearchParam(HttpServletRequest request){
		Search marSearch = new Search();

		marSearch.setSearchText(request.getParameter("search_text"));
		marSearch.setSearchFilter(request.getParameter("search_filter"));

		return marSearch;
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
