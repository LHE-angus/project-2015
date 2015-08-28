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

public class TerminalFeedbackAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setIs_type(0);
		entity.setC_index(new Long(7));
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryListForSelect(entity);
		request.setAttribute("categoryList", categoryList);

		request.setAttribute("mobileCategoryList", getMobileCategories(7));

		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String question_person_like = (String) dynaBean.get("question_person_like");
		String message_type = (String) dynaBean.get("message_type");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Boolean role_id_gt_30 = false;
		Boolean role_id_btw_30_40 = false;
		Boolean role_id_btw_40_60 = false;
		PeRoleUser pUser = new PeRoleUser();
		pUser.setUser_id(ui.getId());
		List<PeRoleUser> pUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);
		if (pUserList.size() > 0) {
			for (PeRoleUser temp : pUserList) {
				if (temp.getRole_id() < 30) {
					role_id_gt_30 = true;
				}
				if (temp.getRole_id() >= 30 && temp.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
				if (temp.getRole_id() >= 40 && temp.getRole_id() < 60) {
					role_id_btw_40_60 = true;
				}
			}
		}

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());

		KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();

		super.copyProperties(entity, form);

		if (!role_id_gt_30 && role_id_btw_30_40) {// 分公司
			entity.setSubcomp_id(kDept.getDept_id());
		} else if (!role_id_gt_30 && !role_id_btw_30_40 && role_id_btw_40_60) {// 经办
			entity.setOffice_id(ui.getDept_id());
			dynaBean.set("is_jb", "1");
		} else if (!role_id_gt_30 && !role_id_btw_30_40 && !role_id_btw_40_60) {// 经办一下
			entity.setQuestion_id(ui.getId());
			dynaBean.set("is_self", "1");
		}
		// 订单的意见反馈需要排除在外
		entity.getMap().put("trade_index_is_null", "true");

		entity.setMessage_from(2); // 来源：意见反馈
		entity.getMap().put("have_where", "have_where");
		entity.getMap().put("question_person_like", question_person_like);
		entity.getMap().put("message_type", message_type);
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

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		if (!role_id_gt_30 && role_id_btw_30_40) {
			konkaDept.setDept_id(kDept.getDept_id());
		}
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

		// 类别 下拉选项
		KonkaMobileCategory kmcate = new KonkaMobileCategory();
		kmcate.setC_type(7);
		kmcate.setIs_type(1);
		kmcate.setIs_del(0);
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryListForSelect(kmcate);
		request.setAttribute("categoryList", categoryList);

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

			// 办事处
			entity.setOffice_id(ui.getDept_id());

			// 分公司
			if (ui.getPar_dept_id() != null) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(ui.getPar_dept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (konkaDept != null) {
					entity.setSubcomp_id(konkaDept.getDept_id());
				}
			} else {
				super.renderText(response, "请重新登录！");
				return null;
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
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
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
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

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

	public ActionForward toReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String feed_id = (String) dynaBean.get("feed_id");

		if (GenericValidator.isLong(feed_id)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.setId(new Long(feed_id));
			entity = getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			request.setAttribute("entity", entity);
			super.copyProperties(form, entity);

			// return mapping.findForward("input");
			return new ActionForward("/admin/TerminalFeedback/reply.jsp");
		} else {
			saveError(request, "errors.long", feed_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward reply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String feed_id = (String) dynaBean.get("feed_id");
		String fb_content = (String) dynaBean.get("fb_content");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaMobileTerminalFbBack entity = new KonkaMobileTerminalFbBack();
		entity.setContent(fb_content);
		entity.setFeed_id(Long.valueOf(feed_id));
		entity.setQuestion_id(ui.getId());
		entity.setQuestion_person(ui.getReal_name());
		entity.setAdd_date(new Date());
		// 办事处
		entity.setOffice_id(ui.getDept_id());

		// 分公司
		if (ui.getPar_dept_id() != null) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(ui.getPar_dept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (konkaDept != null) {
				entity.setSubcomp_id(konkaDept.getDept_id());
			}
		}
		getFacade().getKonkaMobileTerminalFbBackService().createKonkaMobileTerminalFbBack(entity);
		saveMessage(request, "entity.inserted");
		return list(mapping, form, request, response);

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