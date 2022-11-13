package it.giuseppetripiciano.project.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.giuseppetripiciano.project.service.interfaces.AccountService;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Login() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getSession();
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String loginType = request.getParameter("loginType");
		String emailId = request.getParameter("emailId");
		String psw = request.getParameter("psw");
		
		byte resStatus = -1;
		
		if (loginType!=null && emailId!=null && psw!=null) {
			loginType = loginType.trim();
			emailId = emailId.trim();
			
			if ((loginType.equals("user") || loginType.equals("machine")) && emailId!="" && psw!="") {
				// login
				
				List<Object> result = new ArrayList<Object>();
				
				result.add(null);
				result.add(Byte.valueOf(resStatus));
				
				AccountService accountService = (AccountService) session.getServletContext().getAttribute("accountServiceBean");
				
				if (loginType.equals("user"))
					result = accountService.userLogin(emailId, psw);
				else {
					try {
						result = accountService.machineLogin(Integer.parseInt(emailId), psw);
					} catch (NumberFormatException nfe) {
						// error
						
						System.out.println(nfe.getMessage());
					}
				}
				
				resStatus = (Byte) result.get(1);
				
				if (resStatus == 1 || resStatus == 2) {
					// success
					
					Object accountBean = result.get(0);
					
					// destroy current session
					
					session.invalidate();
					
					// create new session for the logged account
					
					session = request.getSession();
					
					session.setAttribute("accountBean", accountBean);
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		
		// send to ajax
		
		if (resStatus == 1) // user login success
			// URL for redirecting logged user to /
			
			out.println(response.encodeURL(request.getContextPath()));
		else if (resStatus == 2) // machine login success 
			// URL for redirecting logged machine to /Sell
			
			out.println(response.encodeURL(request.getContextPath() + "/Sell"));
		else
			// resStatus (=0 failure, =-1 error)
			
			out.println(Integer.toString(resStatus));
	}	
}
