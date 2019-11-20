package mac_repair.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mac_repair.data.AssignmentDAO;
import mac_repair.data.FacilityDAO;
import mac_repair.data.MARDAO;
import mac_repair.data.ReservationDAO;
import mac_repair.data.UserDAO;
import mac_repair.model.*;
import mac_repair.util.DateUtils;
import mac_repair.util.DropdownUtils;

@WebServlet("/repairer")
public class RepairerContoller extends HttpServlet implements HttpSessionListener {

	private static final long serialVersionUID = 1L;
	HttpSession session;
	User currentUser;
	DateUtils dateUtils = new DateUtils();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(); // defining the session parameter
		if (session.getAttribute("current_user") != null)
			currentUser = (User) session.getAttribute("current_user");

		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session.removeAttribute("list_mar"); // list of MAR objects
		session.removeAttribute("mar"); // list of MAR objects
		session.removeAttribute("searchFacility"); // search facility object
		session.removeAttribute("UPDATEUSER");
		session.removeAttribute("errorMsgs");
		session.removeAttribute("success_message");

		session.setAttribute("current_role", "repairer");

		// user not logged in
		if (currentUser == null) {

			response.sendRedirect("login");
		}
		// logged in
		else {

			// Display list of assigned MAR to repairer
			if (request.getParameter("my_mars") != null) {
				String username = currentUser.getUsername();

				ArrayList<MAR> listMAR = new ArrayList<MAR>();
				listMAR = MARDAO.getMARByAssignedRepairer(username);
				session.setAttribute("list_mar", listMAR);

				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/mar_list_full.jsp").include(request, response);
			}

			// SHow MAR details
			else if (request.getParameter("mar_id") != null) {
				int id = Integer.parseInt(request.getParameter("mar_id"));
				MAR mar = MARDAO.getMARByID(id);
				session.setAttribute("mar", mar);
				Assignment assignment = AssignmentDAO.getAssignedToByMarId(mar.getId());
				session.setAttribute("assign", assignment);
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				if (assignment.getAssignedTo().equals(currentUser.getUsername())) {
					boolean reserved = ReservationDAO.reserveCheck(mar.getId());
					if (!reserved) {
						request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
					} else {
						request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response);
					}
				}
			}

			// SHOW search facilities

			// Open profile
			else if (request.getParameter("profile") != null) {
				User user = UserDAO.getUserByUsername(currentUser.getUsername());
				session.setAttribute("UPDATEUSER", user);
				session.setAttribute("state_dropdown", DropdownUtils.getStateDropdown());

				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
			}

			// List of facilities
			else if (request.getParameter("facility_list") != null) {
				List<Facility> facilityList = new ArrayList<Facility>();
				facilityList.addAll(FacilityDAO.getAllFacilities());
				session.setAttribute("list_facilities", facilityList);

				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/facility_list.jsp").include(request, response);
			}

			// List of reservations for the repairer
			else if (request.getParameter("my_reservations") != null) {
				ArrayList<Reservation> reservationList = ReservationDAO
						.getReservationsOfRepairer(currentUser.getUsername());
				session.setAttribute("list_reservations", reservationList);

				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/reservation_list.jsp").include(request, response);
			}

			// Show Repairer Homepage
			else {
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/home_repairer.jsp").include(request, response);
			}

			if (session.getAttribute("current_user") == null)
				session.setAttribute("current_user", currentUser);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session.removeAttribute("list_mar"); // list of MAR objects
		session.removeAttribute("mar");
		session.removeAttribute("reservation");
		session.removeAttribute("UPDATEUSER");
		session.removeAttribute("errorMsgs");
		session.removeAttribute("success_message");

		User currentUser = (User) session.getAttribute("current_user");

		session.setAttribute("current_role", "repairer");

		String action = request.getParameter("action");

		Reservation newReservation = new Reservation();
		// ReservationMessage reservationMessage = new ReservationMessage();

		// user not logged in
		if (currentUser == null) {

			response.sendRedirect("login");
		}
		// logged in
		else {

			// search for facilities

			// reserve facility
			if (action.equals("reserve_facility")) {
				ReservationMessage reservationMessage = new ReservationMessage();
				String validateStartTime = request.getParameter("start_time1");
				newReservation = getReservationParam(request); // if it is a valid time stamp
				int marId = newReservation.getMarId();
				MAR mar = MARDAO.getMARByID(marId);
				newReservation.setFacilityName(mar.getFacilityName());
				int interval = Integer.parseInt(request.getParameter("interval"));
				newReservation.validateReservation(reservationMessage, validateStartTime, interval, Timestamp.valueOf(dateUtils.nowTimeStamp()));

				if (!reservationMessage.getErrorMessage().equals("")) {
					// if error messages
					session.setAttribute("reservation", newReservation);
					session.setAttribute("errorMsgs", reservationMessage);
					request.getRequestDispatcher("/menu_login.jsp").include(request, response);
					session.setAttribute("mar", mar);

					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
					request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response);
				}
				else {
					String username = currentUser.getUsername();

					ArrayList<MAR> listMAR = new ArrayList<MAR>();
					listMAR = MARDAO.getMARByAssignedRepairer(username);
					session.setAttribute("list_mar", listMAR);
					Assignment assignment = AssignmentDAO.getAssignedToByMarId(mar.getId());
					session.setAttribute("assign", assignment);
					newReservation.setMarId(marId);
					ReservationDAO.insertReservation(newReservation);
					session.setAttribute("reservation", newReservation);
					session.setAttribute("success_message", "Facility Reserved Sucessfully");
					session.setAttribute("errorMsgs", reservationMessage);
					session.setAttribute("mar", mar);

					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
					session.setAttribute("errorMsgs", reservationMessage);
					request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response); // Ajinkya
				}
			}

			// For udpating reservation facility
			else if (action.equals("reserved_selected_facility")) {
				String validateStartTime = request.getParameter("start_time1");
				ReservationMessage reservationMessage = new ReservationMessage();
				if (validateStartTime.length() == 16) {
					newReservation = getReservationParam(request); // if it is a valid time stamp
				}
				int marId = newReservation.getMarId();
				MAR mar = MARDAO.getMARByID(marId);
				newReservation.setFacilityName(mar.getFacilityName());
				int interval = Integer.parseInt(request.getParameter("interval"));
				newReservation.validateReservation(reservationMessage, validateStartTime, interval, Timestamp.valueOf(dateUtils.nowTimeStamp()));

				if (!reservationMessage.getErrorMessage().equals("")) { 
					// if error messages
					session.setAttribute("reservation", newReservation);
					session.setAttribute("errorMsgs", reservationMessage);
					request.getRequestDispatcher("/menu_login.jsp").include(request, response);
					int id = Integer.parseInt(request.getParameter("mar_id"));
					newReservation.setMarId(id);
					session.setAttribute("mar", mar);
					session.setAttribute("reservation", newReservation);

					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
					request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response);
				} else {
//					no error
					System.out.println("I am in reserved_selected_facility");
					// Need to verify this
					String username = currentUser.getUsername();
					ArrayList<MAR> listMAR = new ArrayList<MAR>();
					listMAR = MARDAO.getMARByAssignedRepairer(username);
					session.setAttribute("list_mar", listMAR);

					int id = Integer.parseInt(request.getParameter("mar_id"));
					newReservation.setMarId(id);
					Assignment assignment = AssignmentDAO.getAssignedToByMarId(mar.getId());
					ReservationDAO.updateReservation(newReservation);
					session.setAttribute("reservation", newReservation);
					session.setAttribute("assign", assignment);
					session.setAttribute("mar", mar);

//					reservationMessage.setMessage("Reservation modified Successfully");
					session.setAttribute("success_message", "Reservation Modified Sucessfully");
					session.setAttribute("errorMsgs", reservationMessage);
					
					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
					request.getRequestDispatcher("/facility_reserved_form.jsp").include(request, response); // Ajinkya
				}
			}

			else if (action.equals("cancel_reserve")) {

				ReservationMessage reserve = new ReservationMessage();
				int marId = Integer.parseInt(request.getParameter("mar_id"));

				int reservationId = ReservationDAO.getReservationIDByMarID(marId);

				MAR mar = MARDAO.getMARByID(marId);
				newReservation.setMarId(marId);

				ReservationDAO.cancelReservation(reservationId);
				session.setAttribute("reservation", newReservation);
//				reserve.setMessage("Cancellation successful");
				session.setAttribute("success_message", "Reservation Cancelled");
				session.setAttribute("errorMsgs", reserve);
				session.setAttribute("mar", mar);
				request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
				request.getRequestDispatcher("/mar_details_full.jsp").include(request, response);
				request.getRequestDispatcher("/facility_reservation_form.jsp").include(request, response); // Ajinkya
																											// check
																											// this
			}

			else if (action.equals("update_profile")) {
				User updateuser = new User();
				UserError userErrorMsgs = new UserError();
				updateuser = getUpdateProfileParam(request);
				userErrorMsgs = updateuser.validateUser(action);
				if (!userErrorMsgs.getErrorMsg().equals("")) {
					// if error messages
					session.setAttribute("errorMsgs", userErrorMsgs);
					session.setAttribute("UPDATEUSER", updateuser);

					request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
					request.getRequestDispatcher("/update_profile_form.jsp").include(request, response);
				} else {
					// if no error messages
					// update database except role
					UserDAO.updateProfile(updateuser);
					session.setAttribute("success_message", "Profile has been updated!!!!!!!!");
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
		// singe day repair
		int interval = 0;
		String datetimeLocal = "";

		reservation.setMarId(Integer.parseInt(request.getParameter("mar_id")));
		reservation.setFacilityName(request.getParameter("facility_name"));
		// reservation.setStartTime(DateUtils.getSqlDate(request.getParameter("start_time1")));
		datetimeLocal = request.getParameter("start_time1");

		Timestamp st = dateUtils.getTimestampFromDateTime(datetimeLocal);
		Calendar cal = Calendar.getInstance();
		System.out.println("before" + st.getTime());
		cal.setTimeInMillis(st.getTime());
		interval = Integer.parseInt(request.getParameter("interval"));

		System.out.println(cal.getTime());

		if (interval < 18) {
			// SingleDay
			cal.add(Calendar.HOUR_OF_DAY, interval);
		} else {
			// multi-day
//			cal.add(Calendar.DAY_OF_MONTH, interval/18);
			cal.set(Calendar.HOUR_OF_DAY, 6);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			st = new Timestamp(cal.getTime().getTime());

			cal.add(Calendar.DAY_OF_MONTH, interval / 18);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.add(Calendar.MILLISECOND, -1);
		}
		reservation.setStartTime(st);
		Timestamp et = new Timestamp(cal.getTime().getTime());
		reservation.setEndTime(et);
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
