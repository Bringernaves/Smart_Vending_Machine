package it.giuseppetripiciano.project.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.giuseppetripiciano.project.model.*;

import it.giuseppetripiciano.project.service.interfaces.CustomerService;

@WebFilter("/*")
public class CustomFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		
		String requestURL = request.getRequestURI();
		
		if(requestURL.matches(".*(css|jpg|js|ttf|woff2)")){
		    chain.doFilter(request, response);
		    return;
		}
		
		// isLogged filter
		
		boolean isLogged = false;
		
		if (session!=null)
			if (session.getAttribute("accountBean")!=null)
				isLogged = true;
		
		String indexRequest = request.getContextPath() + "/";
		String indexRequest2 = request.getContextPath() + "/index.jsp";
		String loginRequest = request.getContextPath() + "/Login";
		String registerRequest = request.getContextPath() + "/Register";
		boolean isIndexRequest = requestURL.matches(indexRequest + "((;jsessionid=(.+))?)") || requestURL.matches(indexRequest2 + "((;jsessionid=(.+))?)");
		boolean isLoginRequest = requestURL.matches(loginRequest + "((;jsessionid=(.+))?)");
		boolean isRegisterRequest = requestURL.matches(registerRequest + "((;jsessionid=(.+))?)");
		
		if (!isLogged) {
			boolean notLoggedisAuthorized = isIndexRequest || isLoginRequest || isRegisterRequest;
			
			if (!notLoggedisAuthorized) 
				if (request.getMethod().equals("POST"))
					response.getWriter().println(response.encodeURL(loginRequest));
				else
					response.sendRedirect(response.encodeRedirectURL(loginRequest));		
			else
				chain.doFilter(request, response);
		}
		else {
			boolean loggedIsAuthorized = !isLoginRequest && !isRegisterRequest;
			
			if(!loggedIsAuthorized)
				response.sendRedirect(response.encodeRedirectURL(indexRequest));
			else {
				// isMachine filter
				
				boolean isMachine = session.getAttribute("accountBean") instanceof Machine;
				
				String sellRequest = request.getContextPath() + "/Sell";
				String logoutRequest = request.getContextPath() + "/Logout";
				boolean isSellRequest = requestURL.matches(sellRequest + "((;jsessionid=(.+))?)");
				boolean isLogoutRequest = requestURL.matches(logoutRequest + "((;jsessionid=(.+))?)");
				
				if (!isMachine) {
					boolean notMachineIsAuthorized = !isSellRequest;
					
					if (!notMachineIsAuthorized)
						response.sendRedirect(response.encodeRedirectURL(indexRequest));
					else {
						// Automatic disconnection on new page request for connected user
						
						User accountBean = (User) session.getAttribute("accountBean");
						
						CustomerService customerService = (CustomerService) session.getServletContext().getAttribute("customerServiceBean");
						
						byte disconnectStatus = customerService.disconnect(accountBean);
						
						if (disconnectStatus == 1)
							customerService.checkCredit(accountBean);
					
						// isAdmin filter
						
						boolean isAdmin = accountBean.getType() == 2;
						
						String registerTechnicianRequest = request.getContextPath() + "/RegisterTechnician";
						String registerMachineRequest = request.getContextPath() + "/RegisterMachine";
						boolean isRegisterMachineRequest = requestURL.matches(registerMachineRequest + "((;jsessionid=(.+))?)");
						boolean isRegisterTechnicianRequest = requestURL.matches(registerTechnicianRequest + "((;jsessionid=(.+))?)");
						
						if (!isAdmin) {
							boolean notAdminIsAuthorized = !isRegisterMachineRequest && !isRegisterTechnicianRequest;
							
							if (!notAdminIsAuthorized)
								response.sendRedirect(response.encodeRedirectURL(indexRequest));
							else {
								// isTechnician filter
								
								boolean isTechnician = accountBean.getType() == 1;
								
								String refillRequest = request.getContextPath() + "/Refill";
								boolean isRefillRequest = requestURL.matches(refillRequest + "((;jsessionid=(.+))?)");
								
								if (!isTechnician) {
									boolean notTechnicianIsAuthorized = !isRefillRequest;
									
									if (!notTechnicianIsAuthorized)
										response.sendRedirect(response.encodeRedirectURL(indexRequest));
									else 
										chain.doFilter(request, response);
								}
								else
									chain.doFilter(request, response);
							}
						}
						else
							chain.doFilter(request, response);
					}
						
				}	
				else {
					boolean machineIsAuthorized = isSellRequest || isLogoutRequest;
					
					if (!machineIsAuthorized)
						response.sendRedirect(response.encodeRedirectURL(sellRequest));
					else
						chain.doFilter(request, response);
				}
			}
		}
	}
}

