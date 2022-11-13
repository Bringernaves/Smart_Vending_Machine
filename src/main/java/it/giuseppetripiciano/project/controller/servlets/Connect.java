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
 * Servlet implementation class Connect
 */

@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Connect() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/view/connect.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String idMachine = request.getParameter("id");
		
		byte resStatus = -1;
		
		if (idMachine!=null) {
			idMachine = idMachine.trim();
			if (idMachine!="") {
				try {
					// connect user
					
					HttpSession session = request.getSession(false);
					
					Object accountBean = session.getAttribute("accountBean");
					
					CustomerService customerService = (CustomerService) session.getServletContext().getAttribute("customerServiceBean");
					
					resStatus = customerService.connect(accountBean, Integer.parseInt(idMachine));
					
				} catch (NumberFormatException nfe) {
					// error
					
					System.out.println(nfe.getMessage());
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		
		// send resStatus to ajax
		// (=1 success, =0 failure already connected machine, =2 failure inexistent machine, =-1 error)
		
		out.println(Integer.toString(resStatus));
	}

}
