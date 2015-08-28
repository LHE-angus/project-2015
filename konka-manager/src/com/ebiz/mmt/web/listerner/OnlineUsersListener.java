package com.ebiz.mmt.web.listerner;

import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * HttpSessionListener监听session的创建与销毁,比用户登录发生得早<br>
 * 
 * @author zhou
 * 
 */
public class OnlineUsersListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        String sessionid = session.getId();
        Hashtable<String, String> online = (Hashtable<String, String>) application.getAttribute("online");
        if (session.isNew()) {
            PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
            String useraccount = (user == null ? "未登录" : user.getUser_name());
            if (online == null) {
                online = new Hashtable<String, String>();
            }
            online.put(sessionid, useraccount);
            application.setAttribute("online", online);
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        String sessionid = session.getId();
        Hashtable<String, String> online = (Hashtable<String, String>) application.getAttribute("online");
        if (online != null) {
            online.remove(sessionid);
        }
        application.setAttribute("online", online);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        String sessionid = session.getId();
        Hashtable<String, String> online = (Hashtable<String, String>) application.getAttribute("online");
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
        String useraccount = (user == null ? "未登录" : user.getUser_name());
        if (online == null) {
            online = new Hashtable<String, String>();
        }
        online.put(sessionid, useraccount);
        application.setAttribute("online", online);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        ServletContext application = session.getServletContext();
        String sessionid = session.getId();
        Hashtable<String, String> online = (Hashtable<String, String>) application.getAttribute("online");
        if (online != null) {
            online.remove(sessionid);
            application.setAttribute("online", online);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }


}
