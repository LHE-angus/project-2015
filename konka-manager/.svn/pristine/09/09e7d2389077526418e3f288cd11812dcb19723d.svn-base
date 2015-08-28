package com.ebiz.mmt.web.struts.mobile.html;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileLocation;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

public class KonkaMobileLocationAction extends BaseAction {

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			super.renderText(response, "请重新登录！");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		// String mod_code = (String) dynaBean.get("mod_code");
		String longitude = (String) dynaBean.get("longitude");
		String latitude = (String) dynaBean.get("latitude");
		String mobile = (String) dynaBean.get("mobile");
		String imei = (String) dynaBean.get("imei");

		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaMobileLocation entity = new KonkaMobileLocation();
		entity.setLongitude(longitude);
		entity.setLatitude(latitude);
		entity.setAdd_user_id(userInfo.getId());
		entity.setAdd_user_name(userInfo.getUser_name());
		entity.setAdd_time(new Date());
		entity.setMobile(mobile);
		entity.setImei(imei);

		super.getFacade().getKonkaMobileLocationService().createKonkaMobileLocation(entity);
		saveMessage(request, "entity.inserted");

		super.renderText(response, "success");
		return null;
	}

}