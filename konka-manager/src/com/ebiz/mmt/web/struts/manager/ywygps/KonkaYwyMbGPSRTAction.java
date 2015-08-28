package com.ebiz.mmt.web.struts.manager.ywygps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileUserGps;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-07-14
 */
public class KonkaYwyMbGPSRTAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);

		request.setAttribute("deptList", deptList);
		return mapping.findForward("list");
	}

	public ActionForward ajaxGetYwyJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String user_mp_like = (String) dynaBean.get("user_mp_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String lat_max = (String) dynaBean.get("lat_max");
		String lng_max = (String) dynaBean.get("lng_max");
		String lat_min = (String) dynaBean.get("lat_min");
		String lng_min = (String) dynaBean.get("lng_min");

		String firstRow = (String) dynaBean.get("firstRow");
		String pageSize = (String) dynaBean.get("pageSize");

		if (StringUtils.isNotBlank(user_mp_like)) {
			user_mp_like = java.net.URLDecoder.decode(user_mp_like, "utf-8");
		}

		// 获取分公司所有人员数
		PeProdUser user = new PeProdUser();
		if (StringUtils.isNotBlank(dept_id)) {
			user.getMap().put("dept_id_in", dept_id);
		}

		Long all_dept_user_count = super.getFacade().getPeProdUserService().getPeProdUserCount(user);

		// 获取分公司已实时定位人数
		user.getMap().put("leftJoinKonkaMobileUserGps", true);
		user.getMap().put("mp_sn_is_not_null", true);
		Long fixed_dept_user_count = super.getFacade().getPeProdUserService().getPeProdUserCount(user);

		StringBuffer json = new StringBuffer("{");

		KonkaMobileUserGps entity = new KonkaMobileUserGps();

		entity.getMap().put("lng_ge", lng_min);
		entity.getMap().put("lng_le", lng_max);
		entity.getMap().put("lat_ge", lat_min);
		entity.getMap().put("lat_le", lat_max);
		entity.getMap().put("user_mp_like", user_mp_like);

		Long count = super.getFacade().getKonkaMobileUserGpsService().getKonkaMobileUserGpsAndYwyCount(entity);
		entity.getRow().setFirst(Integer.valueOf(firstRow) - 1);
		entity.getRow().setCount(Integer.valueOf(pageSize));
		List<KonkaMobileUserGps> mtList = super.getFacade().getKonkaMobileUserGpsService()
				.getKonkaMobileUserGpsAndYwyPaginatedList(entity);

		// 当前页面的结果集
		StringBuffer jsonBuffer = new StringBuffer();
		if (null != mtList && mtList.size() > 0) {
			for (KonkaMobileUserGps userGps : mtList) {
				jsonBuffer.append("{");
				jsonBuffer.append("\"user_name\":\"").append(userGps.getMap().get("user_name"));
				jsonBuffer.append("\",\"mp_sn\":\"").append(userGps.getMp_sn());
				jsonBuffer.append("\",\"dept_name\":\"").append(userGps.getMap().get("dept_name"));
				jsonBuffer.append("\",\"role_name\":\"").append(userGps.getMap().get("role_name"));
				jsonBuffer.append("\",\"lng\":\"").append(userGps.getLng());
				jsonBuffer.append("\",\"lat\":\"").append(userGps.getLat());
				jsonBuffer.append("\",\"update_time\":\"").append(
						DateFormatUtils.format(userGps.getUpdate_time(), "yyyy-MM-dd HH:mm"));
				jsonBuffer.append("\"},");
			}

			String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
			json.append("\"list\" : [").append(listStr).append("],");
		}

		json.append("\"count\":\"").append(count).append("\",");
		json.append("\"all_dept_user_count\":\"").append(all_dept_user_count).append("\",");
		json.append("\"fixed_dept_user_count\":\"").append(fixed_dept_user_count).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
}
