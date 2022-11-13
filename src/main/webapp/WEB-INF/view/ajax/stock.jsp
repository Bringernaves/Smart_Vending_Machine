<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="it.giuseppetripiciano.project.model.Machine"%>
    
<% List<List<String>> stock = ((Machine) session.getAttribute("accountBean")).getStock(); %>

[
	<% 
		if (stock!=null) {
			for (int i = 1; i < stock.size()-1; i++) { %>
	
				{
					"id": "<%= stock.get(i).get(0) %>", 
					"name":"<%= stock.get(i).get(1) %>", 
					"price":"<%= stock.get(i).get(2) %>", 
					"qty":"<%= stock.get(i).get(3) %>"
				},
 
	<% 
			} 
			if (stock.size() > 1) { %>
	
				{
					"id": "<%= stock.get(stock.size()-1).get(0) %>", 
					"name":"<%= stock.get(stock.size()-1).get(1) %>", 
					"price":"<%= stock.get(stock.size()-1).get(2) %>", 
					"qty":"<%= stock.get(stock.size()-1).get(3) %>"
				}
		
		<% 
			} 
		}
	%>
]
