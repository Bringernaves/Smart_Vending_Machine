package it.giuseppetripiciano.project.service;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class CustomServletContextListener implements ServletContextListener {
	
	@Resource(name="jdbc/myDataSource")
	DataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		
		ServiceFactory serviceFactory = new ServiceFactory(ds);
		
		ctx.setAttribute("accountServiceBean", serviceFactory.getAccountService());
		ctx.setAttribute("customerServiceBean", serviceFactory.getCustomerService());
		ctx.setAttribute("technicianServiceBean", serviceFactory.getTechnicianService());
		ctx.setAttribute("adminServiceBean", serviceFactory.getAdminService());
		ctx.setAttribute("machineServiceBean", serviceFactory.getMachineService());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		// using webapp's classloader for JDBC driver
		
		try {
			// avoids JDBC unregistration fail
			
			Enumeration<Driver> drivers = DriverManager.getDrivers();
	        Driver d = null;
	        
	        while(drivers.hasMoreElements()) {
		        d = (Driver) drivers.nextElement();
		        DriverManager.deregisterDriver(d);
	        }
	        
	        // avoids JDBC memory leak
	        
			AbandonedConnectionCleanupThread.uncheckedShutdown();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
		}	
	}
}
