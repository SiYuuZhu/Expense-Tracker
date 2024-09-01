package cc.openhome.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsers implements HttpSessionListener{
	
	public static int counter;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		OnlineUsers.counter+=1;
		System.out.println("created "+OnlineUsers.counter);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		OnlineUsers.counter-=1;
		System.out.println("Destroyed "+OnlineUsers.counter);
	}
}
