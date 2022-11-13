package it.giuseppetripiciano.project.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.giuseppetripiciano.project.service.interfaces.CustomerService;

/**
 * Servlet implementation class Recharge
 */
@WebServlet("/Recharge")
public class Recharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recharge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/view/recharge.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String addedCredit = request.getParameter("recharge");
		
		byte resStatus = -1;
		
		if (addedCredit!=null) {
			addedCredit = addedCredit.trim();
			if (addedCredit!="") {
				// recharge user
				
				HttpSession session = request.getSession(false);
				
				Object accountBean = session.getAttribute("accountBean");
				
				CustomerService customerService = (CustomerService) session.getServletContext().getAttribute("customerServiceBean");
				
				resStatus = customerService.recharge(accountBean, addedCredit);			
			}
		}
		
		PrintWriter out = response.getWriter();
		
		// send resStatus to ajax
		// (=1 success, =-1 error)
		
		out.println(Integer.toString(resStatus));
	}

}
