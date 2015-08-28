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
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-07-05
 */
public class KonkaYwyMbGPSAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("list");
	}

	public ActionForward ajaxGetYwyJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);

		StringBuffer json = new StringBuffer("{");

		// 分公司
		StringBuffer deptBuffer = new StringBuffer();
		if (null != deptList && deptList.size() > 0) {
			for (KonkaDept dept : deptList) {
				deptBuffer.append("{");
				deptBuffer.append("\"dept_id\":\"").append(dept.getDept_id());
				deptBuffer.append("\",\"dept_name\":\"").append(dept.getDept_name());
				deptBuffer.append("\"},");
			}
			String listStr = StringUtils.removeEnd(deptBuffer.toString(), ",");
			json.append("\"deptlist\" : [").append(listStr).append("],");
		}

		KonkaMobileUserGps entity = new KonkaMobileUserGps();

		Long count = super.getFacade().getKonkaMobileUserGpsService().getKonkaMobileUserGpsAndYwyCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(Integer.valueOf(count.toString()));
		List<KonkaMobileUserGps> mtList = super.getFacade().getKonkaMobileUserGpsService()
				.getKonkaMobileUserGpsAndYwyPaginatedList(entity);

		StringBuffer jsonBuffer = new StringBuffer();
		if (null != mtList && mtList.size() > 0) {
			for (KonkaMobileUserGps userGps : mtList) {
				jsonBuffer.append("{");
				jsonBuffer.append("\"user_name\":\"").append(userGps.getMap().get("user_name"));
				jsonBuffer.append("\",\"mp_sn\":\"").append(userGps.getMp_sn());
				jsonBuffer.append("\",\"root_id\":\"").append(userGps.getMap().get("root_id"));
				jsonBuffer.append("\",\"dept_id\":\"").append(userGps.getMap().get("dept_id"));
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
		json.append("\"count\":\"").append(count).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
}
