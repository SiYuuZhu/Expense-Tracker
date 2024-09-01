package cc.openhome.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cc.openhome.model.AccountingDAOjdbcImpl;
import cc.openhome.model.UserService;

@WebServlet(
    urlPatterns={"/main"}, 
    initParams={
        @WebInitParam(name = "MAIN_PATH", value = "/WEB-INF/jsp/main.jsp")
    }
)
@ServletSecurity(
    @HttpConstraint(rolesAllowed = {"member"})
)

public class main extends HttpServlet {
	
//	private AccountingDAOjdbcImpl accounting = new AccountingDAOjdbcImpl();
	
	protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }
   
    protected void processRequest(
                HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
    	
    	String name = String.valueOf(request.getSession().getAttribute("login"));
    	
    	System.out.println("in Main "+name);
    	
    	UserService userService = (UserService) getServletContext().getAttribute("userService");
    	int todayAmount = userService.findTodayAmount(name);
    	int allCash = userService.findAllCash(name);
    	int allDeposit = userService.findAllDeposit(name);
    	int allAmount = userService.findAllAmount(name);
    	int budget = userService.getBudget(name);
    	int monthCost = userService.getMonthCost(name);
    	
    	System.out.printf("今日餘額  %d\n所有現金  %d\n預算   %d\n本月花費  %d\n",todayAmount, allCash, budget, monthCost);
    	
    	
	    request.setAttribute("overBudget", budget-monthCost);
	    request.setAttribute("todayAmount", todayAmount);
	    request.setAttribute("allCash", allCash);
	    request.setAttribute("allDeposit", allDeposit);
	    request.setAttribute("allAmount", allAmount);
	    request.setAttribute("pcount", OnlineUsers.counter);
	    System.out.println("OnlineUsers "+OnlineUsers.counter);
	    
	    request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
	    
	    
	}
    
}