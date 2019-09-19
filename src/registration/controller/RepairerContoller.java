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
@WebServlet("/repairer")
public class RepairerContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepairerContoller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	User currentUser = (User) session.getAttribute("user");
    	
//    	Display list of assigned MAR to repairer
		if (request.getParameter("my_mars") != null) {
			String username = currentUser.getUsername();
			System.out.println(username);
			MAR mar = new MAR();
			
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=MARDAO.listMARS(username, mar);
			
			session.setAttribute("MARList", marsInDB);
			
			request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
			request.getRequestDispatcher("/repairermarlist.jsp").include(request, response);
//			getServletContext().getRequestDispatcher("/repairermarlist.jsp").forward(request, response);
		}
//		Show Repairer Homepage
		else {
	    	request.getRequestDispatcher("/menu_repairer.jsp").include(request, response);
			request.getRequestDispatcher("/home.jsp").include(request, response);
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		
		String action = request.getParameter("listmars");
		//User user = session.getAttribute("user");
		String username = currentUser.getUsername();
		MAR mar = new MAR();
		if (action.equalsIgnoreCase("List of my MARs")) {
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=MARDAO.listMARS(username, mar);
			session.setAttribute("MARList", marsInDB);	
			getServletContext().getRequestDispatcher("/repairermarlist.jsp").forward(request, response);
		}
	}

}
