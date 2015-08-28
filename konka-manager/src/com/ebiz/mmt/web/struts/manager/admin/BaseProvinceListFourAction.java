package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version build 2011.09.22
 */
public class BaseProvinceListFourAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		Pager pager = (Pager) dynaBean.get("pager");
		String p_name = (String) dynaBean.get("name");
		String p_code = (String) dynaBean.get("p_code");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		BaseProvinceListFour entity = new BaseProvinceListFour();
		entity.setDel_mark(new Integer("0"));

		if (StringUtils.isNotBlank(p_name)) {
			entity.setP_name(p_name);
		}
		if (StringUtils.isNotBlank(p_code)) {
			entity.setP_code(p_code);
		}

		if (GenericValidator.isLong(town)) {
			entity.setP_index(new Long(town));
		}else if (GenericValidator.isLong(country)) {
			entity.setP_index(new Long(country));
		} else if (GenericValidator.isLong(city)) {
			entity.setP_index(new Long(city));
		} else if (GenericValidator.isLong(province)) {
			entity.setP_index(new Long(province));
		}

		Long recordCount = getFacade().getBaseProvinceListFourService().getBaseProvinceListFourCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<BaseProvinceListFour> entityList = getFacade().getBaseProvinceListFourService().getBaseProvinceListFourPaginatedList(
				entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;
		// 
		return this.list(mapping, form, request, response);
	}

	public ActionForward modifyPostAndTel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// 区域p_index
		String p_index = (String) dynaBean.get("p_index");
		BaseProvinceListFour entity = new BaseProvinceListFour();
		entity.setDel_mark((Integer) 0);
		entity.setP_index(Long.valueOf(p_index));
		entity = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(entity);
		super.copyProperties(form, entity);
		return new ActionForward("/../manager/admin/BaseProvinceListFour/modifytelandpost.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward telAndPostSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		BaseProvinceListFour entity = new BaseProvinceListFour();
		super.copyProperties(entity, form);
		super.getFacade().getBaseProvinceListFourService().modifyBaseProvinceListFour(entity);
		saveMessage(request, "entity.updated");
		// 
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 
		String mod_id = (String) dynaBean.get("mod_id");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "series_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");

		BaseProvinceListFour entity = new BaseProvinceListFour();

		if (!GenericValidator.isLong(p_index)) {
			this.saveError(request, "errors.long", new String[] { p_index });
			return mapping.findForward("list");
		}

		entity.setP_index(Long.valueOf(p_index));
		entity = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);

		BaseProvinceListFour BaseProvinceListFour = new BaseProvinceListFour();

		BaseProvinceListFour.setP_index(entity.getPar_index());

		BaseProvinceListFour par_nameEntity = getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFour(BaseProvinceListFour);

		BaseProvinceListFour.setP_index(entity.getRoot_code());

		BaseProvinceListFour root_nameEntity = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
				BaseProvinceListFour);

		request.setAttribute("par_name", par_nameEntity.getP_name());
		request.setAttribute("root_name", root_nameEntity.getP_name());

		entity.setP_index(Long.valueOf(p_index));

		if (entity.getP_level().intValue()!=4) {
			Integer child_p_level = (Integer) (entity.getP_level() + 1);
			entity.setP_level(child_p_level);
			BaseProvinceListFour t =new BaseProvinceListFour();
			t.setPar_index(entity.getP_index());
			request.setAttribute("childrenProvinceListList", getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(t));
		}

		return mapping.findForward("view");
	}
}