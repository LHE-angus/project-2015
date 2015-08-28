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
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version build 2011.09.22
 */
public class BaseProvinceListAction extends BaseAction {

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

		BaseProvinceList entity = new BaseProvinceList();
		entity.setDel_mark(new Short("0"));

		if (StringUtils.isNotBlank(p_name)) {
			entity.setP_name(p_name);
		}
		if (StringUtils.isNotBlank(p_code)) {
			entity.setP_code(p_code);
		}

		if (GenericValidator.isLong(country)) {
			entity.setP_index(new Long(country));
		} else if (GenericValidator.isLong(city)) {
			entity.setP_index(new Long(city));
		} else if (GenericValidator.isLong(province)) {
			entity.setP_index(new Long(province));
		}

		Long recordCount = getFacade().getBaseProvinceListService().getBaseProvinceListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<BaseProvinceList> entityList = getFacade().getBaseProvinceListService().getBaseProvinceListPaginatedList(
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
		BaseProvinceList entity = new BaseProvinceList();
		entity.setDel_mark((short) 0);
		entity.setP_index(Long.valueOf(p_index));
		entity = super.getFacade().getBaseProvinceListService().getBaseProvinceList(entity);
		super.copyProperties(form, entity);
		return new ActionForward("/../manager/admin/BaseProvinceList/modifytelandpost.jsp");
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
		BaseProvinceList entity = new BaseProvinceList();
		super.copyProperties(entity, form);
		super.getFacade().getBaseProvinceListService().modifyBaseProvinceList(entity);
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

		BaseProvinceList entity = new BaseProvinceList();

		if (!GenericValidator.isLong(p_index)) {
			this.saveError(request, "errors.long", new String[] { p_index });
			return mapping.findForward("list");
		}

		entity.setP_index(Long.valueOf(p_index));
		entity = getFacade().getBaseProvinceListService().getBaseProvinceList(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);

		BaseProvinceList baseProvinceList = new BaseProvinceList();

		baseProvinceList.setP_index(entity.getPar_index());

		BaseProvinceList par_nameEntity = getFacade().getBaseProvinceListService()
				.getBaseProvinceList(baseProvinceList);

		baseProvinceList.setP_index(entity.getRoot_code());

		BaseProvinceList root_nameEntity = getFacade().getBaseProvinceListService().getBaseProvinceList(
				baseProvinceList);

		request.setAttribute("par_name", par_nameEntity.getP_name());
		request.setAttribute("root_name", root_nameEntity.getP_name());

		entity.setP_index(Long.valueOf(p_index));

		if (!entity.getP_level().equals(new Short("3"))) {
			Short child_p_level = (short) (entity.getP_level() + 1);
			entity.setP_level(child_p_level);
			request.setAttribute("childrenProvinceListList", getFacade().getBaseProvinceListService()
					.getBaseProvinceListChildrenList(entity));
		}

		return mapping.findForward("view");
	}
}