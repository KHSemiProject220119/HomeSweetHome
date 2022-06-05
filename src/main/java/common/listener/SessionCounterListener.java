package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
//@WebListener
public class SessionCounterListener implements HttpSessionListener {

	// 접속하고 있는 사용자수
	private static int activeSessions;
	
    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	activeSessions++;
    	System.out.println("> 세션생성! 접속사용자수 : " + activeSessions);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(activeSessions > 0)
    		activeSessions--;
    	System.out.println("> 세션폐기! 접속사용자수 : " + activeSessions);
    }
	
}





