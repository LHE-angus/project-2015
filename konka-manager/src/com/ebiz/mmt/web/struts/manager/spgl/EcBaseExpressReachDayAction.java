package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class EcBaseExpressReachDayAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseExpressReachDay entity = new EcBaseExpressReachDay();

		Long recordCount = super.getFacade().getEcBaseExpressReachDayService()
				.getEcBaseExpressReachDayForStoreNameAndFullNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseExpressReachDay> entityList = super.getFacade().getEcBaseExpressReachDayService()
				.getEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseStore entity = new EcBaseStore();
		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String store_id = (String) dynaBean.get("store_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseExpressReachDay entity = new EcBaseExpressReachDay();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setIs_del(0);

		if (StringUtils.isNotBlank(town)) {
			entity.setArea_code(town);
		} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
			entity.setArea_code(country);
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
			entity.setArea_code(city);
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
				&& StringUtils.isBlank(town)) {
			entity.setArea_code(province);
		}
		if (StringUtils.isNotBlank(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}

		if (StringUtils.isEmpty(id)) {

			// 验证唯一性
			EcBaseExpressReachDay er = new EcBaseExpressReachDay();
			er.setStore_id(Long.valueOf(store_id));
			if (StringUtils.isNotBlank(town)) {
				er.setArea_code(town);
			} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
				er.setArea_code(country);
			} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
				er.setArea_code(city);
			} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
					&& StringUtils.isBlank(town)) {
				er.setArea_code(province);
			}
			Long count = super.getFacade().getEcBaseExpressReachDayService().getEcBaseExpressReachDayCount(er);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.express.reach.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getEcBaseExpressReachDayService().createEcBaseExpressReachDay(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcBaseExpressReachDayService().modifyEcBaseExpressReachDay(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseExpressReachDay entity = new EcBaseExpressReachDay();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcBaseExpressReachDayService().removeEcBaseExpressReachDay(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcBaseExpressReachDay entity = new EcBaseExpressReachDay();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcBaseExpressReachDayService().getEcBaseExpressReachDay(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		String p_index = entity.getArea_code();
		if (StringUtils.isNotBlank(p_index)) {
			if (!p_index.endsWith("00")) {
				if (p_index.length() == 6) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					request.setAttribute("country", p_index);
				} else if (p_index.length() == 8) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
					request.setAttribute("town", p_index);
				}
			} else if (p_index.endsWith("0000")) {
				if (p_index.length() == 6) {
					request.setAttribute("province", p_index);
				} else if (p_index.length() == 8) {
					request.setAttribute("province", p_index);
					request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
				}
			} else if (p_index.endsWith("00")) {
				if (p_index.length() == 6) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("city", p_index);
				} else if (p_index.length() == 8) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
					request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
					request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
				}
			}
		}

		EcBaseStore ebs = new EcBaseStore();
		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("input");
	}
}
