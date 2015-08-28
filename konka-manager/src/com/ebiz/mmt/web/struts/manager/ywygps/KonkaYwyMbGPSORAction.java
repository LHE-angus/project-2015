package com.ebiz.mmt.web.struts.manager.ywygps;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileUserGpsTrack;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-07-11
 */
public class KonkaYwyMbGPSORAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trace_date = (String) dynaBean.get("trace_date");
		String mp_sn = (String) dynaBean.get("mp_sn");

		if (StringUtils.isEmpty(trace_date)) {
			Date today = new Date();
			dynaBean.set("trace_date", DateFormatUtils.format(today, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(mp_sn)) {
			dynaBean.set("user_mp_like", mp_sn);
		}

		return mapping.findForward("list");
	}

	public ActionForward ajaxGetYwyJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String user_mp_like = (String) dynaBean.get("user_mp_like");
		String trace_date = (String) dynaBean.get("trace_date");

		String firstRow = (String) dynaBean.get("firstRow");
		String pageSize = (String) dynaBean.get("pageSize");

		if (StringUtils.isEmpty(trace_date)) {
			return mapping.findForward("list");
		}

		if (StringUtils.isNotBlank(user_mp_like)) {
			user_mp_like = java.net.URLDecoder.decode(user_mp_like, "utf-8");
		}

		StringBuffer json = new StringBuffer("{");

		KonkaMobileUserGpsTrack entity = new KonkaMobileUserGpsTrack();
		entity.getMap().put("user_mp_like", user_mp_like);
		entity.getMap().put("add_date_between", trace_date);

		Long count = super.getFacade().getKonkaMobileUserGpsTrackService()
				.getKonkaMobileUserGpsTrackAndYwyCount(entity);
		entity.getRow().setFirst(Integer.valueOf(firstRow) - 1);
		entity.getRow().setCount(Integer.valueOf(pageSize));
		List<KonkaMobileUserGpsTrack> mtList = super.getFacade().getKonkaMobileUserGpsTrackService()
				.getKonkaMobileUserGpsTrackAndYwyPaginatedList(entity);

		// 当前页面的结果集
		StringBuffer jsonBuffer = new StringBuffer();
		if (null != mtList && mtList.size() > 0) {
			for (KonkaMobileUserGpsTrack userGps : mtList) {
				jsonBuffer.append("{");
				jsonBuffer.append("\"user_name\":\"").append(userGps.getMap().get("user_name"));
				jsonBuffer.append("\",\"mp_sn\":\"").append(userGps.getMp_sn());
				jsonBuffer.append("\",\"dept_name\":\"").append(userGps.getMap().get("dept_name"));
				jsonBuffer.append("\",\"role_name\":\"").append(userGps.getMap().get("role_name"));
				jsonBuffer.append("\",\"update_time\":\"").append(
						DateFormatUtils.format(userGps.getAdd_date(), "yyyy-MM-dd HH:mm"));
				jsonBuffer.append("\"},");
			}

			String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
			json.append("\"list\" : [").append(listStr).append("],");
		}

		json.append("\"count\":\"").append(count).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward ajaxGetUserMoveLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String mp_sn = (String) dynaBean.get("mp_sn");
		String trace_date = (String) dynaBean.get("trace_date");

		String lat_max = (String) dynaBean.get("lat_max");
		String lng_max = (String) dynaBean.get("lng_max");
		String lat_min = (String) dynaBean.get("lat_min");
		String lng_min = (String) dynaBean.get("lng_min");

		if (StringUtils.isEmpty(trace_date) || StringUtils.isEmpty(mp_sn)) {
			return mapping.findForward("list");
		}

		StringBuffer json = new StringBuffer("{");

		KonkaMobileUserGpsTrack entity = new KonkaMobileUserGpsTrack();

		entity.getMap().put("lng_ge", lng_min);
		entity.getMap().put("lng_le", lng_max);
		entity.getMap().put("lat_ge", lat_min);
		entity.getMap().put("lat_le", lat_max);
		entity.setMp_sn(mp_sn);
		entity.getMap().put("add_date_between", trace_date);

		entity.getMap().put("add_date_asc", true);

		List<KonkaMobileUserGpsTrack> mtList = super.getFacade().getKonkaMobileUserGpsTrackService()
				.getKonkaMobileUserGpsTrackList(entity);

		// 当前页面的结果集
		StringBuffer jsonBuffer = new StringBuffer();
		if (null != mtList && mtList.size() > 0) {
			for (KonkaMobileUserGpsTrack userGps : mtList) {
				jsonBuffer.append("{");
				jsonBuffer.append("\"mp_sn\":\"").append(userGps.getMp_sn());
				jsonBuffer.append("\",\"lng\":\"").append(userGps.getLng());
				jsonBuffer.append("\",\"lat\":\"").append(userGps.getLat());
				jsonBuffer.append("\",\"add_date\":\"").append(
						DateFormatUtils.format(userGps.getAdd_date(), "yyyy-MM-dd HH:mm"));
				jsonBuffer.append("\"},");
			}

			String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
			json.append("\"list\" : [").append(listStr).append("]");
		}

		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
}
