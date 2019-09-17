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
 * Servlet implementation class ViewRepairsController
 */
@WebServlet("/ViewRepairsController")
public class ViewRepairsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRepairsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("repairs");
		String action1= request.getParameter("viewaction");
		String action2= request.getParameter("radioCompany");
		
		MAR mar = new MAR();
		
		System.out.println(action);
		System.out.println(action1);
		System.out.println(action2);
		

		if(action2!=null)
		{
			if(action == null)
			{
				String action3= request.getParameter("viewaction"+action2);
				mar=MARDAO.getMARByID(Integer.parseInt(action3));
				
				session.setAttribute("MAR", mar);	
				getServletContext().getRequestDispatcher("/mar_details.jsp").forward(request, response);

				
			}
			else
			{
				System.out.println("reserve facility");
			}
			
		}
	}

}
