package com.ebiz.mmt.web.struts.manager.admin;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobilePayment;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-05-09
 */
public class KonkaMobilePaymentAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		KonkaMobilePayment entity = new KonkaMobilePayment();
		entity.setStatus(0);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_id = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_id) {// 分公司用户
			entity.setDept_id(Long.valueOf(dept_id));
		}
		
		Long recordCount = getFacade().getKonkaMobilePaymentService().getKonkaMobilePaymentCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobilePayment> entityList = getFacade().getKonkaMobilePaymentService().getKonkaMobilePaymentPaginatedList(entity);
		for (KonkaMobilePayment konkaMobilePayment : entityList) {
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(konkaMobilePayment.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (null != kd)
				konkaMobilePayment.getMap().put("dept_name", kd.getDept_name());
		}
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_id = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_id) {// 分公司用户
			request.setAttribute("isHeadquarters", "false");
		} else {// 总部用户
			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			kd.setPar_id(0L);
			kd.getMap().put("order_by_dept_name", true);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
			request.setAttribute("konkaDeptList", konkaDeptList);
			request.setAttribute("isHeadquarters", "true");
		}
		
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("dept_id", dept_id);
		
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaMobilePayment entity = new KonkaMobilePayment();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaMobilePaymentService().getKonkaMobilePayment(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		// end

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");

		KonkaMobilePayment entity = new KonkaMobilePayment();
		super.copyProperties(entity, form);
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaMobilePayment t = new KonkaMobilePayment();
		t.getMap().put("dept_id", Long.valueOf(dept_id));
		t.setStatus(1);
		super.getFacade().getKonkaMobilePaymentService().modifyKonkaMobilePayment(t);
		entity.setStatus(0);
		
		if (StringUtils.isBlank(id)) {// insert
			entity.setDept_id(Long.valueOf(dept_id));
			super.getFacade().getKonkaMobilePaymentService().createKonkaMobilePayment(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			entity.setModify_man(peProdUser.getId());
			entity.setModify_time(new Date());
			super.getFacade().getKonkaMobilePaymentService().createKonkaMobilePayment(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=").append(tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}