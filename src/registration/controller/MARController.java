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
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");
		
//		List companies
		if (action.equalsIgnoreCase("getAllMAR")) {
			List<MAR> marList = MARDAO.getAllMAR();
			session.setAttribute("MAR", marList);				
			getServletContext().getRequestDispatcher("/mar_table.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
		
	}
}
