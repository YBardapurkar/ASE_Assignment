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
import registration.model.MAR;

@WebServlet("/facility_manager")
public class FacilityManagerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("MAR");
		session.removeAttribute("listMAR");
		session.removeAttribute("errorMsgs");

//		SHow MAR details
		if (request.getParameter("mar_id") != null) {
			int id = Integer.parseInt(request.getParameter("mar_id"));
			MAR mar = MARDAO.getMARByID(id);
			
			session.setAttribute("MAR", mar);
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_details_fm.jsp").include(request, response);
		}
//		Show All MAR
		else if (request.getParameter("mar_list") != null) {
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getAllMAR());
			session.setAttribute("listMAR", marList);
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/mar_table_fm.jsp").include(request, response);
		}
//		Show Facility Manager Homepage
		else {
			request.getRequestDispatcher("/menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/home_fm.jsp").include(request, response);
		}
	}
}
