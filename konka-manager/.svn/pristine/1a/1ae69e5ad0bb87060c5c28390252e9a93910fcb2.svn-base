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

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcSelfAreaAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String self_name_like = (String) dynaBean.get("self_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcSelfArea entity = new EcSelfArea();
		if (StringUtils.isNotBlank(self_name_like)) {
			entity.getMap().put("self_name_like", self_name_like);

		}

		Long recordCount = super.getFacade().getEcSelfAreaService().getEcSelfAreaCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcSelfArea> entityList = super.getFacade().getEcSelfAreaService().getEcSelfAreaList(entity);
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

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcSelfArea entity = new EcSelfArea();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(country)) {
			entity.setP_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country)) {
			entity.setP_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(country) && StringUtils.isBlank(city)) {
			entity.setP_index(Long.valueOf(province));
		}

		if (entity.getP_index() != null) {
			BaseProvinceListFour bf = new BaseProvinceListFour();
			bf.setP_index(entity.getP_index());
			bf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bf);
			if (null != bf && bf.getPar_index() != null) {
				entity.setPar_pindex(bf.getPar_index());
			}
		}

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			super.getFacade().getEcSelfAreaService().createEcSelfArea(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcSelfAreaService().modifyEcSelfArea(entity);
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

		EcSelfArea entity = new EcSelfArea();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcSelfAreaService().removeEcSelfArea(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
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

		EcSelfArea entity = new EcSelfArea();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcSelfAreaService().getEcSelfArea(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
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
		}

		return mapping.findForward("input");
	}
}
