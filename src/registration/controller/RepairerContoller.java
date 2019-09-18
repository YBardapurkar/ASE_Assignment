package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.MARDAO;
import registration.model.*;
/**
 * Servlet implementation class RepairerContoller
 */
@WebServlet("/RepairerContoller")
public class RepairerContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepairerContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RepairController");
		HttpSession session = request.getSession();
		String action = request.getParameter("listmars");
		//User user = session.getAttribute("user");
		String username = "p1";
		MAR mar = new MAR();
		if (action.equalsIgnoreCase("List of my MARs")) {
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=MARDAO.listMARS(username, mar);
			session.setAttribute("MARList", marsInDB);	
			getServletContext().getRequestDispatcher("/repairermarlist.jsp").forward(request, response);
		}
	}

}
