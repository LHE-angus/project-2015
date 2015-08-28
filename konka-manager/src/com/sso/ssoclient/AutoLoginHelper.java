package com.sso.ssoclient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebiz.mmt.web.Constants;

public class AutoLoginHelper {
	public String doAutoLogin(String username, HttpSession session) {
		return username;
	}

	public void doLogout(HttpServletRequest request,
			HttpServletResponse response) { 
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.USER_INFO);
			session.removeAttribute(Constants.ROLE_INFO);
			session.removeAttribute(Constants.ROLE_INFO_LIST);
			session.invalidate();
		} 
	}
}