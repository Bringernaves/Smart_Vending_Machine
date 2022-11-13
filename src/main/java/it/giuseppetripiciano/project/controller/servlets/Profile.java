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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String psw = request.getParameter("psw");
		String new_email = request.getParameter("new_email");
		String old_psw = request.getParameter("old_psw");
		String new_psw = request.getParameter("new_psw");
		
		byte resStatus = -1;
		
		if (psw!=null && new_email!=null) {
			new_email = new_email.trim();
			if (psw!="" && new_email!="") {
				// changeEmail
				
				HttpSession session = request.getSession(false);
				
				Object accountBean = session.getAttribute("accountBean");
				
				CustomerService customerService = (CustomerService) session.getServletContext().getAttribute("customerServiceBean");
					
				resStatus = customerService.changeEmail(accountBean, psw, new_email);
			}
		}
		
		if (old_psw!=null && new_psw!=null) {
			if (old_psw!="" && new_psw!="") {
				// changePassword
				
				HttpSession session = request.getSession(false);
				
				Object accountBean = session.getAttribute("accountBean");
				
				CustomerService customerService = (CustomerService) session.getServletContext().getAttribute("customerServiceBean");
					
				resStatus = customerService.changePassword(accountBean, old_psw, new_psw);
			}
		}
		
		PrintWriter out = response.getWriter();
		
		// send resStatus to ajax
		// (=1 success, =0 failure, =-1 error)
		
		out.println(Integer.toString(resStatus));
	}

}
