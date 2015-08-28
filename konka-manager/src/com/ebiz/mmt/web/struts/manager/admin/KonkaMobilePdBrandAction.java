package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobilePdBrand;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobilePdBrandAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.checkPopedomInvalid(request, response);
//		}
//		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		super.encodeCharacterForGetMethod(dynaBean, request);
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String is_del = (String) dynaBean.get("is_del");
		
		if (StringUtils.isBlank(is_del)) {
			is_del = "0";
		}
		
		KonkaMobilePdBrand entity = new KonkaMobilePdBrand();
		super.copyProperties(entity, form);
		entity.getMap().put("brand_name_like", brand_name_like);
		dynaBean.set("brand_name_like", brand_name_like);
		entity.setIs_del(Integer.valueOf(is_del));
		dynaBean.set("is_del", is_del);
		
		Long recordCount = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandCount(entity);
		pager.init(recordCount, new Integer(10), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobilePdBrand> list = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandPaginatedList(entity);
		request.setAttribute("entityList", list);
		
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String brand_id = (String) dynaBean.get("brand_id");
		String brand_name = (String) dynaBean.get("brand_name");
		
		KonkaMobilePdBrand temp = new KonkaMobilePdBrand();
		if (StringUtils.isNotBlank(brand_id)) {
			temp.getMap().put("notThisId", brand_id);
			logger.info("_________________________brand_id = " + brand_id);
		}
		temp.setBrand_name(brand_name);
		Long num = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandCount(temp);
		if (num.intValue() > 0) {
			saveError(request, "konka.mobile.pd.brand.error.is.exist", new String[]{brand_name});
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
//			pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		}
		
		KonkaMobilePdBrand entity = new KonkaMobilePdBrand();
		super.copyProperties(entity, form);
		
		if (StringUtils.isBlank(brand_id)) {
			super.getFacade().getKonkaMobilePdBrandService().createKonkaMobilePdBrand(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaMobilePdBrandService().modifyKonkaMobilePdBrand(entity);
			saveMessage(request, "entity.updated");
		}
		
		updateDataPatch();
		
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
//		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String brand_id = (String) dynaBean.get("brand_id");
		String mod_id = (String) dynaBean.get("mod_id");
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		if (GenericValidator.isLong(brand_id)) {
			KonkaMobilePdBrand entity = new KonkaMobilePdBrand();
			entity.setBrand_id(Long.valueOf(brand_id));
			entity = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrand(entity);
			super.copyProperties(form, entity);
		} else {
			this.saveError(request, "errors.long", new String[] { brand_id });
			return new ActionForward("KonkaMobilePdBrand.do?&mod_id="+mod_id, true);
		}
		return mapping.findForward("input");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String brand_id = (String) dynaBean.get("brand_id");
		String mod_id = (String) dynaBean.get("mod_id");
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		if (GenericValidator.isLong(brand_id)) {
			KonkaMobilePdBrand entity = new KonkaMobilePdBrand();
			entity.setBrand_id(Long.valueOf(brand_id));
			entity = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrand(entity);
			super.copyProperties(form, entity);
			dynaBean.set("type_desc", turn(entity.getType_desc()));
		} else {
			this.saveError(request, "errors.long", new String[] { brand_id });
			return new ActionForward("KonkaMobilePdBrand.do?&mod_id="+mod_id, true);
		}
		return mapping.findForward("view");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String brand_id = (String) dynaBean.get("brand_id");
		String[] pks = (String[]) dynaBean.get("pks");
		
		KonkaMobilePdBrand entity = new KonkaMobilePdBrand();
		entity.setIs_del(1);
		if (!StringUtils.isBlank(brand_id) && GenericValidator.isLong(brand_id)) {
			entity.setBrand_id(Long.valueOf(brand_id));
			super.getFacade().getKonkaMobilePdBrandService().removeKonkaMobilePdBrand(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaMobilePdBrandService().removeKonkaMobilePdBrand(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id + "&is_del=1");
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "mod_id")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
		
	}
	
	/**
	 * @desc 页面显示textarea
	 * @param str
	 * @return str
	 */
	public String turn(String str) {
		String content = StringUtils.replace(str, " ", "&nbsp;");
		content = StringUtils.replace(content, "\n", "<br />");
		return content;
	}
	
}
