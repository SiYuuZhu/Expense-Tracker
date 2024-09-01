package cc.openhome.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.Message;
import cc.openhome.model.UserService;

@WebServlet(
    urlPatterns={"/login"}, 
    initParams={
        @WebInitParam(name = "SUCCESS_PATH", value = "main"),
        @WebInitParam(name = "ERROR_PATH", value = "/WEB-INF/jsp/index.jsp")
    }
)
public class Login extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        System.out.println("open");
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Optional<String> optionalPasswd = userService.encryptedPassword(username, password); //encrypt password
        
        try {
	    	System.out.println("going to1");
        	request.login(username, optionalPasswd.get());
	        request.getSession().setAttribute("login", username);
	        response.sendRedirect("main");
	        
        } catch(NoSuchElementException | ServletException e) {
        	
        	int failTimes = userService.loginFailTimes(username);
        	request.setAttribute("errors", Arrays.asList("登入失敗"+String.valueOf(failTimes+"次"+"，登入錯誤達3次，帳戶將凍結")));
            System.out.println(e.toString());
            
            userService.loginFail(username);
            
            if (failTimes>=3) {							// login fail 3 times condition
            	userService.freezeAccount(username);
            	request.setAttribute("errors", Arrays.asList("登入錯誤已達3次，帳戶已凍結"));
            	System.out.println("freeze");
            }
            
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                   .forward(request, response);
        }
    }
}
