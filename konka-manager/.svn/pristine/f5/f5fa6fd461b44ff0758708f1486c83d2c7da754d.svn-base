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
import com.ebiz.mmt.domain.KonkaMobileStocksHis;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileStocksHisAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String coll_id = (String) dynaBean.get("coll_id");
		String storage_id = (String) dynaBean.get("storage_id");
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
		entity.setColl_id(new Long(coll_id));
		entity.setStorage_id(new Long(storage_id));
		Long recordCount = getFacade().getKonkaMobileStocksHisService()
				.getKonkaMobileStocksHisCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileStocksHis> entityList = getFacade()
				.getKonkaMobileStocksHisService()
				.getKonkaMobileStocksHisPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
		super.copyProperties(entity, form);

		if (null == entity.getOp_id()) {
			getFacade().getKonkaMobileStocksHisService()
					.createKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileStocksHisService()
					.modifyKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
			entity.setOp_id(new Long(id));
			entity = getFacade().getKonkaMobileStocksHisService()
					.getKonkaMobileStocksHis(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
			entity.setOp_id(new Long(id));
			getFacade().getKonkaMobileStocksHisService()
					.modifyKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaMobileStocksHisService()
					.modifyKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
			entity.setOp_id(new Long(id));
			entity = getFacade().getKonkaMobileStocksHisService()
					.getKonkaMobileStocksHis(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}