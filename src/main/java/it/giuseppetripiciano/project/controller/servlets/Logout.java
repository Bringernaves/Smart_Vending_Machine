package it.giuseppetripiciano.project.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import  it.giuseppetripiciano.project.service.interfaces.AccountService;

/**
 * Servlet implementation class Logout
 */

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Logout() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		// destroy current session if exists 
		
		if (session!=null) {
			Object accountBean = session.getAttribute("accountBean");
			
			if (accountBean!=null) {
				AccountService accountService = (AccountService) session.getServletContext().getAttribute("accountServiceBean");
				
				accountService.logout(accountBean);
			}
			
			session.invalidate();	
		}
		
		// create new session for guest
		
		session = request.getSession();
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
