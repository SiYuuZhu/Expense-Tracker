package cc.openhome.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.*;

@WebServlet(
	    urlPatterns={"/addR"})
public class AddRecord extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/jsp/add_record.jsp").forward(request, response);
    }
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    	
    	processRequest(request, response);
    	System.out.println("postAdd");
    	request.getRequestDispatcher("/main").forward(request, response);
    }
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
    	
    	boolean type=Boolean.valueOf(request.getParameter("Type"));
    	
    	String date=request.getParameter("tdate");
        String category = request.getParameter("cate");
        boolean accounttype = Boolean.valueOf(request.getParameter("AccountType"));
        int amount = Integer.parseInt(request.getParameter("Amount"));
        String notes = request.getParameter("Notes");
    	String name = String.valueOf(request.getSession().getAttribute("login"));
    	
    	Accounting accting=new Accounting(type,date,category,accounttype,amount,notes,name);
    
    	UserService userService = (UserService) getServletContext().getAttribute("userService");
    	userService.tryCreateAccounting(new Accounting(type,date,category,accounttype,amount,notes,name));
    	System.out.print("added!!");
    	
    }

}
