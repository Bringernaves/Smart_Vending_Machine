package it.giuseppetripiciano.project.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.giuseppetripiciano.project.service.interfaces.AccountService;

/**
 * Servlet implementation class Register
 */

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Register() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getSession();
		
		request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		
		byte resStatus = -1;
		
		if (firstname!=null && lastname!=null && email!=null && psw!=null) {
			firstname = firstname.trim();
			lastname = lastname.trim();
			email = email.trim();
			if (firstname!="" && lastname!="" && email!="" && psw!="") {
				// register user
				
				AccountService accountService = (AccountService) session.getServletContext().getAttribute("accountServiceBean");
				
				resStatus = accountService.registerCustomer(firstname, lastname, email, psw);
			}
		}		
									
		PrintWriter out = response.getWriter();
		
		// send resStatus to ajax
		// (=1 success, =0 failure, =-1 error)
		
		out.println(Integer.toString(resStatus));
	}
}
