package com.ebiz.mmt.web.struts;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Xing,XiuDong
 */
public abstract class BaseMobileAction extends BaseAction {
	
	private static final Boolean ENABLE_SID_AUTH = false;
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseMobileAction.class);

	/**
	 * 
	 * @param entityList
	 * @return
	 */
	protected PeProdUser getSessionUserInfo(HttpServletRequest request) {
		String sid = (String) request.getAttribute("sid");
		if (!ENABLE_SID_AUTH || null == sid || "null".equalsIgnoreCase(sid)) {
			return super.getSessionUserInfo(request);
		}
		
		ServletContext app = request.getSession().getServletContext();
		String sid_adddate = (String) app.getAttribute(sid);
		
		if (null == sid_adddate || "null".equalsIgnoreCase(sid_adddate)) {
			return null;
		}
		
		String user_name = sid_adddate.split(",")[0];
		
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setUser_name(user_name);
		peProdUser = super.getFacade().getPeProdUserService().getPeProdUser(peProdUser);
		
		return peProdUser;
	}

	/**
	 * Check the given request for a session attribute of the given name.
	 * Returns null if there is no session or if the session has no such
	 * attribute. Does not create a new session if none has existed before!
	 * 
	 * @param request
	 *            current HTTP request
	 * @param name
	 *            the name of the session attribute
	 * @return the value of the session attribute, or <code>null</code> if not
	 *         found
	 * @author Xing,XiuDong
	 * @see org.springframework.web.util.WebUtils.getSessionAttribute
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}
}