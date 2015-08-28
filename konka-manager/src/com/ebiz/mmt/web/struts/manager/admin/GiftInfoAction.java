package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-11-07
 */
public class GiftInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		String type_id = (String) dynaBean.get("type_id");
		String gift_name_like = (String) dynaBean.get("gift_name_like");
		String status = (String) dynaBean.get("status");
		String excel_all = (String) dynaBean.get("excel_all");

		GiftInfo entity = new GiftInfo();

		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		if (StringUtils.isNotBlank(type_id)) {
			entity.setType_id(Long.valueOf(type_id));
		}

		if (StringUtils.isNotBlank(status)) {
			entity.setStatus(Integer.valueOf(status));
		} else {
			entity.setStatus(0);
		}

		if (StringUtils.isNotBlank(gift_name_like)) {
			entity.getMap().put("gift_name_like", gift_name_like);
		}

		Boolean role_id_gt_30_and_btw_200_300 = false;
		PeRoleUser p = new PeRoleUser();
		p.setUser_id(ui.getId());
		List<PeRoleUser> pList = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
		if (pList.size() > 0) {
			for (PeRoleUser temp : pList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
					role_id_gt_30_and_btw_200_300 = true;
			}
		}

		if (!role_id_gt_30_and_btw_200_300) {
			KonkaDept kDept = super.getKonkaDeptForFgs(ui.getDept_id());
			if (null != kDept) {
				entity.setDept_id(kDept.getDept_id());
			} else {
				String msg = super.getMessage(request, "errors.permission");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

		}

		Long recordCount = getFacade().getGiftInfoService().getGiftInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<GiftInfo> entityList = super.getFacade().getGiftInfoService().getGiftInfoForNamePaginatedList(entity);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<GiftInfo> entityList1 = getFacade().getGiftInfoService().getGiftInfoForNamePaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		request.setAttribute("entityList", entityList);

		KonkaCategory kCategory = new KonkaCategory();
		kCategory.setC_type(15);
		List<KonkaCategory> kList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kCategory);
		request.setAttribute("kList", kList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Boolean role_id_gt_30_and_btw_200_300 = false;

		PeRoleUser p = new PeRoleUser();
		p.setUser_id(ui.getId());
		List<PeRoleUser> pList = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
		if (pList.size() > 0) {
			for (PeRoleUser temp : pList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
					role_id_gt_30_and_btw_200_300 = true;
			}
		}

		if (!role_id_gt_30_and_btw_200_300) {
			KonkaDept kDept = super.getKonkaDeptForFgs(ui.getDept_id());
			if (null != kDept) {
				dynaBean.set("dept_id", kDept.getDept_id());
			} else {
				String msg = super.getMessage(request, "errors.permission");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			request.setAttribute("is_admin", "1");
			KonkaDept k = new KonkaDept();
			k.setDept_type(3);
			k.getMap().put("order_by_dept_name", true);
			request.setAttribute("deptList", super.getFacade().getKonkaDeptService().getKonkaDeptList(k));
		}

		KonkaCategory kCategory = new KonkaCategory();
		kCategory.setC_type(15);
		List<KonkaCategory> kList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kCategory);
		request.setAttribute("kList", kList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String gift_id = (String) dynaBean.get("gift_id");
		String ref_price = (String) dynaBean.get("ref_price");
		String other_type = (String) dynaBean.get("other_type");
		String type_id = (String) dynaBean.get("type_id");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long c_index = null;
		if (StringUtils.isNotBlank(type_id) && "-1".equals(type_id) && StringUtils.isNotBlank(other_type)) {
			KonkaCategory k = new KonkaCategory();
			k.setC_name(other_type);
			k.setC_type(15);
			k.setIs_del(0);
			c_index = super.getFacade().getKonkaCategoryService().createKonkaCategory(k);

		}

		GiftInfo entity = new GiftInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(ref_price)) {
			entity.setRef_price(new BigDecimal(ref_price));
		}

		if (GenericValidator.isLong(gift_id)) {
			if (null != c_index) {
				entity.setType_id(c_index);
			}
			super.getFacade().getGiftInfoService().modifyGiftInfo(entity);
			saveMessage(request, "entity.updated");
		} else {
			if (null != c_index) {
				entity.setType_id(c_index);
			}
			entity.setAdd_user_id(ui.getId());
			super.getFacade().getGiftInfoService().createGiftInfo(entity);
			saveMessage(request, "entity.inserted");
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String gift_id = (String) dynaBean.get("gift_id");

		if (!GenericValidator.isLong(gift_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GiftInfo entity = new GiftInfo();
		entity.setGift_id(Long.valueOf(gift_id));
		entity = super.getFacade().getGiftInfoService().getGiftInfo(entity);
		if (null == entity) {
			String msg = super.getMessage(request, "entity.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(form, entity);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		Boolean role_id_gt_30_and_btw_200_300 = false;

		PeRoleUser p = new PeRoleUser();
		p.setUser_id(ui.getId());
		List<PeRoleUser> pList = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);
		if (pList.size() > 0) {
			for (PeRoleUser temp : pList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
					role_id_gt_30_and_btw_200_300 = true;
			}
		}

		if (!role_id_gt_30_and_btw_200_300) {
			KonkaDept kDept = super.getKonkaDeptForFgs(ui.getDept_id());
			if (null != kDept) {
				dynaBean.set("dept_id", kDept.getDept_id());
			} else {
				String msg = super.getMessage(request, "errors.permission");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			request.setAttribute("is_admin", "1");
			KonkaDept k = new KonkaDept();
			k.setDept_type(3);
			k.getMap().put("order_by_dept_name", true);
			request.setAttribute("deptList", super.getFacade().getKonkaDeptService().getKonkaDeptList(k));
		}

		KonkaCategory kCategory = new KonkaCategory();
		kCategory.setC_type(15);
		List<KonkaCategory> kList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kCategory);
		request.setAttribute("kList", kList);

		return mapping.findForward("input");
	}

	public ActionForward validateGiftName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String gift_name = (String) dynaBean.get("gift_name");
		GiftInfo entity = new GiftInfo();
		String isExist = "null";

		if (StringUtils.isNotBlank(gift_name)) {
			entity.setGift_name(StringUtils.trim(gift_name));
			List<GiftInfo> entityList = super.getFacade().getGiftInfoService().getGiftInfoList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward validateOtherTypeName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String type_name = (String) dynaBean.get("type_name");

		KonkaCategory k = new KonkaCategory();
		String isExist = "null";

		if (StringUtils.isNotBlank(type_name)) {

			k.setC_name(StringUtils.trim(type_name));
			k.setC_type(15);
			List<KonkaCategory> entityList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(k);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String gift_id = (String) dynaBean.get("gift_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(gift_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GiftInfo entity = new GiftInfo();
		entity.setGift_id(Long.valueOf(gift_id));
		entity.setStatus(0);
		super.getFacade().getGiftInfoService().modifyGiftInfo(entity);

		saveMessage(request, "konka.restart.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String gift_id = (String) dynaBean.get("gift_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(gift_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		GiftInfo entity = new GiftInfo();
		entity.setGift_id(Long.valueOf(gift_id));
		entity.setStatus(1);
		super.getFacade().getGiftInfoService().modifyGiftInfo(entity);

		saveMessage(request, "konka.close.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
