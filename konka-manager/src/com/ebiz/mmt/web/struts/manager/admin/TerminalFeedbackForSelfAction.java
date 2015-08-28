package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileTerminalFbBack;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class TerminalFeedbackForSelfAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward addonmobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.add(mapping, form, request, response);
		return new ActionForward("/../manager/admin/TerminalFeedbackForSelf/mobile_form.jsp");
	}

	public ActionForward listonmobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.list(mapping, form, request, response);
		return new ActionForward("/../manager/admin/TerminalFeedbackForSelf/mobile_list.jsp");
	}

	public ActionForward viewonmobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.view(mapping, form, request, response);
		return new ActionForward("/../manager/admin/TerminalFeedbackForSelf/mobile_view.jsp");
	}

	public ActionForward saveonmobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.save(mapping, form, request, response);

		// the line below is added for pagination
		return new ActionForward("/../manager/admin/TerminalFeedbackForSelf.do?method=listonmobile", true);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		request.setAttribute("mobileCategoryList", getMobileCategories(7));

		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
		super.copyProperties(entity, form);

		entity.setMessage_from(2); // 来源：意见反馈
		entity.setIs_del(0);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
		}
		request.setAttribute("role_id_eq_10", role_id_eq_10);

		// if (!role_id_eq_10) {
		// 非系统管理员只能看见自己的记录
		entity.setQuestion_id(ui.getId());
		// }

		entity.getMap().put("have_where", "have_where");

		Long recordCount = getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedbackCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileTerminalFeedback> entityList = getFacade().getKonkaMobileTerminalFeedbackService()
				.getKonkaMobileTerminalFeedbackPaginatedList(entity);

		for (KonkaMobileTerminalFeedback cur : entityList) {
			KonkaMobileTerminalFbBack b = new KonkaMobileTerminalFbBack();
			b.setFeed_id(cur.getId());
			List<KonkaMobileTerminalFbBack> konkaMobileTerminalFbBackList = super.getFacade()
					.getKonkaMobileTerminalFbBackService().getKonkaMobileTerminalFbBackList(b);

			cur.setKonkaMobileTerminalFbBackList(konkaMobileTerminalFbBackList);
		}

		request.setAttribute("entityList", entityList);

		// 类别 下拉选项
		request.setAttribute("mobileCategoryList", getMobileCategories(7));

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
		super.copyProperties(entity, form);

		if (null == entity.getId()) {
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			entity.setMessage_from(2); // 来源：意见反馈
			entity.setQuestion_id(ui.getId());
			entity.setQuestion_person(ui.getReal_name());
			entity.setDept_id(ui.getDept_id());

			KonkaDept kDept = new KonkaDept();
			kDept.setDept_id(ui.getDept_id());
			kDept = super.getFacade().getKonkaDeptService().getKonkaDept(kDept);

			if (null != kDept.getDept_type()) {
				if (kDept.getDept_type() == 4) {
					entity.setOffice_id(kDept.getDept_id());
					entity.setOffice_name(kDept.getDept_name());
				}
				if (kDept.getDept_type() == 5) {
					KonkaDept kDept2 = new KonkaDept();
					kDept2.setDept_id(kDept.getPar_id());
					kDept2 = super.getFacade().getKonkaDeptService().getKonkaDept(kDept2);
					entity.setSubcomp_id(kDept2.getPar_id());
					entity.setOffice_id(kDept.getPar_id());
				}
			}

			// 分公司
			if (ui.getPar_dept_id() != null) {
				KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
				if (konkaDept != null) {
					entity.setSubcomp_id(konkaDept.getDept_id());
					entity.setSubcomp_name(konkaDept.getDept_name());
				}
			}

			getFacade().getKonkaMobileTerminalFeedbackService().createKonkaMobileTerminalFeedback(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileTerminalFeedbackService().modifyKonkaMobileTerminalFeedback(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
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
		String id = (String) dynaBean.get("id");

		KonkaMobileCategory kmc = new KonkaMobileCategory();
		kmc.setIs_type(0);
		kmc.setC_index(new Long(7));
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryListForSelect(kmc);
		request.setAttribute("categoryList", categoryList);

		if (GenericValidator.isLong(id)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.setId(new Long(id));
			entity.getMap().put("feedback", 1);
			entity = getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));

			kmc.setC_index(new Long(entity.getMessage_type()));
			kmc.setIs_type(1);
			kmc.setIs_del(0);
			KonkaMobileCategory kmCategory = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileCategory(kmc);
			dynaBean.set("wl_index", kmCategory.getC_type());
			dynaBean.set("wl_type", entity.getMessage_type());

			super.copyProperties(form, entity);
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.setId(new Long(id));
			getFacade().getKonkaMobileTerminalFeedbackService().removeKonkaMobileTerminalFeedback(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaMobileTerminalFeedbackService().removeKonkaMobileTerminalFeedback(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.setId(new Long(id));
			entity = getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			request.setAttribute("entity", entity);
			super.copyProperties(form, entity);

			KonkaMobileTerminalFbBack konkaMobileTerminalFbBack = new KonkaMobileTerminalFbBack();
			konkaMobileTerminalFbBack.setFeed_id(Long.valueOf(id));
			List<KonkaMobileTerminalFbBack> konkaMobileTerminalFbBackList = super.getFacade()
					.getKonkaMobileTerminalFbBackService().getKonkaMobileTerminalFbBackList(konkaMobileTerminalFbBack);
			request.setAttribute("konkaMobileTerminalFbBackList", konkaMobileTerminalFbBackList);

			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public List<MobileSelectItem> getMobileCategories(int type) {
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(type);
		List<KonkaMobileCategory> _list = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _t : _list) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getC_index().toString());
			entity.setName(_t.getC_name());
			list.add(entity);
		}
		return list;
	}
}