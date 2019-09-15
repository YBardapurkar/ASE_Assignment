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

@WebServlet("/mar")
public class MARController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("MAR");
		session.removeAttribute("listMAR");
		session.removeAttribute("errorMsgs");
		
//		MAR details
		if (request.getParameter("mar_id") != null) {
			int id = Integer.parseInt(request.getParameter("mar_id"));
			MAR mar = MARDAO.getMARByID(id);
			
			session.setAttribute("MAR", mar);
			request.getRequestDispatcher("/mar_details.jsp").include(request, response);
		}
//		List by assigned status
		else if (request.getParameter("assigned") != null) {
			boolean assigned = ("true".equals(request.getParameter("assigned")));
			List<MAR> marList = new ArrayList<MAR>();
			if (assigned) {
				marList.addAll(MARDAO.getAssignedMAR());
			} else {
				marList.addAll(MARDAO.getUnassignedMAR());
			}
			session.setAttribute("listMAR", marList);				
			request.getRequestDispatcher("/mar_table.jsp").include(request, response);
		}
//		List MAR
		else {
			List<MAR> marList = new ArrayList<MAR>();
			marList.addAll(MARDAO.getAllMAR());
			session.setAttribute("listMAR", marList);				
			request.getRequestDispatcher("/mar_table.jsp").include(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("MAR");
		session.removeAttribute("allMAR");
		session.removeAttribute("errorMsgs");
		
	}
}
