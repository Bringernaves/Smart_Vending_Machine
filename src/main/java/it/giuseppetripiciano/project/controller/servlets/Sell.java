package it.giuseppetripiciano.project.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.giuseppetripiciano.project.service.interfaces.MachineService;

/**
 * Servlet implementation class Sell
 */

@WebServlet("/Sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Sell() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		Object accountBean = session.getAttribute("accountBean");
		
		MachineService machineService = (MachineService) session.getServletContext().getAttribute("machineServiceBean");
		
		machineService.resetStatus(accountBean);
		
		request.getRequestDispatcher("/WEB-INF/view/sell.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idProduct = request.getParameter("id");
		
		HttpSession session = request.getSession(false);
		
		Object accountBean = session.getAttribute("accountBean");
		
		MachineService machineService = (MachineService) session.getServletContext().getAttribute("machineServiceBean");
		
		PrintWriter out = response.getWriter();
		
		byte resStatus = -1;
		
		if (idProduct == null) {
			
			resStatus = machineService.checkStatus(accountBean);
			
			// send to ajax
			
			if (resStatus == 1) // -> connected
				// json
				
				request.getRequestDispatcher("/WEB-INF/view/ajax/stock.jsp").forward(request, response);
			else 
				// resStatus (=2 -> disconnected, =0 unchanged, =-1 -> error)
			
				out.println(Integer.toString(resStatus));
		}
		else {
			try {
				resStatus = machineService.sellProduct(accountBean, Integer.parseInt(idProduct));
			} catch (NumberFormatException nfe) {
				// error
				
				System.out.println(nfe.getMessage());
			}
			
			// send resStatus to ajax
			// (=1 success, =0 failure, =-1 error)
			
			out.println(Integer.toString(resStatus));
		}	
	}
}
