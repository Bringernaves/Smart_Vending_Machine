package it.giuseppetripiciano.project.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSessionListener;  
import javax.servlet.annotation.WebListener;

import it.giuseppetripiciano.project.model.*;

import it.giuseppetripiciano.project.service.interfaces.CustomerService;

@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated (HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		
		System.out.println("Session with ID " + session.getId() + " created.");
	}
	
	@Override
	public void sessionDestroyed (HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		
		boolean isLogged = false;
		
		if (session.getAttribute("accountBean")!=null)
			isLogged = true;
		
		if (isLogged) {
			boolean isMachine = session.getAttribute("accountBean") instanceof Machine;
			
			if (!isMachine) {
				User accountBean = (User) session.getAttribute("accountBean");
				
				CustomerService customerService = (CustomerService) hse.getSession().getServletContext().getAttribute("customerServiceBean");
				
				customerService.disconnect(accountBean);
			}
		}
		
		System.out.println("Session with ID " + session.getId() + " destroyed.");
	}
}
